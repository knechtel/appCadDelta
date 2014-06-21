/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
