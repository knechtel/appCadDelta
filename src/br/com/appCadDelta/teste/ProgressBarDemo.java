/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.teste;

/**
 *
 * @author MaiquelKl
 */

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
import br.com.appCadDelta.JPAConttroller.UsuarioJpaController;
import br.com.appCadDelta.entity.Usuario;
import br.com.appCadDelta.util.SessionDesktop;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.beans.*;
import java.util.Random;
import java.util.List;


public class ProgressBarDemo extends JPanel
        implements ActionListener,
        PropertyChangeListener {

    public List<Usuario> listUsuario;
    public Usuario usuario= new Usuario();
    private static JFrame frame;
    private JProgressBar progressBar;
    private static JButton startButton;
    private JTextArea taskOutput;
    private Task task;
    private int finalP = 0;

    class Task extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread.
         */

        @Override
        public Void doInBackground() {
            Random random = new Random();

            new Thread(new Runnable() {
                @Override
                public void run() {
                  
                    UsuarioJpaController jpaController = new UsuarioJpaController();
                    
                    
                    listUsuario = jpaController.findByLoginAndSenha(SessionDesktop.getUsuario());
                    
                    finalP = 1;

                    if (listUsuario.get(0).getPerfil() == 1) {
                        System.out.println("this.dispose()");

                        br.com.appCadDelta.Gui.Desktop desktop = new br.com.appCadDelta.Gui.Desktop();
                        desktop.setVisible(true);
                        setProgress(100);
                        frame.dispose();
                    
                    } else {
                        //usuario operador
                        setProgress(100);
                        frame.dispose();
                        br.com.appCadDelta.GuiUser.DesktopUser desktop = new br.com.appCadDelta.GuiUser.DesktopUser();
                        desktop.setVisible(true);
                        SessionDesktop.setUsuario(listUsuario.get(0));

                    }
                }
            }).start();
            int progress = 0;
            //Initialize progress property.
            setProgress(0);
            while (progress < 100) {
                //Sleep for up to one second.
//                Usuario usuario = new Usuario();
//                usuario.setLogin("vanessa");
//                usuario.setSenha("123");
//                java.util.List<Usuario> listUsuario = jpaController.findByLoginAndSenha(usuario);

                if (finalP == 0) {
                    try {
                        Thread.sleep(random.nextInt(2000));
                    } catch (InterruptedException ignore) {
                    }
                } else {
                    setProgress(100);
                    Thread.interrupted();


                }

                //   
                //Make random progress.

                progress += random.nextInt(10);
                setProgress(Math.min(progress, 100));

            }
            return null;
        }

        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {
            Toolkit.getDefaultToolkit().beep();
            startButton.setEnabled(true);
            setCursor(null); //turn off the wait cursor
            taskOutput.append("Done!\n");
        }
    }

    public ProgressBarDemo() {
        super(new BorderLayout());

        //Create the demo's UI.
        startButton = new JButton("Start");
        startButton.setActionCommand("start");
        startButton.addActionListener(this);

        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        taskOutput = new JTextArea(5, 20);
        taskOutput.setMargin(new Insets(5, 5, 5, 5));
        taskOutput.setEditable(false);

        JPanel panel = new JPanel();
        panel.add(startButton);
        panel.add(progressBar);

        add(panel, BorderLayout.PAGE_START);
        add(new JScrollPane(taskOutput), BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
    }

    /**
     * Invoked when the user presses the start button.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("chamor actionPerformed");
        startButton.setEnabled(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        //Instances of javax.swing.SwingWorker are not reusuable, so
        //we create new instances as needed.
        task = new Task();
        task.addPropertyChangeListener(this);
        task.execute();
    }

    /**
     * Invoked when task's progress property changes.
     */
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            progressBar.setValue(progress);
            taskOutput.append(String.format(
                    "Completed %d%% of task.\n", task.getProgress()));
        }
    }

    /**
     * Create the GUI and show it. As with all GUI code, this must run on the
     * event-dispatching thread.
     */
    public static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("Controle de O.S");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new ProgressBarDemo();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        frame.setResizable(false);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        startButton.doClick();
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
