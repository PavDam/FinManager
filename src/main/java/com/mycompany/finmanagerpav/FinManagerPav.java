/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.finmanagerpav;

import LoginForms.LoginForm;
import com.mysql.cj.jdbc.MysqlDataSource;

/**
 *
 * @author user
 */
public class FinManagerPav {

    public static MysqlDataSource data = new MysqlDataSource(); 
    public static int currentUserID;
    public static String currentUsername;
    public static float currentLimit;
    
    public static void main(String[] args) {
        data.setServerName("sql7.freesqldatabase.com");
        data.setPort(3306);
        data.setUser("sql7713578");
        data.setPassword("8nAlC3Tcqv");
        data.setDatabaseName("sql7713578");
        
        LoginForm LoginF = new LoginForm();
        // Відображення вікна
        LoginF.setVisible(true);
    }
}
