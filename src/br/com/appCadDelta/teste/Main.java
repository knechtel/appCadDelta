/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.teste;

import br.com.appCadDelta.JPAConttroller.AparelhoJpaController;
import br.com.appCadDelta.JPAConttroller.CidadeJpaController;
import br.com.appCadDelta.JPAConttroller.ClienteJpaController;
import br.com.appCadDelta.JPAConttroller.OrdemServicoJpaController;
import br.com.appCadDelta.entity.Aparelho;
import br.com.appCadDelta.entity.Cidade;
import br.com.appCadDelta.entity.Cliente;
import br.com.appCadDelta.entity.Ordemservico;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author Maiquel
 */
public class Main {

    public static void teste() {
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
        }
    }

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

    public static void importCidades() {
        String thisLine = null;
        try {
            BufferedReader bf = new BufferedReader(new FileReader("C:\\Users\\MaiquelKl\\Desktop\\delta\\cidadesInit.csv"));
            try {
                while ((thisLine = bf.readLine()) != null) { // while loop begins here
                    String[] str = thisLine.split(";");

                    for (int i = 0; i < str.length; i++) {
                        String s = str[i];
                        if (s.equals("\"\"")) {
                            System.out.println("nao contar");
                        } else {
                            Cidade c = new Cidade();
                            if (i == 0) {
                                c.setNome(s);
                            }
                            if (i == 1) {
                                c.setContChave(Integer.parseInt(s));
                            }
                            if (i == 2) {
                                c.setCep(s);
                            }
                            if (i == 3) {
                                c.setUf(s);
                            }
                            CidadeJpaController jpa = new CidadeJpaController();
                            jpa.create(c);
                        }
                        System.out.println("  " + s);
                    }
                    System.out.println("line : " + thisLine);
                }
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void importClientes() {
        int cont = 0;
        String thisLine = null;
        try {
            BufferedReader bf = new BufferedReader(new FileReader("C:\\Users\\MaiquelKl\\Desktop\\delta\\clientes.csv"));
            try {
                while ((thisLine = bf.readLine()) != null) { // while loop begins here
                    String[] str = thisLine.split(";");

                    Cliente c = new Cliente();
                    cont++;
                    for (int i = 0; i < str.length; i++) {
                        String s = str[i];
                        System.out.println("s = " + s + " ï " + i);
                        if (cont > 1) {
                            if (i == 0) {
                                c.setNome(s);
                            }
                            if (i == 1) {
                                c.setContador(Integer.parseInt(s));
                            }
                            if (i == 2) {
                                c.setRg(s);
                            }
                            if (i == 3) {
                                c.setCpf(s);
                            }

                            if (i == 4) {
                                c.setEndereco(s);
                            }
                            if (i == 5) {
                                //c.setCidadeId(s);
                            }

                            if (i == 8) {
                                c.setTelefoneResidencial(s);
                            }
                            if (i == 9) {
                                c.setCelular(s);
                            }
                            if (i == 11) {
                                c.setTelefoneProfissional(s);
                            }


                            System.out.println("cont = " + cont + " string [i] =" + s + " i = " + i);
                        }
                    }
                    ClienteJpaController jpa = new ClienteJpaController();
                    if (c.getContador() != null) {
                        jpa.create(c);
                    }
                    System.out.println("cont " + cont + " line : " + thisLine);
                }
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        int cont = 0;
        String thisLine = null;
        try {
            BufferedReader bf = new BufferedReader(new FileReader("/Users/maiquelknechtel/Documents/delta/aparelhos.csv"));
            try {
                while ((thisLine = bf.readLine()) != null) { // while loop begins here
                    String[] str = thisLine.split(";");

                    Aparelho c = new Aparelho();
                    cont++;
                    for (int i = 0; i < str.length; i++) {
                        String s = str[i];
                        System.out.println("s = " + s + " ï " + i);
                        if (cont > 1) {
                            if (i == 0) {
                                //contOS
                                if (!s.equals("")) {
                                    if (cont == 6) {
                                        System.out.println("linha "+thisLine);
                                        System.out.println("ss = " + s);
                                    }
                                    c.setContOs(Integer.parseInt(s));
                                }
                            }
                            if (i == 1) {
                                c.setDescricao(s);
                            }
                            if (i == 2) {
                                c.setContador(Integer.parseInt(s));
                            }
                            if (i == 3) {
                                //data Entrada
                            }
                            if (i == 4) {
                                //dataSaida
                            }
                            if (i == 5) {
                                //dataEntradaRetorno
                            }
                            if (i == 6) {
                                //saidaRetorno
                            }
                            if (i == 7) {
                                c.setMarca(s);
                            }
                            if (i == 8) {
                                c.setModelo(s);
                            }
                            if (i == 9) {
                                c.setSerial(s);
                            }
                            if (i == 10) {
                                c.setDefeitoApresentado(s);
                            }
                            if (i == 11) {
                                c.setDefeitoCostado(s);
                            }
                            if (i == 12) {
                                c.setUsuarioRecebedor(s);
                            }

                            if (i == 13) {
                                c.setUsuarioEntregador(s);
                            }
                            if (i == 14) {
                                //totalPecas
                            }
                            if (i == 15) {
                                //maodeObra
                            }
                            if (i == 16) {
                                //desconto
                            }
                            if (i == 17) {
                                //orcamento
                            }
                            if (i == 18) {
                                if (s.equals("SIM")) {
                                    c.setAutorizado(1);
                                } else {
                                    c.setAutorizado(0);
                                }
                            }
                            if (i == 19) {
                                //garantia
                            }
                            if (i == 20) {
                                //pago
                            }
                            if (i == 21) {
                                //sem conserto
                            }
                            if (i == 22) {
                                c.setTecResponsavel(s);
                            }
                            if (i == 26) {
                                if (s.equals("sim")) {
                                    c.setPronto(1);
                                } else {
                                    c.setPronto(0);
                                }
                            }
                            if (i == 27) {
                                c.setAspecto(s);
                            }



                        }
                    }
                    AparelhoJpaController jpa = new AparelhoJpaController();
                    //    if (c.getContador() != null) {
                    jpa.create(c);
                    //    }
                    System.out.println("cont " + cont + "STR LENGHT"+str.length+" line : " + thisLine);
                }
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
