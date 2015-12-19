/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.chart;

/**
 *
 * @author maiquelknechtel
 */
import br.com.appCadDelta.JPAConttroller.AparelhoJpaController;
import br.com.appCadDelta.JPAConttroller.OrdemServicoJpaController;
import br.com.appCadDelta.entity.Aparelho;
import br.com.appCadDelta.util.Util;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a bar chart.
 */
public class BarChartDemo1 extends JInternalFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title the frame title.
     */
    
    private static String ano=null;
    public BarChartDemo1(String ano) {

        super("Fluxo de caixa: "+ano,true,true,true,true);
        this.ano=ano;
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);

    }

    /**
     * Returns a sample dataset.
     *
     * @return The dataset.
     */
    private static CategoryDataset createDataset() {

        // row keys...
        String series1 = "Janeiro";
        String series2 = "Fevereiro";
        String series3 = "Março";
        String series4 = "Abril";
        String series5 = "Maio";
        String series6 = "Junho";
        String series7 = "Julho";
        String series8 = "Agosto";
        String series9 = "Setembro";
        String series10 = "outubro";
        String series11 = "Novembro";
        String series12 = "Dezembro";

        // column keys...
        String category1 = "Janeiro 1";
        String category2 = "Fevereiro 2";
        String category3 = "Março 3";
        String category4 = "Abril 4";
        String category5 = "Maio 5";
        String category6 = "Junho 6";
        String category7 = "Julho 7";
        String category8 = "Agosto 8";
        String category9 = "Setembro 9";
        String category10 = "outubro 10";
         String category11 = "Novembro 11";

         String category12 = "Dezembro 12";

        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

       
        try {
            Util.getLucroAnual(Util.sringToDate("01/01/"+ano), Util.sringToDate("01/02/"+ano));

            dataset.addValue(Util.getLucroAnual(Util.sringToDate("01/02/"+ano), Util.sringToDate("01/03/"+ano)), series1, category1);

        } catch (Exception ex) {
            Logger.getLogger(BarChartDemo1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            Util.getLucroAnual(Util.sringToDate("01/02/"+ano), Util.sringToDate("01/03/"+ano));

            dataset.addValue(Util.getLucroAnual(Util.sringToDate("01/02/"+ano), Util.sringToDate("01/03/"+ano)), series2, category2);

        } catch (Exception ex) {
            Logger.getLogger(BarChartDemo1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            Util.getLucroAnual(Util.sringToDate("01/02/2003"), Util.sringToDate("01/03/"+ano));

            dataset.addValue(Util.getLucroAnual(Util.sringToDate("01/02/"+ano), Util.sringToDate("01/03/"+ano)), series3, category3);

        } catch (Exception ex) {
            Logger.getLogger(BarChartDemo1.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            dataset.addValue(Util.getLucroAnual(Util.sringToDate("01/03/"+ano), Util.sringToDate("01/04/"+ano)), series4, category4);

        } catch (Exception ex) {
            Logger.getLogger(BarChartDemo1.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            dataset.addValue(Util.getLucroAnual(Util.sringToDate("01/04/"+ano), Util.sringToDate("01/05/"+ano)), series5, category5);

        } catch (Exception ex) {
            Logger.getLogger(BarChartDemo1.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            dataset.addValue(Util.getLucroAnual(Util.sringToDate("01/05/"+ano), Util.sringToDate("01/06/"+ano)), series6, category6);

        } catch (Exception ex) {
            Logger.getLogger(BarChartDemo1.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            dataset.addValue(Util.getLucroAnual(Util.sringToDate("01/06/"+ano), Util.sringToDate("01/07/"+ano)), series7, category7);

        } catch (Exception ex) {
            Logger.getLogger(BarChartDemo1.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            dataset.addValue(Util.getLucroAnual(Util.sringToDate("01/07/"+ano), Util.sringToDate("01/08/"+ano)), series8, category8);

        } catch (Exception ex) {
            Logger.getLogger(BarChartDemo1.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            dataset.addValue(Util.getLucroAnual(Util.sringToDate("01/08/"+ano), Util.sringToDate("01/09/"+ano)), series9, category9);

        } catch (Exception ex) {
            Logger.getLogger(BarChartDemo1.class.getName()).log(Level.SEVERE, null, ex);
        }

        
           try {

            dataset.addValue(Util.getLucroAnual(Util.sringToDate("01/09/"+ano), Util.sringToDate("01/10/"+ano)), series10, category10);

        } catch (Exception ex) {
            Logger.getLogger(BarChartDemo1.class.getName()).log(Level.SEVERE, null, ex);
        }

           
           try {

            dataset.addValue(Util.getLucroAnual(Util.sringToDate("01/10/"+ano), Util.sringToDate("01/11/"+ano)), series11, category11);

        } catch (Exception ex) {
            Logger.getLogger(BarChartDemo1.class.getName()).log(Level.SEVERE, null, ex);
        }

           
        
           try {

            dataset.addValue(Util.getLucroAnual(Util.sringToDate("01/11/"+ano), Util.sringToDate("01/12/"+ano)), series12, category12);

        } catch (Exception ex) {
            Logger.getLogger(BarChartDemo1.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        
        return dataset;

    }

    /**
     * Creates a sample chart.
     *
     * @param dataset the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) {

        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart(
                "Gráfico em barra do fluxo de caixa", // chart title
                "Mês do ano", // domain axis label
                "Valor em Reais", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips?
                false // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);

        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        // set up gradient paints for series...
        GradientPaint gp0 = new GradientPaint(
                0.0f, 0.0f, Color.blue,
                0.0f, 0.0f, new Color(0, 0, 64)
        );
        GradientPaint gp1 = new GradientPaint(
                0.0f, 0.0f, Color.green,
                0.0f, 0.0f, new Color(0, 64, 0)
        );
        GradientPaint gp2 = new GradientPaint(
                0.0f, 0.0f, Color.red,
                0.0f, 0.0f, new Color(64, 0, 0)
        );
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        // OPTIONAL CUSTOMISATION COMPLETED.

        return chart;

    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {

     //   BarChartDemo1 demo = new BarChartDemo1("Bar Chart Demo");
       // demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
       // demo.setVisible(true);

    }

   

}
