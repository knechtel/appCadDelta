/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Maiquel
 */
public class FormataCpf extends PlainDocument {
 
        private int tamanho = 14;

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

        String texto = getText(0, getLength());
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isDigit(c)) {
                return;
            }
        }

        if (texto.length() < tamanho) {
            super.remove(0, getLength());
            StringBuilder s = new StringBuilder(texto + str);

            if (s.length() == 3) {
                s.insert(3, ".");
            } else if (s.length() == 7) {
                s.insert(7, ".");
            }else if(s.length() == 11){
                s.insert(11, "-");
            }

            super.insertString(0, s.toString(), a);
        }
    }

}
