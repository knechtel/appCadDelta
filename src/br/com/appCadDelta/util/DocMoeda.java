/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.util;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Maiquel
 */
public class DocMoeda extends PlainDocument{
    //variavel de controle para saber se o usuario apertou a tecla "_".
    boolean teclado = false;
    
    //variavel de controle para saber se o digito esta sendo movido para o lado.
    boolean movendo = false;
    
    //variavel de controle para saber se deve-se acrescentar a palavra no plural ou nao.
    boolean plural = false;
    
    //variavel de controle para saber se � a primeira palavra que esta sendo inserida
    //na variavel numExtenso.
    boolean primeiraPalavra  = false;
    
    JTextField jTextValor;
    public DocMoeda(JTextField jtf){
       this.jTextValor = jtf;
    
    }
    
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                char c[] = str.toCharArray();
                if (!Character.isDigit(c[0]) && str.equals("R$ ___.___.___.___.___,__") || str.equals("_")) {
                    if (!teclado) {
                        super.insertString(offs, str, a);
                        teclado = false;
                    }
                }
                
                if (Character.isDigit(c[0])) {
                    if (movendo) {
                        super.insertString(offs, str, a);
                    } else {
                        if (getText(3, 1).equals("_")) {
                            inserirDigito(offs);
                            jTextValor.setCaretPosition(offs - 1);
                            jTextValor.select(offs - 1, offs);
                            jTextValor.replaceSelection("");
                            super.insertString(offs - 1, str, a);
                            movendo = false;
                        }
                    }
                }
            }
    
    public void inserirDigito(int offs) throws BadLocationException {
        if (getText(3, 1).equals("_")) {
            movendo = true;
            for (int i = 4; i <= 25; i++) {
                if (getText(i, 1).equals("_") || getText(i, 1).equals(".") || getText(i, 1).equals(",")) {
                    jTextValor.setCaretPosition(i + 1);
                } else {
                    if (i < 25) {
                        if (i == 23 || i == 19 || i == 15 || i == 11 || i == 7) {
                            String s = getText(i, 1);
                            jTextValor.setCaretPosition(i - 2);
                            jTextValor.select(i - 2, i - 1);
                            jTextValor.replaceSelection(s);
                        } else {
                            String s = getText(i, 1);
                            jTextValor.setCaretPosition(i - 1);
                            jTextValor.select(i - 1, i);
                            jTextValor.replaceSelection(s);
                        }
                    }
                }
            }
        }
    }
    
    
}
