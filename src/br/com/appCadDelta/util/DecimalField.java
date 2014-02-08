/*
 * DecimalField.java
 *
 * Criado em 21 de Maio de 2007, 10:01
 */

package br.com.appCadDelta.util;

import java.awt.TextField;
import java.awt.Toolkit;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;


/**
 * JTextField personalizado permitindo somente n�meros inteiros ou decimais.
 *
 * @author Alcides Liberali
 */
public class DecimalField extends TextField {
    private Toolkit toolkit;
    private NumberFormat decimalFormatter;
    private int maxDigits;
    private int casasDecimais;
    
    
    /**
     * Constr�i um novo DecimalField com m�ximo de casas ap�s a virgula igual a
     * casasDecimais.
     *
     * @param casasDecimais  Um inteiro contendo o n�mero de casas ap�s a 
     * virgula.
     */
    public DecimalField(int casasDecimais) {
        super(4);
        toolkit = Toolkit.getDefaultToolkit();
        decimalFormatter = NumberFormat.getNumberInstance(Locale.US);
        //setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        this.maxDigits = 99;
//        this.setHorizontalAlignment(JTextField.RIGHT);
        if(casasDecimais < 1) {
            this.casasDecimais = 2;
        } else {
            this.casasDecimais = casasDecimais;
        }
    }
    
    
    /**
     * Construtor, Constr�i um novo DecimalField com tamanho m�ximo de 99 e com
     * duas casas decimais.
     */
    public DecimalField() {
        super(4);
        toolkit = Toolkit.getDefaultToolkit();
        decimalFormatter = NumberFormat.getNumberInstance(Locale.US);
        //setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        this.maxDigits = 99;
        this.casasDecimais = 2;
    //    this.setHorizontalAlignment(JTextField.RIGHT);
    }
    
    
    /**
     * Retorna o comprimento do DecimalField.
     *
     * @return  Um inteiro contendo o total de caracteres no campo.
     */
    public int getlength() {
        String fieldValue = getText();
        return (fieldValue.length());
    }
    
    
    /**
     * Retorna o valor do DecimalField.
     *
     * @return  Um double contendo o texto do campo.
     */
    public double getValue() {
        if ((getText().trim().equals("")) || getText() == null){
            return 0;
        } else {
            String valor;
            valor = this.getText();
            valor = valor.replace(",",".");
            return Double.parseDouble(valor);
        }
    }
    
    
    /**
     * Seta o valor passado no DecimalField.
     *
     * @param value  Um inteiro contendo o valor a ser escrito no campo.
     */
    public void setValue(double value) {
        setText(Double.toString(value).replace('.',','));
    }
    
    
    /**
     * Seta o n�mero m�ximo de caracteres no DecimalField.
     *
     * @param value  um inteiro contendo a capacidade m�xima do campo.
     */
    public void setMaxDigits(int value) {
        if(value > 0) {
            maxDigits = value;
        }
    }
    
    
    /**
     * Retorna o n�mero m�ximo de caracteres no DecimalField.
     *
     * @return	Um inteiro contendo o m�ximo de caracteres suportado pelo campo.
     */
    public int getMaxDigits() {
        return maxDigits;
    }
    
    
    /**
     * Limpa o JNumberField.
     */
    public void clearField() {
        setText("");
    }
    
    
    /**
     * Cria um novo documento padr�o para o campo.
     *
     * @return	Um documento novo que ser� aplicado no componente.
     */
    protected Document createDefaultModel() {
        return new WholeNumberDocument(this);
    }
    
    
    protected class WholeNumberDocument extends PlainDocument {

        DecimalField campo;
        
        public WholeNumberDocument(DecimalField campo){
            this.campo = campo;
        }
        
        /**
         * Insere o texto digitado no campo, conforme ele � digitado.
         * Caso um caracter n�o permitido seja digitado, n�o � exibido.
         *
         * @param offs	Um inteiro contendo a capacidade do campo.
         * @param str	Uma String contendo o texto a ser inserido no campo.
         * @param a	Um AttributeSet contendo os atributos.
         */
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            char[] textoInicial = str.toCharArray();
            char[] textoFinal = new char[textoInicial.length];
            char[] textoCampo = campo.getText().toCharArray();
            int j = 0;
            
