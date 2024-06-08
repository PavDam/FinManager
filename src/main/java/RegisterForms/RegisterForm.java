/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package RegisterForms;

import LoginForms.LoginForm;
import com.mycompany.finmanagerpav.FinManagerPav;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author user
 */
public class RegisterForm extends javax.swing.JFrame {

    /**
     * Creates new form RegisterForm
     */
    public RegisterForm() {
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

        RegistrationTitleLabel = new javax.swing.JLabel();
        EmailLabel = new javax.swing.JLabel();
        PasswordLabel = new javax.swing.JLabel();
        RegisterButton = new javax.swing.JButton();
        PasswordField = new javax.swing.JPasswordField();
        EmailField = new javax.swing.JTextField();
        NameLabel = new javax.swing.JLabel();
        UsernameField = new javax.swing.JTextField();
        RepeatPassLabel = new javax.swing.JLabel();
        RepeatPassField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FinManager");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        RegistrationTitleLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        RegistrationTitleLabel.setText("Реєстрація");

        EmailLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        EmailLabel.setText("Ел. пошта");

        PasswordLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        PasswordLabel.setText("Пароль");

        RegisterButton.setText("Зареєструватися");
        RegisterButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RegisterButton.setFocusable(false);
        RegisterButton.setPreferredSize(new java.awt.Dimension(130, 23));
        RegisterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterButtonActionPerformed(evt);
            }
        });

        PasswordField.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        EmailField.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        EmailField.setName(""); // NOI18N

        NameLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        NameLabel.setText("Ім'я");

        UsernameField.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        UsernameField.setName(""); // NOI18N

        RepeatPassLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RepeatPassLabel.setText("Повторити пароль");

        RepeatPassField.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(RegistrationTitleLabel)
                        .addComponent(UsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PasswordLabel)
                    .addComponent(NameLabel)
                    .addComponent(RepeatPassLabel)
                    .addComponent(EmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EmailLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(RegisterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RepeatPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(RegistrationTitleLabel)
                .addGap(18, 18, 18)
                .addComponent(NameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RepeatPassLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RepeatPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(RegisterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        LoginForm LoginF = new LoginForm();
        LoginF.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private boolean isValidEmail(String email) {
        // Регулярний вираз для перевірки
        String emailRegex = "^[^@]+@[^@]+\\.[^@]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validateRegistrationInput(String username, String email, String password, String repPassword) {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || repPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Заповніть всі поля!", "Помилка", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Невірний формат ел. пошти!", "Помилка", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!password.equals(repPassword)) {
            JOptionPane.showMessageDialog(this, "Паролі не співпадають!", "Помилка", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try (Connection connection = FinManagerPav.data.getConnection()) {
            // Перевірка, чи email вже існує в базі даних
            String checkQuery = "SELECT COUNT(*) FROM `user` WHERE `Email` = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
                checkStatement.setString(1, email);
                try (ResultSet resultSet = checkStatement.executeQuery()) {
                    if (resultSet.next() && resultSet.getInt(1) > 0) {
                        JOptionPane.showMessageDialog(this, "Електронна пошта вже зайнята!", "Помилка", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Помилка при перевірці користувача!", "Помилка", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
        
    private void RegisterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterButtonActionPerformed
        // TODO add your handling code here:
        String username = UsernameField.getText();
        String email = EmailField.getText();
        String password = new String(PasswordField.getPassword());
        String repPassword = new String(RepeatPassField.getPassword());

        if (!validateRegistrationInput(username, email, password, repPassword)) {
            return;
        }
        
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        try (Connection connection = FinManagerPav.data.getConnection()) {
            // Створення нового користувача в базі даних
            String insertQuery = "INSERT INTO `user` (Username, Email, Password) VALUES (?, ?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                insertStatement.setString(1, username);
                insertStatement.setString(2, email);
                insertStatement.setString(3, hashedPassword);
                insertStatement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Акаунт створено!", "Успіх", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Помилка при реєстрації користувача!", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_RegisterButtonActionPerformed

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
            java.util.logging.Logger.getLogger(RegisterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField EmailField;
    private javax.swing.JLabel EmailLabel;
    private javax.swing.JLabel NameLabel;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JLabel PasswordLabel;
    private javax.swing.JButton RegisterButton;
    private javax.swing.JLabel RegistrationTitleLabel;
    private javax.swing.JPasswordField RepeatPassField;
    private javax.swing.JLabel RepeatPassLabel;
    private javax.swing.JTextField UsernameField;
    // End of variables declaration//GEN-END:variables
}