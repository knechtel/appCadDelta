/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.JPAConttroller;

import br.com.appCadDelta.entity.Cidade;
import br.com.appCadDelta.factory.EntityManagerFactory;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Maiquel
 */
public class CidadeJpaController implements Serializable {

    private EntityManager em = null;

    public CidadeJpaController() {
        EntityManagerFactory emf = new EntityManagerFactory();
        em = emf.getEntityManager();
    }

    public void create(Cidade cidade) {
        em.getTransaction().begin();
        em.persist(cidade);
        em.getTransaction().commit();
        em.close();
    }

    public void edit(Cidade cidade) {
        em.getTransaction().begin();
        em.merge(cidade);
        em.getTransaction().commit();
        em.close();
    }

    public List<Cidade> findAll() {
        try {
            return em.createNamedQuery("Cidade.findAll").getResultList();
        } finally {
            em.close();
        }
    }

    public void delete(Cidade cidade) {
        em.getTransaction().begin();
        cidade = em.merge(cidade);
        em.remove(cidade);
        em.getTransaction().commit();
        em.close();
    }

    public static void main(String[] args) {
        Cidade c = new Cidade();
        c.setNome("Osório");
        c.setUf("RS");
        CidadeJpaController cidadeJpa = new CidadeJpaController();
        cidadeJpa.create(c);

    }
}
