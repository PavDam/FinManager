/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package AccountsForms;

import static AccountsForms.AccountsForm.displayAccounts;
import com.mycompany.finmanagerpav.FinManagerPav;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class EditAccountDialog extends javax.swing.JDialog {

    /**
     * Creates new form EditAccountDialog
     */
    public EditAccountDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CurrencyLabel = new javax.swing.JLabel();
        UpdateAccountButton = new javax.swing.JButton();
        AmountLabel = new javax.swing.JLabel();
        AmountField = new javax.swing.JTextField();
        TitleLabel = new javax.swing.JLabel();
        TitleField = new javax.swing.JTextField();
        AddAccountLabel = new javax.swing.JLabel();
        CurrencyComboBox = new javax.swing.JComboBox<>();
        IdLabel = new javax.swing.JLabel();
        IdField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Редагувати рахунок");

        CurrencyLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CurrencyLabel.setText("Валюта");

        UpdateAccountButton.setText("Оновити");
        UpdateAccountButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        UpdateAccountButton.setFocusable(false);
        UpdateAccountButton.setPreferredSize(new java.awt.Dimension(130, 23));
        UpdateAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateAccountButtonActionPerformed(evt);
            }
        });

        AmountLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AmountLabel.setText("Сума");

        AmountField.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        AmountField.setName(""); // NOI18N

        TitleLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TitleLabel.setText("Назва");

        TitleField.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        TitleField.setName(""); // NOI18N

        AddAccountLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AddAccountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AddAccountLabel.setText("Змініть дані гаманця:");

        CurrencyComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UAH", "USD" }));
        CurrencyComboBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        CurrencyComboBox.setEnabled(false);
        CurrencyComboBox.setFocusable(false);

        IdLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        IdLabel.setText("ID");

        IdField.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        IdField.setEnabled(false);
        IdField.setName(""); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(UpdateAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CurrencyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(TitleLabel)
                        .addComponent(TitleField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(AddAccountLabel, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(IdLabel)
                            .addComponent(IdField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(AmountField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(AmountLabel))
                    .addComponent(CurrencyLabel))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(AddAccountLabel)
                .addGap(18, 18, 18)
                .addComponent(IdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(IdField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TitleField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(AmountField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CurrencyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CurrencyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(UpdateAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean validateInput(int accountID, String title, String amountText) {
        if (title.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Будь ласка, заповніть усі поля.", "Помилка", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        float floatAmount;
        try {
            floatAmount = Float.parseFloat(amountText);
            if (floatAmount <= 0) {
                JOptionPane.showMessageDialog(this, "Введіть дійсне число", "Помилка", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Некоректний формат суми.", "Помилка", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        // Перевірка на існування гаманця з такою ж назвою для обраного користувача
        try (Connection connection = FinManagerPav.data.getConnection()) {
            String query = "SELECT COUNT(*) FROM account WHERE UserID = ? AND Title = ? AND ID <> ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, FinManagerPav.currentUserID);
                statement.setString(2, title);
                statement.setInt(3, accountID);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next() && resultSet.getInt(1) > 0) {
                        JOptionPane.showMessageDialog(this, "Гаманець з такою назвою вже існує.", "Помилка", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Помилка при перевірці гаманця.", "Помилка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
        
    private void UpdateAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateAccountButtonActionPerformed
        // TODO add your handling code here:
        int accountID = Integer.parseInt(IdField.getText());
        String title = TitleField.getText();
        String currency = (String) CurrencyComboBox.getSelectedItem();
        String amountText = AmountField.getText();

        if (!validateInput(accountID, title, amountText)) {
            return; // Вихід, якщо введені дані некоректні
        }

        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("#.##", decimalFormatSymbols);
        String formattedAmount = df.format(Float.parseFloat(amountText));
        float amount = Float.parseFloat(formattedAmount);

        try (Connection connection = FinManagerPav.data.getConnection()) {
            String query = "UPDATE account SET Title = ?, Amount = ?, Currency = ? WHERE ID = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, title);
                statement.setFloat(2, amount);
                statement.setString(3, currency);
                statement.setInt(4, accountID);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    displayAccounts();
                    JOptionPane.showMessageDialog(this, "Рахунок успішно оновлено!", "Успіх", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Не вдалося оновити рахунок!", "Помилка", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Помилка при оновленні рахунку!", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_UpdateAccountButtonActionPerformed

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
            java.util.logging.Logger.getLogger(EditAccountDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditAccountDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditAccountDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditAccountDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditAccountDialog dialog = new EditAccountDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AddAccountLabel;
    public javax.swing.JTextField AmountField;
    private javax.swing.JLabel AmountLabel;
    public javax.swing.JComboBox<String> CurrencyComboBox;
    private javax.swing.JLabel CurrencyLabel;
    public javax.swing.JTextField IdField;
    private javax.swing.JLabel IdLabel;
    public javax.swing.JTextField TitleField;
    private javax.swing.JLabel TitleLabel;
    private javax.swing.JButton UpdateAccountButton;
    // End of variables declaration//GEN-END:variables
}
