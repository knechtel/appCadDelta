/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.JPAConttroller;

import br.com.appCadDelta.entity.Peca;
import br.com.appCadDelta.factory.EntityManagerFactory;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author maiquelknechtel
 */
public class PecasJpaController {

    private EntityManager em = null;

    public PecasJpaController() {
        EntityManagerFactory emf = new EntityManagerFactory();
        em = emf.getEntityManager();
    }

    public void create(Peca ospecas) {
        em.getTransaction().begin();
        em.persist(ospecas);
        em.getTransaction().commit();
        em.close();
    }

    public void edit(Peca ospecas) {
        em.getTransaction().begin();
        em.merge(ospecas);
        em.getTransaction().commit();
        em.close();
    }

    public List<Peca> findByAparelho(Integer id) {
        return em.createQuery("SELECT os FROM Peca os WHERE os.idAparelho = :id")
                .setParameter("id", id).getResultList();
    }

    public void delete(Peca peca) {
        em.getTransaction().begin();
        peca = em.merge(peca);
        em.remove(peca);
        em.getTransaction().commit();
        em.close();

    }

    public static void main(String[] args) {
        Peca ospeca = new Peca();
        ospeca.setDescricao("fonte");
        ospeca.setIdAparelho(1);
        PecasJpaController pecaCtl = new PecasJpaController();
        pecaCtl.create(ospeca);

    }
}
