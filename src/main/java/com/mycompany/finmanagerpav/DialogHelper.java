/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finmanagerpav;

import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class DialogHelper {
    /**
     * Показує діалогове вікно з підтвердженням.
     *
     * @param message Повідомлення, яке буде показане у вікні.
     * @param title Назва вікна.
     * @return true, якщо користувач натиснув "Так", false - якщо "Ні".
     */
    public static boolean showConfirmationDialog(String message, String title) {
        Object[] options = {"Так", "Ні"};
        int response = JOptionPane.showOptionDialog(null,
                message,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        
        return response == JOptionPane.YES_OPTION;
    }
}
