/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.Gui;

import br.com.appCadDelta.GuiUser.CadOsUserJInternalFrame;
import br.com.appCadDelta.JPAConttroller.AparelhoJpaController;
import br.com.appCadDelta.JPAConttroller.ClienteJpaController;
import br.com.appCadDelta.JPAConttroller.OrdemServicoJpaController;
import br.com.appCadDelta.JPAConttroller.PecasJpaController;
import br.com.appCadDelta.entity.Aparelho;
import br.com.appCadDelta.entity.Cidade;
import br.com.appCadDelta.entity.Cliente;
import br.com.appCadDelta.entity.Ordemservico;
import br.com.appCadDelta.entity.Peca;
import br.com.appCadDelta.relatorio.Relatorio;
import br.com.appCadDelta.util.LimitadorMoeda;
import br.com.appCadDelta.util.SessionDesktop;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Maiquel
 */
public class CadOsJInternalFrameSwap extends javax.swing.JInternalFrame {

    private DefaultComboBoxModel comboClienteModel;
    private DefaultListModel<Ordemservico> listModelOs;
    private DefaultListModel listModelAparelho;
    private Cliente cliente;
    private Aparelho aparelho;
    private Ordemservico os;
    private boolean newOs = false;
    private boolean newAparelho = true;

    /**
     * Creates new form CadOsJInternalFrame
     */
    public CadOsJInternalFrameSwap(String nome) {
        super("Cadastro de Ordem de Serviço");

        listModelOs = new DefaultListModel();

        OrdemServicoJpaController osJPA = new OrdemServicoJpaController();

        for (Ordemservico os : osJPA.findByCliente(nome)) {
            listModelOs.addElement(os);
        }
        comboClienteModel = new DefaultComboBoxModel<Cliente>();
        comboClienteModel.addElement("                         " + " - " + 0);

        ClienteJpaController jpaCliente = new ClienteJpaController();
        List<Cliente> listCliente = jpaCliente.findAll();
        for (Cliente c : listCliente) {
            comboClienteModel.addElement(c.getNome() + " - " + c.getId());
        }

        listModelAparelho = new DefaultListModel<Aparelho>();
        initComponents();
        LimitadorMoeda limitador = new LimitadorMoeda();
        jTextValorOS.setDocument(limitador);
        LimitadorMoeda limitador2 = new LimitadorMoeda();
        jTextAparelhoPreco.setDocument(limitador2);
        //LimitadorMoeda.i = 0;
        //jTextValorOS.setText("0,00");

    }

    public CadOsJInternalFrameSwap(Integer id) {
        super("Cadastro de Ordem de Serviço");

        listModelOs = new DefaultListModel();

        OrdemServicoJpaController osJPA = new OrdemServicoJpaController();

        os = osJPA.findByContOS(id);
        listModelOs.addElement(os);

        comboClienteModel = new DefaultComboBoxModel<Cliente>();
        comboClienteModel.addElement("                         " + " - " + 0);

        ClienteJpaController jpaCliente = new ClienteJpaController();
        List<Cliente> listCliente = jpaCliente.findAll();
        for (Cliente c : listCliente) {
            comboClienteModel.addElement(c.getNome() + " - " + c.getId());
        }

        listModelAparelho = new DefaultListModel<Aparelho>();
        initComponents();

        if (SessionDesktop.getFlagAtzCadOS() == 1) {

            SessionDesktop.setFlagAtzCadOS(0);
        }
        LimitadorMoeda limitador = new LimitadorMoeda();
        jTextValorOS.setDocument(limitador);
        //LimitadorMoeda.i = 0;
        //jTextValorOS.setText("0,00");
        LimitadorMoeda limitador2 = new LimitadorMoeda();
        jTextAparelhoPreco.setDocument(limitador2);
        jListOs.setSelectedIndex(0);
    }

    public CadOsJInternalFrameSwap(List<Ordemservico> listOS) {
        super("Cadastro de Ordem de Serviço");

        listModelOs = new DefaultListModel();

        for (Ordemservico os : listOS) {
            listModelOs.addElement(os);
        }
        comboClienteModel = new DefaultComboBoxModel<Cliente>();
        comboClienteModel.addElement("                         " + " - " + 0);

        listModelAparelho = new DefaultListModel<Aparelho>();
        initComponents();
        LimitadorMoeda limitador = new LimitadorMoeda();
        jTextValorOS.setDocument(limitador);
        //LimitadorMoeda.i = 0;
        //jTextValorOS.setText("0,00");
        LimitadorMoeda limitador2 = new LimitadorMoeda();
        jTextAparelhoPreco.setDocument(limitador2);
    }

