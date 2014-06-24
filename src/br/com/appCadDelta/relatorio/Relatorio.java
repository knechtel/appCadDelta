/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.relatorio;

import br.com.appCadDelta.GuiUser.DesktopUser;
import br.com.appCadDelta.JPAConttroller.OrdemServicoJpaController;
import br.com.appCadDelta.entity.Ordemservico;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;


import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JInternalFrame;
import net.sf.jasperreports.engine.JRException;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

public class Relatorio {

    public Relatorio() {
    }

    public void geraRelatorio(Integer id) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("D:\\ferramentas\\relatorio\\rel1.jasper");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
        OrdemServicoJpaController osJpa = new OrdemServicoJpaController();
        List<Ordemservico> listOs = osJpa.findAparelhosByIdOS(id);

        JRBeanCollectionDataSource beanCollectioinDataSource = new JRBeanCollectionDataSource(listOs);


        JasperReport jasperReport = null;
        try {
            jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);
        } catch (JRException ex) {
            Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), beanCollectioinDataSource);
        } catch (JRException ex) {
            Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        // view report to UI
//        JasperViewer.viewReport(jasperPrint, false);
        //mais nada
        JRViewer jrviewer = new JRViewer(jasperPrint);
        JInternalFrame jif = new JInternalFrame("Relatório de O.S", true, true,true);
        jif.getContentPane().add(jrviewer);
        jif.setSize(1000, 600);
        DesktopUser.getDesktopPane().add(jif);
        jif.setVisible(true);
        

    }

    public void start() {
        try {
            // load report location
            FileInputStream fis = new FileInputStream("D:\\ferramentas\\relatorio\\rel1.jasper");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);


            EntityManagerFactory emf = Persistence.createEntityManagerFactory("appDeltaCadPU", new HashMap());
            EntityManager em = emf.createEntityManager();

            Query q = em.createNamedQuery("Ordemservico.findAparelhosByIdOS").setParameter("idOs", 1);


            List<Ordemservico> listOs = (List<Ordemservico>) q.getResultList();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println("size  = " + listOs.get(0).getListaAparelho().size());


            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            JRBeanCollectionDataSource beanCollectioinDataSource = new JRBeanCollectionDataSource(listOs);


            //   JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(maps);


            // compile report
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), beanCollectioinDataSource);
            // view report to UI
            JasperViewer.viewReport(jasperPrint, false);

        } catch (FileNotFoundException | JRException e) {
            e.printStackTrace();
        }
    }

    private String getRandomString() {
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args) {
        Relatorio main = new Relatorio();
        main.start();
    }
}