            for (int i = 0; i < textoFinal.length; i++) {
                //verifica se � um caractere valido.
                if ((textoInicial[i] == '-' || textoInicial[i] == ',' || Character.isDigit(textoInicial[i]))&&(offs<(maxDigits)) && (int)textoInicial[i] != 10) {
                    
                    //verifica se o caractere � virgula
                    if (textoInicial[i] == ',') {
                        if (!((existeVirgula()) || (existeVirgulaFinal(textoFinal)))){
                            if(!((posicaoAtual() == 0) && (i == 0))){
                                if (!((posicaoAtual() == 0) && (i == 1) && (textoInicial[0] == '-'))) {
                                    if(!((posicaoAtual() == 1) && (existeMenos()) && (i == 0))) {
                                        if (i > 0){
                                            if (!((textoInicial[0] == '-') && (textoInicial[1] == ',') && (i == 1))) {
                                                textoFinal[j++] = textoInicial[i];
                                            }
                                        } else {
                                            textoFinal[j++] = textoInicial[i];
                                        }
                                    }
                                } 
                            }
                        } 
                    } else if (textoInicial[i] == '-'){
                        if((posicaoAtual() == 0) && (i == 0)){
                            textoFinal[j++] = textoInicial[i];
                        }
                    } else {
                      /*  if (!((existeVirgula()) && (posicaoVirgula()+campo.getCasasDecimais()+1) == posicaoAtual())){
                            textoFinal[j++] = textoInicial[i];
                        }*/
                        
                        if ((existeVirgula()) || (existeVirgulaFinal(textoFinal))) {
                            int posicaoLimite = posicaoVirgula()+campo.getCasasDecimais()+1;
                            int posicaoLimiteString = posicaoVirgulaFinal(textoFinal)+campo.getCasasDecimais()+1;
                            int casasAposVirgula;
                            int casasDecimaisDisponiveis;
                            if (existeVirgula()) {
                                casasAposVirgula = posicaoAtual() - posicaoVirgula();
                                casasDecimaisDisponiveis = campo.getCasasDecimais() - casasAposVirgula + 1;
                                if (!((posicaoLimite == posicaoAtual()) || (i >= casasDecimaisDisponiveis))) {
                                    textoFinal[j++] = textoInicial[i];
                                }
                            } else {
                                if ((i < posicaoLimiteString)) {
                                    textoFinal[j++] = textoInicial[i];
                                }
                            }
                        } else {
                            textoFinal[j++] = textoInicial[i];
                        }
                        
                    }
                }
            }
            super.insertString(offs, new String(textoFinal, 0, j), a);
        }
        
        public boolean existeVirgula(){
            char[] textoCampo = campo.getText().toCharArray();
            for (int i = 0; i < campo.getText().length(); i++) {
                if (textoCampo[i] == ',') {
                    return true;
                }
            }
            return false;
        }
        
        public boolean existeVirgulaFinal(char[] textoFin){
            for (int i = 0; i < textoFin.length; i++) {
                if (textoFin[i] == ',') {
                    return true;
                }
            }
            return false;
        }
        
        public boolean existeMenos(){
            char[] textoCampo = campo.getText().toCharArray();
            for (int i = 0; i < campo.getText().length(); i++) {
                if (textoCampo[i] == '-') {
                    return true;
                }
            }
            return false;
        }
        
        public int posicaoAtual(){
            return campo.getText().length();
        }
        
        public int posicaoVirgula(){
            char[] textoCampo = campo.getText().toCharArray();
            
             for (int i = 0; i < campo.getText().length(); i++) {
                 if (textoCampo[i] == ',') {
                     return i;
                 }
             }
             return 0;
        }
        
        public int posicaoVirgulaFinal(char[] textoFin){
            for (int i = 0; i < textoFin.length; i++) {
                 if (textoFin[i] == ',') {
                     return i;
                 }
             }
             return 0;
        }
    }

    public int getCasasDecimais() {
        return casasDecimais;
    }
}