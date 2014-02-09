/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.teste;

import br.com.appCadDelta.JPAConttroller.AparelhoJpaController;
import br.com.appCadDelta.JPAConttroller.OrdemServicoJpaController;
import br.com.appCadDelta.entity.Aparelho;
import br.com.appCadDelta.entity.Ordemservico;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maiquel
 */
public class Main {
    
    public static void remove() {
        System.out.println("init()");
        OrdemServicoJpaController osJpa = new OrdemServicoJpaController();
        
        Ordemservico os = osJpa.findAparelhosByIdOS(16).get(0);
        List<Aparelho> listAparelho = new ArrayList<Aparelho>();
        os.setListaAparelho(listAparelho);        
        OrdemServicoJpaController osJpa1 = new OrdemServicoJpaController();
        osJpa1.edit(os);
        System.out.println("FIM");
    }

    public static void main(String[] args) {
        
        
    }
}
