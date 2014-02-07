/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.JPAConttroller;

import java.io.Serializable;
import br.com.appCadDelta.entity.Cliente;
import br.com.appCadDelta.factory.EntityManagerFactory;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Maiquel
 */
public class ClienteJpaController implements Serializable {

    private EntityManager em = null;

    public ClienteJpaController() {
        EntityManagerFactory emf = new EntityManagerFactory();
        em = emf.getEntityManager();
    }

    public void create(Cliente cliente) {
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        em.close();
    }
    
    public void edit(Cliente cliente){
        em.getTransaction().begin();
        em.merge(cliente);
        em.getTransaction().commit();
        em.close();
    }

    public Cliente findById(Integer id) {
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public List<Cliente> findAll() {
        try {
            return em.createNamedQuery("Cliente.findAll").getResultList();
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        ClienteJpaController cidadeJpa = new ClienteJpaController();
        Cliente c = cidadeJpa.findById(1);
        c.setEndereco("centro");
        new ClienteJpaController().edit(c);
    }
}
