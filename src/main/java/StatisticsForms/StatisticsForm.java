/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package StatisticsForms;

import MenuForms.MenuForm;
import static com.mycompany.finmanagerpav.CurrencyConverter.getExchangeRate;
import com.mycompany.finmanagerpav.FinManagerPav;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author user
 */
public class StatisticsForm extends javax.swing.JFrame {

    /**
     * Creates new form StatisticForm
     */
    public StatisticsForm() {
        initComponents();
        setLocationRelativeTo(null);
        showChart();
    }

    private void showChart() {
        int selectedIndex = TypeComboBox.getSelectedIndex();
        String selectedType = (String) TypeComboBox.getSelectedItem();

        //Якщо вибрано 0 або 1 пункт (кругова діаграма для Витрат або Отримань)
        switch (selectedIndex) {
            case 0, 1 -> showPieChart(selectedType);
            case 2 -> showBarChart("Витрати");
            case 3 -> showBarChart("Отримання");
        }
    }
    
    private void showPieChart(String selectedType) {
        try (Connection connection = FinManagerPav.data.getConnection()) {
            String query = "SELECT c.Name AS CategoryName, " +
                "COUNT(*) * 100.0 / (SELECT COUNT(*) FROM transaction t JOIN category c ON t.CategoryID = c.ID WHERE c.Type = ? AND t.UserID = ?) AS Percentage " +
                "FROM transaction t " +
                "JOIN category c ON t.CategoryID = c.ID " +
                "WHERE c.Type = ? AND t.UserID = ? " +
                "GROUP BY c.Name";

            DefaultPieDataset dataset = new DefaultPieDataset();

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, selectedType);
                statement.setInt(2, FinManagerPav.currentUserID);
                statement.setString(3, selectedType);
                statement.setInt(4, FinManagerPav.currentUserID);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String categoryName = resultSet.getString("CategoryName");
                        double percentage = resultSet.getDouble("Percentage");
                        dataset.setValue(categoryName, percentage);
                    }
                }
            }

            JFreeChart chart = ChartFactory.createPieChart(selectedType, dataset, false, true, false);
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(600, 300));
            DiagramPanel.removeAll();
            DiagramPanel.setLayout(new java.awt.BorderLayout());
            DiagramPanel.add(chartPanel, BorderLayout.CENTER);
            DiagramPanel.revalidate();
            DiagramPanel.repaint();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Помилка при відображенні статистики!", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }
        
    private void showBarChart(String selectedType) {
        float exchangeRate = getExchangeRate();
        if (exchangeRate == -1) {
            return; // Помилка при отриманні курсу обміну, виходимо з методу
        }

        try (Connection connection = FinManagerPav.data.getConnection()) {
            String query = "SELECT DATE_FORMAT(t.Date, '%Y-%m') AS Month, SUM(t.Amount * CASE WHEN a.Currency = 'USD' THEN ? ELSE 1 END) AS Total " +
                           "FROM transaction t " +
                           "JOIN category c ON t.CategoryID = c.ID " +
                           "JOIN account a ON t.AccountID = a.ID " + // Приєднуємо таблицю account для отримання валюти
                           "WHERE c.Type = ? AND t.UserID = ? " +
                           "GROUP BY DATE_FORMAT(t.Date, '%Y-%m') " +
                           "ORDER BY DATE_FORMAT(t.Date, '%Y-%m') ASC " +
                           "LIMIT 6";

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setFloat(1, exchangeRate);
                statement.setString(2, selectedType);
                statement.setInt(3, FinManagerPav.currentUserID);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String month = resultSet.getString("Month");
                        double total = resultSet.getDouble("Total");
                        if(total < 0){
                            total *= -1;
                        }
                        dataset.addValue(total, selectedType, month);
                    }
                }
            }

            JFreeChart barChart = ChartFactory.createBarChart(
                "Транзакції за місяцями: " + selectedType, 
                "Місяць", 
                selectedType, 
                dataset, 
                PlotOrientation.VERTICAL, 
                false, true, false);

            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.setPreferredSize(new java.awt.Dimension(600, 300));
            DiagramPanel.removeAll();
            DiagramPanel.setLayout(new java.awt.BorderLayout());
            DiagramPanel.add(chartPanel, BorderLayout.CENTER);
            DiagramPanel.revalidate();
            DiagramPanel.repaint();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Помилка при відображенні статистики!", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DiagramPanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        TypeComboBox = new javax.swing.JComboBox<>();
        SaveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FinManager");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        javax.swing.GroupLayout DiagramPanelLayout = new javax.swing.GroupLayout(DiagramPanel);
        DiagramPanel.setLayout(DiagramPanelLayout);
        DiagramPanelLayout.setHorizontalGroup(
            DiagramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        DiagramPanelLayout.setVerticalGroup(
            DiagramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        TypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Витрати", "Отримання", "За місяцями (Витрати)", "За місяцями (Отримання)" }));
        TypeComboBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        TypeComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TypeComboBox.setFocusable(false);
        TypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TypeComboBoxActionPerformed(evt);
            }
        });

        SaveButton.setText("Зберегти");
        SaveButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SaveButton.setFocusable(false);
        SaveButton.setPreferredSize(new java.awt.Dimension(130, 23));
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DiagramPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(TypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(DiagramPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        MenuForm MenuF = new MenuForm();
        MenuF.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void TypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TypeComboBoxActionPerformed
        // TODO add your handling code here:
        showChart();
    }//GEN-LAST:event_TypeComboBoxActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        // TODO add your handling code here:
        JFreeChart chart = ((ChartPanel) DiagramPanel.getComponent(0)).getChart();

        // Відображаємо діалогове вікно для вибору місця збереження
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Зберегти діаграму як PNG");
        fileChooser.setSelectedFile(new File("chart.png"));
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Отримуємо вибраний файл для збереження
            File fileToSave = fileChooser.getSelectedFile();
            try {
                // Зберігаємо діаграму у форматі PNG за вказаною шляхом
                ChartUtils.saveChartAsPNG(fileToSave, chart, 600, 300);
                JOptionPane.showMessageDialog(this, "Діаграму успішно збережено!", "Успіх", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Помилка при збереженні діаграми!", "Помилка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_SaveButtonActionPerformed

    
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
            java.util.logging.Logger.getLogger(StatisticsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StatisticsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StatisticsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StatisticsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StatisticsForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DiagramPanel;
    public javax.swing.JButton SaveButton;
    private javax.swing.JComboBox<String> TypeComboBox;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
