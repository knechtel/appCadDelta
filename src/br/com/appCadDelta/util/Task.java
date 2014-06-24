/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.util;

import br.com.appCadDelta.relatorio.Relatorio;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.util.Random;
import javax.swing.SwingWorker;

/**
 *
 * @author MaiquelKl
 */
public class Task extends SwingWorker<Void, Void> {

    @Override
    public Void doInBackground() {
        Random random = new Random();


        new Thread(new Runnable() {
            @Override
            public void run() {
                Relatorio relatorio = new Relatorio();
                try {

                    if (SessionDesktop.getIdRelatorio() != 0) {
                        relatorio.geraRelatorio(SessionDesktop.getIdRelatorio());
                    } else {
                        relatorio.geraRelatorio(3);
                    }
                    setProgress(100);
                    ProgressMonitorDemo.finalP = 1;
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

                System.out.println("FINAL -- ||");

            }
        }).start();



        int progress = 0;
        setProgress(0);
        try {
            while (progress < 100 && ProgressMonitorDemo.finalP == 0) {

                //Sleep for up to one second.
                Thread.sleep(random.nextInt(1000));
                //Make random progress.
                progress += random.nextInt(10);
                setProgress(Math.min(progress, 100));
            }
        } catch (InterruptedException ignore) {
        }
        return null;
    }

    @Override
    public void done() {
        Toolkit.getDefaultToolkit().beep();
        //startButton.setEnabled(true);
        SessionDesktop.getProcessMonitor().setProgress(100);
    }
}