/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package MenuForms;

import AccountsForms.AccountsForm;
import LoginForms.LoginForm;
import com.mycompany.finmanagerpav.DialogHelper;
import com.mycompany.finmanagerpav.FinManagerPav;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class MenuForm extends javax.swing.JFrame {

    /**
     * Creates new form MenuForm
     */
    public MenuForm() {
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

        WelcomeLabel = new javax.swing.JLabel();
        UserExitButton = new javax.swing.JButton();
        ToTransactionsButton = new javax.swing.JButton();
        ToAccountsButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FinManager");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        WelcomeLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        WelcomeLabel.setText("Привіт, <ім'я>!");

        UserExitButton.setText("Вихід");
        UserExitButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        UserExitButton.setFocusable(false);
        UserExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserExitButtonActionPerformed(evt);
            }
        });

        ToTransactionsButton.setText("Транзакції");
        ToTransactionsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ToTransactionsButton.setFocusable(false);
        ToTransactionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ToTransactionsButtonActionPerformed(evt);
            }
        });

        ToAccountsButton.setText("Гаманці");
        ToAccountsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ToAccountsButton.setFocusable(false);
        ToAccountsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ToAccountsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(ToTransactionsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(WelcomeLabel)
                    .addComponent(ToAccountsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(117, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(UserExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(WelcomeLabel)
                .addGap(18, 18, 18)
                .addComponent(ToTransactionsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ToAccountsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                .addComponent(UserExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        WelcomeLabel.setText("Привіт, " + FinManagerPav.currentUsername + "!");
        
        // Перевірка наявності хоча б одного запису в таблиці Accounts для поточного користувача
        try (Connection connection = FinManagerPav.data.getConnection()) {
            String query = "SELECT COUNT(*) FROM `account` WHERE `UserID` = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, FinManagerPav.currentUserID);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next() && resultSet.getInt(1) == 0) {
                        // Якщо в поточного користувача немає гаманців, вимкнути кнопку транзакцій
                        ToTransactionsButton.setEnabled(false);
                        ToTransactionsButton.setText("Створіть гаманець");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Помилка при перевірці записів у таблиці Accounts!", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_formWindowOpened

    private void UserExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserExitButtonActionPerformed
        // TODO add your handling code here:
        if (DialogHelper.showConfirmationDialog("Вийти з акаунту?", "Вихід")) {
            FinManagerPav.currentUserID = 0;
            FinManagerPav.currentUsername = null; 
            LoginForm LoginF = new LoginForm();
            LoginF.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_UserExitButtonActionPerformed

    private void ToTransactionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ToTransactionsButtonActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_ToTransactionsButtonActionPerformed

    private void ToAccountsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ToAccountsButtonActionPerformed
        // TODO add your handling code here:
        AccountsForm AccountsF = new AccountsForm();
        AccountsF.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ToAccountsButtonActionPerformed

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
            java.util.logging.Logger.getLogger(MenuForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LoginButton;
    private javax.swing.JButton LoginButton1;
    private javax.swing.JButton ToAccountsButton;
    private javax.swing.JButton ToTransactionsButton;
    private javax.swing.JButton UserExitButton;
    public javax.swing.JLabel WelcomeLabel;
    // End of variables declaration//GEN-END:variables
}
