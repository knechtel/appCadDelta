/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.util;

/**
 *
 * @author Maiquel
 */
import javax.swing.text.*;

public class LimitadorMoeda2 extends PlainDocument {

    public static Integer i = 0;
    public static final int maxdigits = 12;

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

        if (i == 0) {
            super.insertString(offs, str, a);
        }
        i++;
        String texto = getText(0, getLength());

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isDigit(c)) {
                return;
            }
        }

        if (texto.length() < LimitadorMoeda2.maxdigits) {
            super.remove(0, getLength());
            texto = texto.replace(".", "").replace(",", "");
            StringBuffer s = new StringBuffer(texto + str);

            if (s.length() > 0 && s.charAt(0) == '0') {
                s.deleteCharAt(0);
            }

            if (s.length() < 3) {
                if (s.length() < 1) {
                    s.insert(0, "000");
                } else if (s.length() < 2) {
                    s.insert(0, "00");
                } else {
                    s.insert(0, "0");
                }
            }

            s.insert(s.length() - 2, ",");

            if (s.length() > 6) {
                s.insert(s.length() - 6, ".");
            }

            if (s.length() > 10) {
                s.insert(s.length() - 10, ".");
            }

            super.insertString(0, s.toString(), a);
        }
    }

    @Override
    public void remove(int offset, int length) throws BadLocationException {
        if (i == 0) {
            super.remove(offset, length);
        } else {
            super.remove(offset, length);
            String texto = getText(0, getLength());
            texto = texto.replace(",", "");
            texto = texto.replace(".", "");
            super.remove(0, getLength());
            insertString(0, texto, null);
        }
    }
}
