/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.finmanagerpav;

import com.mysql.cj.jdbc.MysqlDataSource;

/**
 *
 * @author user
 */
public class FinManagerPav {

    public static MysqlDataSource data = new MysqlDataSource(); 
    public static int currentUserID;
    public static String currentUsername;
    
    public static void main(String[] args) {
        data.setServerName("localhost");
        data.setPort(3306);
        data.setUser("root");
        data.setPassword("277353Damian");
        data.setDatabaseName("finance-manager");
        
        LoginForm LoginF = new LoginForm();
        // Відображення вікна
        LoginF.setVisible(true);
    }
}
