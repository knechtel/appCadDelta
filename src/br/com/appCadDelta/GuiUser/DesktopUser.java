/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.GuiUser;

import br.com.appCadDelta.Gui.*;
import br.com.appCadDelta.JPAConttroller.OrdemServicoJpaController;
import br.com.appCadDelta.entity.Ordemservico;
import br.com.appCadDelta.util.ProgressMonitorDemo;
import br.com.appCadDelta.util.SessionDesktop;
import br.com.appCadDelta.util.Task;
import br.com.appCadDelta.util.Util;


import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.ProgressMonitor;

/**
 *
 * @author Maiquel
 */
public class DesktopUser extends javax.swing.JFrame {

    /**
     * Creates new form Desktop
     */
    public DesktopUser() {
        super(">_ Sistema de controle de ordem de serviço");
//        try {
//            UIManager.setLookAndFeel(
//        UIManager.getCrossPlatformLookAndFeelClassName());
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Desktop.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            Logger.getLogger(Desktop.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(Desktop.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(Desktop.class.getName()).log(Level.SEVERE, null, ex);
//        }

        initComponents();
        setSize(1024, 700);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 255));

        jMenu1.setText("File");

        jMenuItem1.setText("Ordem de Servico");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Cadastro de clientes");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenu3.setText("Livro caixa");

        jMenuItem7.setText("Os por dia");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenu1.add(jMenu3);

        jMenu4.setText("Buscar O.S");

        jMenuItem3.setText("por dia de entrada");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem3);

        jMenuItem8.setText("por ID");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem8);

        jMenu1.add(jMenu4);

        jMenuItem5.setText("Cadastro de cidades");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem4.setText("Sair");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem6.setText("Imprimir OS");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        br.com.appCadDelta.GuiUser.CadOsUserJInternalFrame cadOs = new br.com.appCadDelta.GuiUser.CadOsUserJInternalFrame(null, false);
        jDesktopPane1.add(cadOs);
        cadOs.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        CadClienteJInternalFrame cadCliente = new CadClienteJInternalFrame();
        jDesktopPane1.add(cadCliente);
        cadCliente.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        CadCidadeJInternalFrame cadCidade = new CadCidadeJInternalFrame();
        jDesktopPane1.add(cadCidade);
        cadCidade.setVisible(true);

    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        String codOs = JOptionPane.showInputDialog(null, "Digite o codigo da ordem de serviço:");
        boolean parseToNumber = true;

        try {
            Integer.parseInt(codOs);

        } catch (NumberFormatException e) {
            parseToNumber = false;
        }

        if (codOs == null) {
            JOptionPane.showMessageDialog(null, "Operação cancelada!");
        } else if (parseToNumber) {
            OrdemServicoJpaController osJpaEdit = new OrdemServicoJpaController();
            OrdemServicoJpaController osJpa = new OrdemServicoJpaController();
            Ordemservico os = osJpa.findById(Integer.parseInt(codOs));

            if (os != null) {

                if (os.getDataSaida() == null) {
                    os.setDataSaida(new Date());
                    osJpaEdit.edit(os);

                } else {
                    //  ProgressMonitorDemo progress = new ProgressMonitorDemo();
                    SessionDesktop.setIdRelatorio(os.getId());
                    /// progress.createAndShowGUI();
//                    Relatorio relatorio = new Relatorio();
//                    try {
//                        relatorio.geraRelatorio(os.getId());
//                    } catch (FileNotFoundException ex) {
//                        Logger.getLogger(DesktopUser.class.getName()).log(Level.SEVERE, null, ex);
//                    }


                    ProgressMonitorDemo pgd = new ProgressMonitorDemo();
                    pgd.createAndShowGUI();
                }

            } else {
                JOptionPane.showMessageDialog(null, "É necessário entrar com um código válido de O.S!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "É necessário entrar com um código válido de O.S!");
        }


    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        String str = JOptionPane.showInputDialog(null, "Digite a data da saída da ordem de serviço:");

        CaixaJInternalFrame caixa = new CaixaJInternalFrame(str);
        jDesktopPane1.add(caixa);
        caixa.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        String str = JOptionPane.showInputDialog(null, "Digite a data de entrada da ordem de serviço:");
        boolean erroStringToDate = false;

        try {
            Util.sringToDate(str);
            erroStringToDate = false;
        } catch (Exception ex) {
            ex.printStackTrace();
            erroStringToDate = true;
        }
        if (erroStringToDate) {
            JOptionPane.showMessageDialog(null, " Data inválida!");
        }

        if (str != null && erroStringToDate == false) {
            CadOsUserJInternalFrame cadOs = new CadOsUserJInternalFrame(str, true);
            jDesktopPane1.add(cadOs);
            cadOs.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        String str = JOptionPane.showInputDialog(null, "Digite o Id da ordem de serviço:");
        if (str != null ) {
            CadOsUserJInternalFrame cadOs = new CadOsUserJInternalFrame(str, false);
            jDesktopPane1.add(cadOs);
            cadOs.setVisible(true);
        }
        
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    public static JDesktopPane getDesktopPane() {
        return jDesktopPane1;
    }

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
            java.util.logging.Logger.getLogger(DesktopUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DesktopUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DesktopUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DesktopUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DesktopUser().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    // End of variables declaration//GEN-END:variables
}