    public CadOsJInternalFrameSwap() {
        super("Cadastro de Ordem de Serviço");

        if (SessionDesktop.getFlagCliente() == 0) {
            listModelOs = new DefaultListModel();

            OrdemServicoJpaController jpaOs = new OrdemServicoJpaController();
            for (Ordemservico os : jpaOs.findAllPaginatorByIdNative()) {
                listModelOs.addElement(os);
            }

            listModelAparelho = new DefaultListModel<Aparelho>();
            initComponents();
            LimitadorMoeda limitador = new LimitadorMoeda();
            jTextValorOS.setDocument(limitador);
            //LimitadorMoeda.i = 0;
            //jTextValorOS.setText("0,00");
            LimitadorMoeda limitador2 = new LimitadorMoeda();
            jTextAparelhoPreco.setDocument(limitador2);
        } else {
            listModelOs = new DefaultListModel();

            ClienteJpaController jpaCliente = new ClienteJpaController();
            cliente = SessionDesktop.getCliente();

            listModelAparelho = new DefaultListModel<Aparelho>();
            initComponents();

            newOs = true;

            listModelAparelho = new DefaultListModel<Aparelho>();
            jListAparelho.setModel(listModelAparelho);
            os = new Ordemservico();

            if (cliente != null) {

                jTextNomeCliente.setText(cliente.getNome());
                jTextRg.setText(cliente.getRg());
                jTextTelefone.setText(cliente.getTelefone());
                jTextEndereco.setText(cliente.getEndereco());
                jTextCpf.setText(cliente.getCpf());
                jTextCelular.setText(cliente.getCelular());
            }

            LimitadorMoeda limitador = new LimitadorMoeda();
            jTextValorOS.setDocument(limitador);
            LimitadorMoeda limitador2 = new LimitadorMoeda();
            jTextAparelhoPreco.setDocument(limitador2);
            SessionDesktop.setFlagCliente(0);
            if (cliente.getCidadeId() != null) {
                jTextCidade.setText(cliente.getCidadeId().getNome());
            }

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jListOs = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextRg = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextCpf = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextTelefone = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextCelular = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextEndereco = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListAparelho = new javax.swing.JList();
        jLabel8 = new javax.swing.JLabel();
        jTextModeloAparelho = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextSerialAparelho = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextMarcaAparelho = new javax.swing.JTextField();
        jTextDescricaoAparelho = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextUsuarioCadastrou = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextFinalizado = new javax.swing.JTextField();
        jCheckBoxAutorizado = new javax.swing.JCheckBox();
        jCheckPronto = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextObs = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        jButtonOsSalvar = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextDateEntrada = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextDataSaida = new javax.swing.JTextField();
        jButtonNovoAparelho = new javax.swing.JButton();
        jButtonSalvarAp = new javax.swing.JButton();
        jButtonDeleteAparelho = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jTextValorOS = new javax.swing.JTextField();
        jTextShowVlrOS = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jButtonImprimir = new javax.swing.JButton();
        jTextCidade = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jTextAparelhoPreco = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextAparelhoDefeito = new javax.swing.JTextField();
        jButtonPecas = new javax.swing.JButton();
        jTextNomeCliente = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jCheckBoxsConserto = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        jListOs.setModel(listModelOs);
        jListOs.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListOsValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jListOs);

        jLabel1.setText("Cliente");

        jLabel2.setText("RG");

        jLabel3.setText("CPF");

        jLabel4.setText("Telefone :");

        jTextTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextTelefoneActionPerformed(evt);
            }
        });

        jLabel6.setText("Celular");

        jLabel7.setText("Endereço");

        jListAparelho.setModel(listModelAparelho
        );
        jListAparelho.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListAparelhoValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jListAparelho);

        jLabel8.setText("Modelo");

        jLabel9.setText("Serial");

        jLabel10.setText("Marca");

        jLabel11.setText("Aparelho");

        jLabel13.setText("Cadastrado por");

        jTextUsuarioCadastrou.setEditable(false);

        jLabel14.setText("Finalizado");

        jTextFinalizado.setEditable(false);

        jCheckBoxAutorizado.setText("Autorizado");

        jCheckPronto.setText("Pronto");
        jCheckPronto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckProntoActionPerformed(evt);
            }
        });

        jLabel15.setText("Total do orçamento");

        jTextObs.setColumns(20);
        jTextObs.setRows(5);
        jScrollPane4.setViewportView(jTextObs);

        jLabel17.setText("obs:");

        jButtonOsSalvar.setText("Salvar");
        jButtonOsSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOsSalvarActionPerformed(evt);
            }
        });

        jButtonDelete.setText("Delete");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jLabel16.setText("Cidade");

        jLabel5.setText("Data Entrada");

        jTextDateEntrada.setEnabled(false);
        jTextDateEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextDateEntradaActionPerformed(evt);
            }
        });

        jLabel18.setText("Data Saida");

        jTextDataSaida.setEnabled(false);

        jButtonNovoAparelho.setText("Novo");
        jButtonNovoAparelho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoAparelhoActionPerformed(evt);
            }
        });

        jButtonSalvarAp.setText("Salvar");
        jButtonSalvarAp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarApActionPerformed(evt);
            }
        });

        jButtonDeleteAparelho.setText("Delete");
        jButtonDeleteAparelho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteAparelhoActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 0, 0));
        jLabel19.setText("O.S");

        jTextValorOS.setEditable(false);

        jTextShowVlrOS.setForeground(new java.awt.Color(255, 0, 0));
        jTextShowVlrOS.setEnabled(false);

        jLabel20.setText("Total em peças");

        jButtonImprimir.setText("Imprimir");
        jButtonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirActionPerformed(evt);
            }
        });

        jButton1.setText("Garantia?");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel21.setText("Preço");

        jTextAparelhoPreco.setEditable(false);

        jLabel22.setText("Defeito");

        jButtonPecas.setText("peças");
        jButtonPecas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPecasActionPerformed(evt);
            }
        });

        jButton5.setText("Cliente");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel23.setText("Código do Aparelho");

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 51));
        jLabel12.setText("Dados do aparelho a serem preenchidos :");

        jCheckBoxsConserto.setText("s/Conserto");
        jCheckBoxsConserto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxsConsertoActionPerformed(evt);
            }
        });

        jButton2.setLabel("set saída");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Novo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(21, 21, 21)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 65, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(jLabel19)))
                .add(36, 36, 36)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel23)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel1)
                                    .add(jLabel2)
                                    .add(jLabel4)
                                    .add(jLabel7))
                                .add(36, 36, 36)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(layout.createSequentialGroup()
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                            .add(jTextTelefone, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                                            .add(jTextEndereco)
                                            .add(jTextRg))
                                        .add(18, 18, 18)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(jLabel6)
                                            .add(jLabel3)
                                            .add(jLabel16))
                                        .add(32, 32, 32)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextCidade, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 128, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextCelular, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 128, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextCpf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 127, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                    .add(jTextNomeCliente))))
                        .add(0, 0, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 65, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(jCheckBoxAutorizado)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(jCheckPronto)
                                .add(18, 18, Short.MAX_VALUE)
                                .add(jButton1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jButtonDeleteAparelho)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jButtonNovoAparelho, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 71, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jButtonSalvarAp))
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                .add(jLabel8)
                                                .add(jLabel21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 44, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel5))
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                            .add(layout.createSequentialGroup()
                                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                                    .add(jTextDateEntrada)
                                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextAparelhoPreco)
                                                    .add(jTextModeloAparelho)
                                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextMarcaAparelho, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                    .add(jLabel11)
                                                    .add(jLabel9)
                                                    .add(jLabel22)
                                                    .add(jLabel18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                                .add(25, 25, 25)
                                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                                    .add(jTextAparelhoDefeito, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                                                    .add(jTextDescricaoAparelho)
                                                    .add(jTextSerialAparelho)
                                                    .add(jTextDataSaida)))
                                            .add(jLabel12, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)))
                                    .add(layout.createSequentialGroup()
                                        .add(jCheckBoxsConserto)
                                        .add(18, 18, 18)
                                        .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 412, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                .add(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jButton3)
                .add(1, 1, 1)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jButtonImprimir)
                            .add(jButton2))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jButtonPecas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 84, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jButton5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 84, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jButtonDelete, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jButtonOsSalvar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .add(98, 98, 98)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                .add(jLabel20)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTextShowVlrOS, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 106, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                .add(jLabel15)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTextValorOS, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 106, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                    .add(layout.createSequentialGroup()
                        .add(21, 21, 21)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(layout.createSequentialGroup()
                                .add(jLabel14)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(jTextFinalizado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 112, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(layout.createSequentialGroup()
                                .add(jLabel13)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(jTextUsuarioCadastrou, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 112, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(30, 30, 30)
                        .add(jLabel17)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 255, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jLabel19)
                .add(3, 3, 3)
                .add(jScrollPane1)
                .add(105, 105, 105))
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel1)
                            .add(jTextNomeCliente, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel2)
                            .add(jTextRg, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel3)
                            .add(jTextCpf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel4)
                            .add(jTextTelefone, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel6)
                            .add(jTextCelular, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel16)
                            .add(jTextCidade, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jTextEndereco, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel7))))
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(50, 50, 50)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jTextDataSaida, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel18)
                            .add(jTextDateEntrada, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel5))
                        .add(3, 3, 3)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel8)
                            .add(jTextModeloAparelho, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel9)
                            .add(jTextSerialAparelho, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jTextDescricaoAparelho, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel11)
                            .add(jTextMarcaAparelho, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel10))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jTextAparelhoPreco, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel21)
                            .add(jLabel22)
                            .add(jTextAparelhoDefeito, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jCheckBoxAutorizado)
                            .add(jCheckPronto)
                            .add(jButton1)
                            .add(jButtonDeleteAparelho)
                            .add(jButtonNovoAparelho)
                            .add(jButtonSalvarAp))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jCheckBoxsConserto)
                            .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(layout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel23)
                            .add(jLabel12))
                        .add(11, 11, 11)
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 201, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(6, 6, 6)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabel13)
                                    .add(jTextUsuarioCadastrou, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabel14)
                                    .add(jTextFinalizado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(jLabel17))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jButtonOsSalvar)
                            .add(jButtonImprimir)
                            .add(jButton5)
                            .add(jButton3))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jButtonPecas)
                            .add(jButtonDelete)
                            .add(jButton2)))
                    .add(layout.createSequentialGroup()
                        .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 93, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel20)
                            .add(jTextShowVlrOS, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jTextValorOS, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel15))
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jButton2.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextTelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextTelefoneActionPerformed

    private void jTextDateEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextDateEntradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextDateEntradaActionPerformed

    private void jButtonSalvarApActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarApActionPerformed
        // TODO add your handling code here
        boolean errorMarcaAp;

        if (!newAparelho) {

            if (aparelho != null) {

                aparelho.setMarca(jTextMarcaAparelho.getText());
                aparelho.setModelo(jTextModeloAparelho.getText());
                aparelho.setSerial(jTextSerialAparelho.getText());
                aparelho.setDescricao(jTextDescricaoAparelho.getText());
                aparelho.setDefeito(jTextAparelhoDefeito.getText());

                if (os.getUsuarioEntregouid() != null) {
                    aparelho.setUsuarioEntregador(os.getUsuarioEntregouid().getNome());
                }
                if (os.getUsuarioRecebeuid() != null) {
                    aparelho.setUsuarioRecebedor(os.getUsuarioRecebeuid().getNome());
                }
                AparelhoJpaController aparelhoJpa = new AparelhoJpaController();

                if (jCheckBoxAutorizado.isSelected()) {
                    aparelho.setAutorizado(1);
                } else {
                    aparelho.setAutorizado(0);
                }

                if (jCheckPronto.isSelected()) {
                    aparelho.setPronto(1);
                } else {
                    aparelho.setPronto(0);
                }
                if (jCheckBoxsConserto.isSelected()) {
                    aparelho.setSemConserto("sim");
                } else {
                    aparelho.setSemConserto("não");
                }
//                if (!jTextAparelhoPreco.getText().equals("")) {
//                    System.out.println("parse = " + jTextAparelhoPreco.getText());
//
//                    String str = jTextAparelhoPreco.getText().replace(".", "");
//
//                    Double vlrOrcamento = Double.parseDouble(str.replace(",", "."));
//                    aparelho.setPreco(vlrOrcamento);
//                }
                aparelho.setContOs(os.getContOs());
                aparelhoJpa.edit(aparelho);
                aparelhoJpa = null;

                JOptionPane.showMessageDialog(null, "Aparelho editado com sucesso!");

            } else {
                JOptionPane.showMessageDialog(null, "Um aparelhocprecisa ser selecionado!");

            }

        } else {
//novo aparelho

            Aparelho a = new Aparelho();
            a.setUsuarioRecebedor(SessionDesktop.getUsuario().getNome());

            if (os != null) {
                a.setContOs(os.getContOs());
            } else {
                Cliente umCliente = new Cliente();
                umCliente.setNome(jTextNomeCliente.getText());
                umCliente.setCelular(jTextCelular.getText());
                umCliente.setEndereco(jTextEndereco.getText());
                umCliente.setCpf(jTextCpf.getText());
                umCliente.setTelefone(jTextTelefone.getText());
                umCliente.setRg(jTextRg.getText());

                ClienteJpaController ctlCliente = new ClienteJpaController();
                ctlCliente.create(umCliente);
                
                cliente = umCliente;
                aparelho = new Aparelho();

            }
            if (jTextMarcaAparelho.getText().equals("")) {
                errorMarcaAp = true;
                jTextMarcaAparelho.setBackground(Color.RED);
            } else {
                errorMarcaAp = false;
                jTextMarcaAparelho.setBackground(Color.WHITE);
                a.setMarca(jTextMarcaAparelho.getText());
                a.setModelo(jTextModeloAparelho.getText());
                a.setSerial(jTextSerialAparelho.getText());
                a.setDescricao(jTextDescricaoAparelho.getText());
                a.setDefeito(jTextAparelhoDefeito.getText());
                if (jCheckBoxAutorizado.isSelected()) {
                    a.setAutorizado(1);
                } else {
                    a.setAutorizado(0);
                }

                if (jCheckPronto.isSelected()) {
                    a.setPronto(1);
                } else {
                    a.setPronto(0);
                }
            }

            if (!errorMarcaAp) {
                JOptionPane.showMessageDialog(null, "Aparelho cadastrado com sucesso!");
                AparelhoJpaController aparelhoJpa = new AparelhoJpaController();

                if (aparelho == null) {
                    aparelho = new Aparelho();
                }
                aparelho.setDataEntrada(new Date());
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                jTextDateEntrada.setText(df.format(aparelho.getDataEntrada()));

                a.setDataEntrada(new Date());

                aparelhoJpa.create(a);

                if (os == null) {
                    os = new Ordemservico();
                    os.setClienteId(cliente);
                    System.out.println("AQUI new OS");
                    newOs = true;
                } else if (os.getListaAparelho() == null) {
                    List<Aparelho> listAp = new ArrayList<Aparelho>();
                    os.setListaAparelho(listAp);
                    os.getListaAparelho().add(a);
                } else {
                    os.getListaAparelho().add(a);
                }

                listModelAparelho.addElement(a.getId());

                System.out.println("listModelAparelho = " + listModelAparelho.size());
                if (listModelAparelho.size() == 1) {
                    newAparelho = false;
                    jButtonOsSalvar.doClick();

                    if (os.getUsuarioEntregouid() != null) {
                        a.setUsuarioEntregador(os.getUsuarioEntregouid().getNome());
                    }
                    if (os.getUsuarioRecebeuid() != null) {
                        a.setUsuarioRecebedor(os.getUsuarioRecebeuid().getNome());
                    }
                    AparelhoJpaController jpa = new AparelhoJpaController();
                    a.setContOs(os.getContOs());
                    jpa.edit(a);

                    AparelhoJpaController jpa1 = new AparelhoJpaController();
                    Integer i = jpa1.findLastRegister().getContador() + 1;
                    a.setContador(i);
                    AparelhoJpaController jpa2 = new AparelhoJpaController();

                    jpa2.edit(a);

                } else if (os == null) {
                    System.out.println("insere....");

                }
            } else {
                JOptionPane.showMessageDialog(null, "É preciso preencher ao menos o campo marca de aparelho!");
            }
        }
    }//GEN-LAST:event_jButtonSalvarApActionPerformed

    private void jButtonOsSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOsSalvarActionPerformed
        // TODO add your handling code here:
        if (newOs) {

            if (cliente == null) {
                cliente = new Cliente();
            }
            if (os == null) {
                os = new Ordemservico();
            }

            cliente.setCpf(jTextCpf.getText());
            cliente.setRg(jTextRg.getText());
            cliente.setTelefone(jTextTelefone.getText());
            cliente.setCelular(jTextCelular.getText());
            cliente.setEndereco(jTextEndereco.getText());
            cliente.setNome(jTextNomeCliente.getText());

            if (jTextObs.getText() != null) {
                os.setObs(jTextObs.getText());
            }
            os.setDataEntrada(new Date());
            if (!jTextValorOS.getText().equals("")) {
                Double vlrOrcamento = Double.parseDouble(jTextValorOS.getText().replace(",", "."));
                os.setTotalOrcamento(vlrOrcamento);
            }

            if (!jTextNomeCliente.getText().equals("")) {

                ClienteJpaController clienteJpa = new ClienteJpaController();
                clienteJpa.create(cliente);

                os.setClienteId(cliente);

                os.setUsuarioRecebeuid(SessionDesktop.getUsuario());

                OrdemServicoJpaController osJpa = new OrdemServicoJpaController();

                Integer i = osJpa.findLastRegister().getContOs();
                os.setContOs(i + 1);

                OrdemServicoJpaController osJpa2Insere = new OrdemServicoJpaController();

                System.out.println("os   " + os);
                System.out.println(">>>>>>>>> aquiii");
                System.out.println("os.getAparelhos" + os.getListaAparelho());
                osJpa2Insere.create(os);
                newOs = false;
                listModelOs.add(0, os);
                jListOs.setModel(listModelOs);

                JOptionPane.showMessageDialog(null, "Os inserida com sucesso!");
                jTextNomeCliente.setBackground(Color.WHITE);
            } else {

                jTextNomeCliente.setBackground(Color.YELLOW);
                JOptionPane.showMessageDialog(null, "É preciso pelo menos preencher o nome do cliente!");

            }
        } else {
            if (cliente == null) {
                JOptionPane.showMessageDialog(null, "Clique no botão Novo para adicionar uma nova OS!");
            } else {
                cliente.setCpf(jTextCpf.getText());
                cliente.setRg(jTextRg.getText());
                cliente.setTelefone(jTextTelefone.getText());
                cliente.setCelular(jTextCelular.getText());
                cliente.setEndereco(jTextEndereco.getText());
                cliente.setNome(jTextNomeCliente.getText());
                os.setClienteId(cliente);

                ClienteJpaController clienteJpa = new ClienteJpaController();
                clienteJpa.edit(cliente);

//            if (!jTextValorOS.getText().equals("")) {
//                Double vlrOrcamento = Double.parseDouble(jTextValorOS.getText().replace(",", "."));
//                os.setTotalOrcamento(vlrOrcamento);
//            }
                OrdemServicoJpaController osJpa = new OrdemServicoJpaController();
                os.setUsuarioRecebeuid(SessionDesktop.getUsuario());
                os.setObs(jTextObs.getText());

                osJpa.edit(os);
                JOptionPane.showMessageDialog(null, "OS " + os.getContOs() + " editada com sucesso!");
            }
        }

    }//GEN-LAST:event_jButtonOsSalvarActionPerformed

    private void jListOsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListOsValueChanged
        // TODO add your handling code here
        //OrdemServico os = DAOFactory.getOrdemServicoRepositorio().findOsJoinCliente(new Integer(jListOs.getSelectedValue().toString()));
        newOs = false;
        jCheckBoxAutorizado.setSelected(false);
        jCheckPronto.setSelected(false);
        jCheckBoxsConserto.setSelected(false);
        aparelho = null;
        LimitadorMoeda.i = 0;
        jTextAparelhoPreco.setText("");
        jTextAparelhoDefeito.setText("");
        jTextUsuarioCadastrou.setText("");

        OrdemServicoJpaController osJpa = new OrdemServicoJpaController();

        //os = osJpa.findById(new Integer(jListOs.getSelectedValue().toString()));
        if (jListOs.getSelectedValue() != null) {

            os = listModelOs.get(jListOs.getSelectedIndex());

            //se for o ultimoRegistro
            if (os.equals(listModelOs.lastElement())) {
                OrdemServicoJpaController osJpaSwap = new OrdemServicoJpaController();

                List<Ordemservico> list = osJpaSwap.findAllPaginatorByIdNative(os.getContOs());
                for (Ordemservico os : list) {
                    listModelOs.addElement(os);
                }
                jListOs.setModel(listModelOs);
            }

            if (os != null) {

                jTextObs.setText(os.getObs());
                if (os.getUsuarioRecebeuid() != null) {
                    jTextUsuarioCadastrou.setText(os.getUsuarioRecebeuid().getNome());
                } else {
                    jTextUsuarioCadastrou.setText("");
                }
                if (os.getUsuarioEntregouid() != null) {
                    jTextFinalizado.setText(os.getUsuarioEntregouid().getNome());
                } else {
                    jTextFinalizado.setText("");
                }

                jTextObs.setText(os.getObs());
                if (os.getDataEntrada() != null) {
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    jTextDateEntrada.setText("");
                } else {
                    jTextDateEntrada.setText("");
                }
                if (os.getDataSaida() != null) {

                    jTextDataSaida.setText("");
                } else {
                    jTextDataSaida.setText("");
                }
                cliente = os.getClienteId();

                jTextNomeCliente.setText(cliente.getNome());
                jTextRg.setText(cliente.getRg());
                jTextTelefone.setText(cliente.getTelefone());
                jTextEndereco.setText(cliente.getEndereco());
                jTextCpf.setText(cliente.getCpf());
                jTextCelular.setText(cliente.getCelular());

                if (cliente.getCidadeId() != null) {
                    jTextCidade.setText(cliente.getCidadeId().getNome());
                } else {
                    jTextCidade.setText("NULL");
                }

                AparelhoJpaController aparelhoJpaController = new AparelhoJpaController();
                List<Aparelho> listAparelhoTemp = aparelhoJpaController.nativeFindAparelhosByIdOS(os.getContOs());

                listModelAparelho = new DefaultListModel();
                for (Aparelho aparelho : listAparelhoTemp) {
                    listModelAparelho.addElement(aparelho);
                }
                jListAparelho.setModel(listModelAparelho);

                cleanFieldsAparelho();

                if (listModelAparelho.size() >= 1) {
                    jListAparelho.setSelectedIndex(0);
                }
                DecimalFormat df = new DecimalFormat("$,##0.00");

                System.out.println("os.getTotalOrcamento = " + os.getTotalOrcamento());

                AparelhoJpaController aparelhoJpaCtl = new AparelhoJpaController();
                List<Aparelho> listAparelho1 = aparelhoJpaCtl.nativeFindAparelhosByIdOS(os.getContOs());

                double valorPecas = 0;

                for (Aparelho ap1 : listAparelho1) {
                    PecasJpaController pecasCtl = new PecasJpaController();

                    List<Peca> listPecas = pecasCtl.findByAparelho(ap1.getContador());
                    if (listPecas != null) {
                        for (Peca p : listPecas) {
                            valorPecas = valorPecas + p.getValor();
                        }
                    }

                }

                if (os.getTotalOrcamento() == null) {
                    os.setTotalOrcamento(valorPecas);
                } else if (os.getTotalOrcamento() < valorPecas) {
                    OrdemServicoJpaController osJpa3 = new OrdemServicoJpaController();
                    os.setTotalOrcamento(valorPecas);
                    osJpa3.edit(os);
                }
                jTextShowVlrOS.setText(df.format(os.getTotalOrcamento()));

            }
        }
    }//GEN-LAST:event_jListOsValueChanged

    private void jListAparelhoValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListAparelhoValueChanged
        // TODO add your handling code here:
        newAparelho = false;
        AparelhoJpaController aparelhoJpa = new AparelhoJpaController();

        if (jListAparelho.getSelectedValue() != null) {
            aparelho = aparelhoJpa.findById(
                    new Integer(jListAparelho.getSelectedValue().toString().replace(" ", "")));
        }
        if (aparelho != null) {
            jTextMarcaAparelho.setText(aparelho.getMarca());
            jTextModeloAparelho.setText(aparelho.getModelo());
            jTextSerialAparelho.setText(aparelho.getSerial());
            jTextDescricaoAparelho.setText(aparelho.getDescricao());
            jTextUsuarioCadastrou.setText(aparelho.getUsuarioRecebedor());
            jTextFinalizado.setText(aparelho.getUsuarioEntregador());
        }

        if (aparelho != null) {
            if (aparelho.getAutorizado() == null) {
                jCheckBoxAutorizado.setSelected(false);
            } else {
                if (aparelho.getAutorizado() == 1) {
                    jCheckBoxAutorizado.setSelected(true);
                } else if (aparelho.getAutorizado() == 0) {
                    jCheckBoxAutorizado.setSelected(false);
                }
            }
        }

        if (aparelho != null) {
            if (aparelho.getPronto() == null) {
                jCheckPronto.setSelected(false);
            } else {
                if (aparelho.getPronto() == 1) {
                    jCheckPronto.setSelected(true);
                } else if (aparelho.getPronto() == 0) {
                    jCheckPronto.setSelected(false);
                }
            }
        }

        if (aparelho != null) {
            if (aparelho.getSemConserto() == null) {
                jCheckPronto.setSelected(false);
            } else {
                if (aparelho.getSemConserto().equalsIgnoreCase("sim")) {
                    jCheckBoxsConserto.setSelected(true);
                }
            }
        }

        if (aparelho != null) {
            if (aparelho.getPreco() != null) {
                String valorOrcamento = aparelho.getPreco().toString().replace(".", ",");

                String[] vlr = valorOrcamento.split(",");
                if (vlr[vlr.length - 1].length() == 1) {
                    valorOrcamento = valorOrcamento + "0";
                }
                LimitadorMoeda.i = 0;

                jTextAparelhoPreco.setText(valorOrcamento);
                LimitadorMoeda.i = 0;

            } else {
                jTextAparelhoPreco.setText("");
                LimitadorMoeda.i = 0;
                jTextAparelhoPreco.setText("");
                jTextAparelhoPreco.setText("0,00");
            }
        }

        if (aparelho != null) {
            if (aparelho.getDefeito() != null) {
                jTextAparelhoDefeito.setText(aparelho.getDefeito());
            } else {
                jTextAparelhoDefeito.setText("");
            }
        }

        if (aparelho != null) {
            if (aparelho.getDataEntrada() != null) {
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                jTextDateEntrada.setText(df.format(aparelho.getDataEntrada()));
            }
        }

        if (aparelho != null) {
            if (aparelho.getDataSaida() != null) {
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                jTextDataSaida.setText(df.format(aparelho.getDataSaida()));
            } else {
                jTextDataSaida.setText("");
            }
        }

        if (aparelho != null) {
            PecasJpaController pecasCtl = new PecasJpaController();
            List<Peca> listPecas = pecasCtl.findByAparelho(aparelho.getContador());
            double valorPecas = 0;
            for (Peca p : listPecas) {
                valorPecas = valorPecas + p.getValor();
                System.out.println("p  " + p + "  valor = " + p.getValor());
            }
            System.out.println("FIM");
            LimitadorMoeda.i = 0;

            DecimalFormat df = new DecimalFormat("$,##0.00");

            jTextAparelhoPreco.setText(df.format(valorPecas));
        }
    }//GEN-LAST:event_jListAparelhoValueChanged

    private void jButtonNovoAparelhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoAparelhoActionPerformed
        newAparelho = true;
        cleanFieldsAparelho();
        jTextDataSaida.setText("");
        jTextFinalizado.setText("");

        if (os != null) {
            if (newOs) {
                JOptionPane.showMessageDialog(null, "Insira o aparelho que sera vinculado a OS");
            } else {
                if (os.getContOs() != null) {
                    JOptionPane.showMessageDialog(null, "Insira o aparelho que sera vinculado a OS " + os.getContOs());
                }
            }
        }
    }//GEN-LAST:event_jButtonNovoAparelhoActionPerformed

    public void cleanFieldsAparelho() {
        jTextMarcaAparelho.setText("");
        jTextModeloAparelho.setText("");
        jTextSerialAparelho.setText("");
        jTextDescricaoAparelho.setText("");
        jTextAparelhoDefeito.setText("");
        jTextDateEntrada.setText("");
        jTextDataSaida.setText("");
        jTextAparelhoPreco.setText("");
    }

    public void cleanFieldsCliente() {
        jTextNomeCliente.setText("");
        jTextRg.setText("");
        jTextTelefone.setText("");
        jTextEndereco.setText("");
        jTextCpf.setText("");
        jTextCelular.setText("");
        jTextDataSaida.setText("");
        jTextDateEntrada.setText("");
        jTextObs.setText("");
    }


    private void jButtonDeleteAparelhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteAparelhoActionPerformed
        if (jListAparelho.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(null, "É preciso selecionar um aparelho para deletalo!");
        } else {
            os.getListaAparelho().remove(aparelho);
            listModelAparelho.remove(new Integer(jListAparelho.getSelectedIndex()));

            jListAparelho.setModel(listModelAparelho);
        }

    }//GEN-LAST:event_jButtonDeleteAparelhoActionPerformed

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed

        try {
            if (aparelho != null) {
                if (aparelho.getDataSaida() == null) {

                    os.setUsuarioEntregouid(SessionDesktop.getUsuario());

                    AparelhoJpaController osJpa = new AparelhoJpaController();

                    if (aparelho.getImpresso() == null) {
                        aparelho.setImpresso(1);
                        System.out.println("ainda nao imprime");
                    } else {
                        System.out.println("imprime");
                        aparelho.setDataSaida(new Date());
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        jTextDataSaida.setText(df.format(aparelho.getDataSaida()));

                    }
                    osJpa.edit(aparelho);
                }
            } else {
                JOptionPane.showMessageDialog(null, "É preciso selecionar um aparelho");
            }

            // TODO add your handling code here:
            Relatorio relatorio = new Relatorio();
            relatorio.geraRelatorio(os.getContOs());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CadOsJInternalFrameSwap.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        // TODO add your handling code here:
        if (os != null) {
            if (JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja deletar a  OS ?", "WARNING",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                if (os != null) {

                    if (os.getId() != null) {
                        OrdemServicoJpaController osJpa = new OrdemServicoJpaController();
                        osJpa.delete(os);
                        listModelOs.removeElement(os);
                        jListOs.setModel(listModelOs);
                        JOptionPane.showMessageDialog(null, "Os removida com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "É preciso salvar OS para poder remove-la");

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "É preciso salvar OS para poder remove-la");

                }
            } else {

            }
        } else {
            JOptionPane.showMessageDialog(null, "É preciso salvar OS para poder remove-la");
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jCheckProntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckProntoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckProntoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        if (jListAparelho.getSelectedValue() != null) {

            if (aparelho.getDataSaida() != null) {
                SessionDesktop.setOs(os);
                SessionDesktop.setAparelho(aparelho);
                CadGarantiaJInternalFrame cadGarantia = new CadGarantiaJInternalFrame();
                Desktop.getDesktopPane().add(cadGarantia);
                cadGarantia.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "É necessário o aparelho ter uma data de saída!");
            }

        } else {
            JOptionPane.showMessageDialog(null, "É preciso ter um aparelho selecionado!");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonPecasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPecasActionPerformed
        // TODO add your handling code here:
        if (jListAparelho.getSelectedValue() != null) {
            SessionDesktop.setAparelho(aparelho);
            SessionDesktop.setOs1(os);
            CadPecasJInternalFrame cadPecas = new CadPecasJInternalFrame(os.getContOs().toString());
            Desktop.getDesktopPane().add(cadPecas);
            cadPecas.setVisible(true);
            cadPecas.addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
                public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                }

                public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {

                    SessionDesktop.setFlagAtzCadOS(1);
                    CadOsJInternalFrameSwap cadOs = new CadOsJInternalFrameSwap(SessionDesktop.getOs1().getContOs());
                    Desktop.getDesktopPane().add(cadOs);
                    cadOs.setVisible(true);
                    System.out.println("Fechando a janela");

                }

                public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                }

                public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
                }

                public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
                }

                public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
                }

                public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                }
            });

            this.dispose();

        } else {
            JOptionPane.showMessageDialog(null, "É preciso ter um aparelho selecionado!");
        }

    }//GEN-LAST:event_jButtonPecasActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        this.dispose();

        CadClienteJInternalFrame cadCliente = new CadClienteJInternalFrame();
        Desktop.getDesktopPane().add(cadCliente);
        cadCliente.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jCheckBoxsConsertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxsConsertoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxsConsertoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (os != null) {
            int ctlAparelho = 0;

            if (listModelAparelho.size() >= 1) {

                for (int i = 0; i < listModelAparelho.size(); i++) {
                    Aparelho ap = (Aparelho) listModelAparelho.get(i);

                    if (ap.getDataSaida() == null) {
                        ap.setDataSaida(new Date());
                        AparelhoJpaController apJpaCtl = new AparelhoJpaController();
                        apJpaCtl.edit(ap);
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        jTextDataSaida.setText(df.format(ap.getDataSaida()));
                        ctlAparelho = 1;
                    } else {
                        JOptionPane.showMessageDialog(null, "já existe uma data de saída para o aparelho " + ap.getId());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Não existem aparelhos para configurar data de saída!");
            }

            if (ctlAparelho == 1) {
                JOptionPane.showMessageDialog(null, "Foi configurado a data de saída para o(s) aparelho(s)!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "A OS precisa ser salva para configurar uma data de saída para um aparelho!");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        newOs = true;

        cleanFieldsCliente();
        listModelAparelho = new DefaultListModel();
        jListAparelho.setModel(listModelAparelho);
        cleanFieldsAparelho();
        JOptionPane.showMessageDialog(null, "Preencha os campos para cadastrar uma nova OS!");
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonDeleteAparelho;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonNovoAparelho;
    private javax.swing.JButton jButtonOsSalvar;
    private javax.swing.JButton jButtonPecas;
    private javax.swing.JButton jButtonSalvarAp;
    private javax.swing.JCheckBox jCheckBoxAutorizado;
    private javax.swing.JCheckBox jCheckBoxsConserto;
    private javax.swing.JCheckBox jCheckPronto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jListAparelho;
    private javax.swing.JList jListOs;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextAparelhoDefeito;
    private javax.swing.JTextField jTextAparelhoPreco;
    private javax.swing.JTextField jTextCelular;
    private javax.swing.JTextField jTextCidade;
    private javax.swing.JTextField jTextCpf;
    private javax.swing.JTextField jTextDataSaida;
    private javax.swing.JTextField jTextDateEntrada;
    private javax.swing.JTextField jTextDescricaoAparelho;
    private javax.swing.JTextField jTextEndereco;
    private javax.swing.JTextField jTextFinalizado;
    private javax.swing.JTextField jTextMarcaAparelho;
    private javax.swing.JTextField jTextModeloAparelho;
    private javax.swing.JTextField jTextNomeCliente;
    private javax.swing.JTextArea jTextObs;
    private javax.swing.JTextField jTextRg;
    private javax.swing.JTextField jTextSerialAparelho;
    private javax.swing.JTextField jTextShowVlrOS;
    private javax.swing.JTextField jTextTelefone;
    private javax.swing.JTextField jTextUsuarioCadastrou;
    private javax.swing.JTextField jTextValorOS;
    // End of variables declaration//GEN-END:variables
}
