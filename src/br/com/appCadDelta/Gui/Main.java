/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.Gui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author MaiquelKl
 */
public class Main {

    public static void teste(){
    JPanel panel = new JPanel();
        JLabel label = new JLabel("Entre com a senha:");
        JPasswordField pass = new JPasswordField(10);
        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"OK", "Cancel"};
        int option = JOptionPane.showOptionDialog(null, panel, "The title",
                JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[1]);
        if (option == 0) // pressing OK button
        {
            char[] password = pass.getPassword();
            System.out.println("Your password is: " + new String(password));
        }}
    
    public static void main(String[] args) {
        LoginJFrame login = new LoginJFrame();
        login.setVisible(true);
        login.setLocationRelativeTo(null);
    }
}
