/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.finmanagerpav;

import MainForms.MenuForm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.prefs.Preferences;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author user
 */
public class LoginForm extends javax.swing.JFrame {

    public Preferences pref = Preferences.userRoot().node("Finmanager_remember");
    /**
     * Creates new form LoginForm
     */
    public LoginForm() {
        initComponents();
        setLocationRelativeTo(null);
        
        String getEmail = null;
        getEmail = pref.get("Email", getEmail);
        
        EmailField.setText(getEmail);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PasswordField = new javax.swing.JPasswordField();
        EmailField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        LoginButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        ToRegisterLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FinManager");
        setPreferredSize(new java.awt.Dimension(650, 400));
        setResizable(false);

        PasswordField.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        EmailField.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        EmailField.setName(""); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Вхід");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setLabelFor(EmailField);
        jLabel2.setText("Ел. пошта");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setLabelFor(PasswordField);
        jLabel3.setText("Пароль");

        LoginButton.setText("Увійти");
        LoginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LoginButton.setFocusable(false);
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Або ви можете  ");

        ToRegisterLabel.setForeground(new java.awt.Color(51, 102, 255));
        ToRegisterLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ToRegisterLabel.setText("зареєструватися!");
        ToRegisterLabel.setToolTipText("");
        ToRegisterLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ToRegisterLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ToRegisterLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(354, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(LoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel1)
                            .addComponent(EmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, 0)
                        .addComponent(ToRegisterLabel)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LoginButton)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(ToRegisterLabel))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ToRegisterLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ToRegisterLabelMouseClicked
        // TODO add your handling code here:
        RegisterForm RegF = new RegisterForm();
        // Відображення вікна
        RegF.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ToRegisterLabelMouseClicked

    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginButtonActionPerformed
        // TODO add your handling code here:
        String email = EmailField.getText();
        String password = new String(PasswordField.getPassword());
        
        if(email.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this, "Заповніть всі поля!", "Помилка", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try (Connection connection = FinManagerPav.data.getConnection()) {
            String query = "SELECT ID, Username, Password FROM `user` WHERE Email = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int userID = resultSet.getInt("ID");
                        String username = resultSet.getString("Username");
                        String hashedPassword = resultSet.getString("Password");

                        if (BCrypt.checkpw(password, hashedPassword)) {
                            // Пароль вірний
                            FinManagerPav.currentUserID = userID;
                            FinManagerPav.currentUsername = username;

                            pref.put("Email", email);
                            
                            MenuForm MenuF = new MenuForm();
                            MenuF.setVisible(true);
                            this.dispose(); 
                        } else {
                            // Невірний пароль
                            JOptionPane.showMessageDialog(this, "Невірний пароль!", "Помилка", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        // Користувача з такою поштою не знайдено
                        JOptionPane.showMessageDialog(this, "Користувача не знайдено!", "Помилка", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Помилка при вході!", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_LoginButtonActionPerformed

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
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField EmailField;
    private javax.swing.JButton LoginButton;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JLabel ToRegisterLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}