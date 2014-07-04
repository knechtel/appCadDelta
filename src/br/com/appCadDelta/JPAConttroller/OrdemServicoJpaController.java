/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.JPAConttroller;

import br.com.appCadDelta.entity.Ordemservico;
import br.com.appCadDelta.factory.EntityManagerFactory;
import br.com.appCadDelta.util.Util;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

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

    public void edit(Ordemservico os) {
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

    public void removeAparelhoOS(Ordemservico os) {
        try {
            em.createQuery("DELETE FROM aparelho_ordemservicos a WHERE a.id = :id").setParameter("id", os.getId()).executeUpdate();
        } finally {
            em.close();
        }
    }

    public void delete(Ordemservico os) {
        em.getTransaction().begin();
        os = em.merge(os);
        em.remove(os);
        em.getTransaction().commit();
        em.close();

    }
    
    public List<Ordemservico> findByDateEntrega(Date dataSaida){
    
        try {
            return em.createNamedQuery("Ordemservico.findByDataSaida")
                    .setParameter("dataSaida", dataSaida).getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Ordemservico> findByDateEntrada(Date dataSaida){
    
        try {
            return em.createNamedQuery("Ordemservico.findByDataEntrada")
                    .setParameter("dataEntrada", dataSaida).getResultList();
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) throws Exception {
        OrdemServicoJpaController jpa = new OrdemServicoJpaController();
        
        String str = JOptionPane.showInputDialog(null,"Entre com a data:");
        
        System.out.println("os -> "+jpa.findByDateEntrega(Util.sringToDate(str)));
    }
}
