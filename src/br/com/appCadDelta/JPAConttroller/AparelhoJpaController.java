/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.JPAConttroller;

import br.com.appCadDelta.entity.Aparelho;
import br.com.appCadDelta.entity.Ordemservico;
import br.com.appCadDelta.factory.EntityManagerFactory;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Maiquel
 */
public class AparelhoJpaController {

    private EntityManager em = null;

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

    public void edit(Aparelho aparelho) {
        em.getTransaction().begin();
        em.merge(aparelho);
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

    public void delete(Aparelho aparelho) {
        try {
            em.createNamedQuery("Aparelho.delete").setParameter("id", aparelho.getId()).executeUpdate();
        } finally {
            em.close();
        }
    }

    public List<Aparelho> nativeFindAparelhosByIdOS(Integer id) {
        try {
            return em.createNativeQuery("Select * from Aparelho where contOS = ?", Aparelho.class).setParameter(1, id).getResultList();
        } finally {
            em.close();
        }
    }



    public List<Aparelho> findByDateSaida(Date sringToDate) {
        //SELECT o FROM Ordemservico o WHERE o.dataSaida = :dataSaida
        try {
            return em.createQuery("SELECT a FROM Aparelho a WHERE a.dataSaida = :dataSaida").setParameter("dataSaida", sringToDate).getResultList();
        } finally {
            em.close();
        }
    }

    public Aparelho findLastRegister() {

        try {
            return em.createQuery("Select a from Aparelho as a order by a.contador DESC", Aparelho.class).setMaxResults(1).getSingleResult();
        } finally {
            em.close();
        }
    }
    
    public static void main(String[] args) {

        AparelhoJpaController aparelhoJpa = new AparelhoJpaController();

        List<Aparelho> list = aparelhoJpa.nativeFindAparelhosByIdOS(18482);

        System.out.println("size = " + list.size());
    } 
    
}
