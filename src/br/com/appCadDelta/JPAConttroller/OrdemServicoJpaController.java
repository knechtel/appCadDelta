/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.JPAConttroller;

import br.com.appCadDelta.entity.Ordemservico;
import br.com.appCadDelta.factory.EntityManagerFactory;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Maiquel
 */
public class OrdemServicoJpaController {

    private EntityManager em = null;

    public OrdemServicoJpaController() {
        EntityManagerFactory ent = new EntityManagerFactory();
        em = ent.getEntityManager();

    }

    public void create(Ordemservico os) {
        em.getTransaction().begin();
        em.persist(os);
        em.getTransaction().commit();
        em.close();
    }

    public void edit(Ordemservico os){
        em.getTransaction().begin();
        em.merge(os);
        em.getTransaction().commit();
        em.close();
    }
    
    public List<Ordemservico> findAll() {
        try {
            return em.createNamedQuery("Ordemservico.findAll").getResultList();
        } finally {
            em.close();
        }
    }

    public Ordemservico findById(Integer id) {
        try {
            return em.find(Ordemservico.class, id);
        } finally {
            em.close();
        }
    }

  
    public List<Ordemservico> findAparelhosByIdOS(Integer id) {
        try {
            return em.createNamedQuery("Ordemservico.findAparelhosByIdOS").setParameter("idOs", id).getResultList();
        } finally {
            em.close();
        }
    }
    
    public void removeAparelhoOS(Ordemservico os){
        try{
            em.createQuery("DELETE FROM aparelho_ordemservicos a WHERE a.id = :id").setParameter("id",os.getId()).executeUpdate();
        }finally{
            em.close();
        }
    }

    public static void main(String[] args) {
        OrdemServicoJpaController jpa = new OrdemServicoJpaController();
        System.out.println("teste" + jpa.findAparelhosByIdOS(8));
    }
}
