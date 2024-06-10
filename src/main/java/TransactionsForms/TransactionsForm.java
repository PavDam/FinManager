/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package TransactionsForms;

import CategoriesForms.CustomCategoriesForm;
import MenuForms.MenuForm;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import static com.mycompany.finmanagerpav.CurrencyConverter.getExchangeRate;
import com.mycompany.finmanagerpav.FinManagerPav;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class TransactionsForm extends javax.swing.JFrame {

    /**
     * Creates new form TransactionsForm
     */
    public TransactionsForm() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    // Метод для відображення транзакцій користувачеві з конвертованою сумою в UAH
    public static void displayTransactions() {
        DefaultTableModel model = (DefaultTableModel) TransactionsTable.getModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < TransactionsTable.getColumnCount() - 1; i++) {
            TransactionsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        model.setRowCount(0); // Очищення таблиці перед відображенням нових даних

        int userID = FinManagerPav.currentUserID;
        float totalAmount = 0;

        try (Connection connection = FinManagerPav.data.getConnection()) {
            // Отримати вибрану валюту з CurrencyComboBox
            String selectedCurrency = (String) CurrencyComboBox.getSelectedItem();
            float exchangeRate = getExchangeRate();

            // Отримати поточний місяць і рік
            Calendar calendar = Calendar.getInstance();
            int currentMonth = calendar.get(Calendar.MONTH) + 1; // Місяці починаються з 0
            int currentYear = calendar.get(Calendar.YEAR);

            // Запит для отримання транзакцій з бази даних
            String query = "SELECT t.ID, a.Title AS AccountTitle, a.Currency AS AccountCurrency, t.Date, c.Name AS CategoryName, t.Amount, t.Note " +
                    "FROM transaction t " +
                    "JOIN account a ON t.AccountID = a.ID " +
                    "JOIN category c ON t.CategoryID = c.ID " +
                    "WHERE t.UserID = ? " +
                    "ORDER BY t.Date DESC, t.ID DESC";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userID);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int ID = resultSet.getInt("ID");
                        String accountTitle = resultSet.getString("AccountTitle");
                        String accountCurrency = resultSet.getString("AccountCurrency");
                        String dateStr = resultSet.getString("Date");
                        String formattedDate = formatDate(dateStr);
                        String categoryName = resultSet.getString("CategoryName");
                        float amount = resultSet.getFloat("Amount");
                        String note = resultSet.getString("Note");

                        // Перевірити, чи валюта акаунта - USD, і конвертувати суму транзакції в UAH
                        if ("USD".equals(accountCurrency) && "UAH".equals(selectedCurrency)) {
                            // Отримати курс обміну USD-UAH та конвертувати суму транзакції в UAH
                            amount *= exchangeRate;
                        } else if ("UAH".equals(accountCurrency) && "USD".equals(selectedCurrency)) {
                            amount /= exchangeRate;
                        }

                        // Додати рядок з транзакцією в таблицю
                        model.addRow(new Object[]{ID, accountTitle, formattedDate, categoryName, amount, note});

                        // Отримати місяць та рік з dateStr
                        String[] dateParts = dateStr.split("-");
                        int transactionYear = Integer.parseInt(dateParts[0]);
                        int transactionMonth = Integer.parseInt(dateParts[1]);

                        // Додати суму транзакції до загальної суми, якщо місяць і рік збігаються і сума від'ємна
                        if (amount < 0 && transactionYear == currentYear && transactionMonth == currentMonth) {
                            totalAmount += amount;
                        }
                    }

                    float LimitInSelectedCurrency = FinManagerPav.currentLimit;
                    if ("USD".equals(selectedCurrency)) {
                        LimitInSelectedCurrency /= exchangeRate; // Конвертуємо ліміт у валюту обраної транзакції
                    }
                    
                    if(totalAmount != 0.0){
                        totalAmount *= -1;
                    }
                    UseLimitLabel1.setText(String.format("%.2f/%.2f %s", totalAmount, LimitInSelectedCurrency, selectedCurrency));
                    
                    if (totalAmount * -1 <= LimitInSelectedCurrency){
                        LimitStatusLabel.setText("Все гаразд!");
                        LimitStatusLabel.setForeground(new Color(0, 204, 0));
                    }
                    else {
                        LimitStatusLabel.setText("Перевищено!");
                        LimitStatusLabel.setForeground(new Color(255, 51, 51));                    
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Помилка при відображенні транзакцій!", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private static String formatDate(String dateStr) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy", new Locale("uk", "UA"));
        String formattedDate = "";

        try {
            Date date = inputFormat.parse(dateStr);
            formattedDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formattedDate;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TransactionsTable = new javax.swing.JTable();
        AddAccountButton = new javax.swing.JButton();
        CurrencyComboBox = new javax.swing.JComboBox<>();
        UseLimitLabel0 = new javax.swing.JLabel();
        UseLimitLabel1 = new javax.swing.JLabel();
        LimitStatusLabel = new javax.swing.JLabel();
        ToCustomCategoriesButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FinManager");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        TransactionsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Рахунок", "Дата", "Категорія", "Сума", "Примітка"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TransactionsTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(TransactionsTable);
        if (TransactionsTable.getColumnModel().getColumnCount() > 0) {
            TransactionsTable.getColumnModel().getColumn(0).setPreferredWidth(35);
        }

        AddAccountButton.setText("Нова транзакція");
        AddAccountButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AddAccountButton.setFocusable(false);
        AddAccountButton.setPreferredSize(new java.awt.Dimension(130, 23));
        AddAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddAccountButtonActionPerformed(evt);
            }
        });

        CurrencyComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UAH", "USD" }));
        CurrencyComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CurrencyComboBoxActionPerformed(evt);
            }
        });

        UseLimitLabel0.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        UseLimitLabel0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        UseLimitLabel0.setText("Витрачено/ліміт:");

        UseLimitLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        UseLimitLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        UseLimitLabel1.setText("0/1 <UAH/USD>");

        LimitStatusLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LimitStatusLabel.setForeground(new java.awt.Color(0, 204, 0));
        LimitStatusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LimitStatusLabel.setText("Статус");

        ToCustomCategoriesButton.setText("Категорії");
        ToCustomCategoriesButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ToCustomCategoriesButton.setFocusable(false);
        ToCustomCategoriesButton.setPreferredSize(new java.awt.Dimension(130, 23));
        ToCustomCategoriesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ToCustomCategoriesButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CurrencyComboBox, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(UseLimitLabel0, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LimitStatusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(658, 658, 658)
                        .addComponent(UseLimitLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(8, 8, 8))
            .addGroup(layout.createSequentialGroup()
                .addGap(675, 675, 675)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ToCustomCategoriesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(CurrencyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(UseLimitLabel0)
                .addGap(0, 0, 0)
                .addComponent(UseLimitLabel1)
                .addGap(0, 0, 0)
                .addComponent(LimitStatusLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ToCustomCategoriesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(AddAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        MenuForm MenuF = new MenuForm();
        MenuF.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void AddAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddAccountButtonActionPerformed
        // TODO add your handling code here:
        AddTransactionDialog AddTransactionD = new AddTransactionDialog(this, true);
        AddTransactionD.setVisible(true);
    }//GEN-LAST:event_AddAccountButtonActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        displayTransactions();
    }//GEN-LAST:event_formWindowOpened

    private void CurrencyComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CurrencyComboBoxActionPerformed
        // TODO add your handling code here:
        displayTransactions();
    }//GEN-LAST:event_CurrencyComboBoxActionPerformed

    private void ToCustomCategoriesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ToCustomCategoriesButtonActionPerformed
        // TODO add your handling code here:
        CustomCategoriesForm CustomCategoriesF = new CustomCategoriesForm();
        CustomCategoriesF.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_ToCustomCategoriesButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TransactionsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransactionsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransactionsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransactionsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransactionsForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddAccountButton;
    private static javax.swing.JComboBox<String> CurrencyComboBox;
    private static javax.swing.JLabel LimitStatusLabel;
    public javax.swing.JButton ToCustomCategoriesButton;
    private static javax.swing.JTable TransactionsTable;
    private javax.swing.JLabel UseLimitLabel0;
    private static javax.swing.JLabel UseLimitLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
