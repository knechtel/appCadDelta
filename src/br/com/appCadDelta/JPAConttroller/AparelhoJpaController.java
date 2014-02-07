/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.JPAConttroller;

import br.com.appCadDelta.entity.Aparelho;
import br.com.appCadDelta.factory.EntityManagerFactory;
import javax.persistence.EntityManager;

/**
 *
 * @author Maiquel
 */
public class AparelhoJpaController {
  
    
    public EntityManager em = null;

    public AparelhoJpaController() {
        EntityManagerFactory emf = new EntityManagerFactory();
        em = emf.getEntityManager();
    }  

    public void create(Aparelho aparelho) {
        em.getTransaction().begin();
        em.persist(aparelho);
        em.getTransaction().commit();
        em.close();
    }
    
    public Aparelho findById(Integer id) {
        try {
            return em.find(Aparelho.class, id);
        } finally {
            em.close();
        }
    }
    
    public static void main(String[] args) {
        Aparelho c  = new Aparelho();
        
        AparelhoJpaController aparelhoJpa = new AparelhoJpaController();
        aparelhoJpa.create(c);
    }  
}
