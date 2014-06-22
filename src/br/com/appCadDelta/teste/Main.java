/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.teste;

import br.com.appCadDelta.JPAConttroller.OrdemServicoJpaController;
import br.com.appCadDelta.entity.Aparelho;
import br.com.appCadDelta.entity.Ordemservico;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author Maiquel
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

    public static void remove() {
        System.out.println("init()");
        OrdemServicoJpaController osJpa = new OrdemServicoJpaController();

        Ordemservico os = osJpa.findAparelhosByIdOS(16).get(0);
        List<Aparelho> listAparelho = new ArrayList<Aparelho>();
        os.setListaAparelho(listAparelho);
        OrdemServicoJpaController osJpa1 = new OrdemServicoJpaController();
        osJpa1.edit(os);
        System.out.println("FIM");
    }

    public static void main(String[] args) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(df.format(new java.util.Date()));
    }
}
