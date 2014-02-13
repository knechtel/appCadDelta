/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.util;

import br.com.appCadDelta.entity.Cidade;

/**
 *
 * @author Maiquel
 */
public class CidadeModel {
    
    private Cidade cidade;
    
    

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
    @Override
    public String toString(){
        return cidade.getNome().toString();
    }
    
    
    
}
