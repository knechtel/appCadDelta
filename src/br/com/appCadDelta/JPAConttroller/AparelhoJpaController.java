/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.JPAConttroller;

import br.com.appCadDelta.entity.Aparelho;
import br.com.appCadDelta.entity.Ordemservico;
import br.com.appCadDelta.factory.EntityManagerFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        em.getTransaction().begin();
        aparelho = em.merge(aparelho);
        em.remove(aparelho);
        em.getTransaction().commit();
        em.close();
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

    
       public List<Aparelho> findByYearDateSaida(Date dataSaidaStart,Date dataSaidaEnd) {

        try {
            return em.createQuery("Select a FROM Aparelho a WHERE a.dataSaida BETWEEN :dataSaidaStart AND :dataSaidaEnd", 
                    Aparelho.class).setParameter("dataSaidaStart",dataSaidaStart).setParameter("dataSaidaEnd", dataSaidaEnd).getResultList();
        } finally {
            em.close();
        }
    }
    
    
    public static void main(String[] args) {
 AparelhoJpaController osjpa = new AparelhoJpaController();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String str = "02/09/2013";
        Date date = null;
        try {
            date = formatter.parse(str);

        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
        String str1 = "02/09/2003";
        Date date1 = null;
        try {
            date1 = formatter.parse(str1);

        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        
        System.out.println("date "+date);
        System.out.println(osjpa.findByYearDateSaida(date1,date).size());
         } 
    
}
