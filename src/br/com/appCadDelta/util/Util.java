/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.util;

import br.com.appCadDelta.JPAConttroller.AparelhoJpaController;
import br.com.appCadDelta.entity.Aparelho;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.hwpf.usermodel.DateAndTime;

/**
 *
 * @author MaiquelKl
 */
public class Util {

    public static Date sringToDate(String data) throws Exception {
        if (data == null || data.equals("")) {
            return null;
        }

        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = (java.util.Date) formatter.parse(data);
        } catch (ParseException e) {
            throw e;
        }
        return date;
    }
    
    public static Double getLucroAnual(Date dataStart, Date dataEnd){
    
        
           
         AparelhoJpaController jpaCtl2 = new AparelhoJpaController();

        SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");

        double valorTotal2 = 0;

        for (Aparelho ap : jpaCtl2.findByYearDateSaida(dataStart, dataEnd)) {
            System.out.println("id" + ap.getId() + "  orcamento >> " + ap.getOrcamento() + "data saida " + format2.format(ap.getDataSaida()));
            if (ap.getOrcamento() != null) {
                valorTotal2 = valorTotal2 + ap.getOrcamento();
            }
        }
    return valorTotal2;
    }
}
