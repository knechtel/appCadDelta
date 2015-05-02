/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.JPAConttroller;

import br.com.appCadDelta.Gui.Desktop;
import br.com.appCadDelta.entity.Cliente;
import br.com.appCadDelta.entity.Ordemservico;
import br.com.appCadDelta.factory.EntityManagerFactory;
import br.com.appCadDelta.util.Util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public List<Ordemservico> findAllOrderByContOs() {
        try {
            return em.createQuery("SELECT o FROM Ordemservico as o order by o.contOs").getResultList();
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

    public List<Ordemservico> findAparelhosByIdOS2(Integer id) {
        try {
            return em.createNativeQuery("SELECT * FROM Ordemservico JOIN aparelho ON"
                    + " aparelho.contOs = ordemservico.contOs where Ordemservico.id = ? s", Ordemservico.class).setParameter(1, id).getResultList();
        } finally {
            em.close();
        }
    }

    public Ordemservico findByContOS(Integer id) {
        try {
            return em.createQuery("Select o FROM Ordemservico o WHERE o.contOs = :cont", Ordemservico.class).setParameter("cont", id).getSingleResult();
        } finally {
            em.close();
        }
    }

    public void removeAparelhoOS(Integer id) {
        try {
            em.createQuery("DELETE FROM aparelho_ordemservicos a WHERE a.id = :id").setParameter("id", id).executeUpdate();
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

    public List<Ordemservico> findByDateSaida(Date dataSaida) {

        try {
            return em.createNamedQuery("Ordemservico.findByDataSaida")
                    .setParameter("dataSaida", dataSaida).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Ordemservico> findByDateEntrada(Date dataSaida) {

        try {
            return em.createNamedQuery("Ordemservico.findByDataEntrada")
                    .setParameter("dataEntrada", dataSaida).getResultList();
        } finally {
            em.close();
        }
    }

    public Ordemservico findLastRegister() {

        try {
            return em.createQuery("Select o from Ordemservico as o  order by o.contOs DESC", Ordemservico.class).setMaxResults(1).getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Ordemservico> findByCliente(String cliente) {

        try {
            return em.createQuery("SELECT o FROM Ordemservico o  JOIN FETCH o.clienteId WHERE o.clienteId.nome LIKE  :nome")
                    .setParameter("nome", cliente + "%").getResultList();

        } finally {
            em.close();
        }
    }

    public List<Ordemservico> listByContOS(Integer id) {
        try {
            return em.createQuery("Select o FROM Ordemservico o WHERE o.contOs = :cont", Ordemservico.class).setParameter("cont", id).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Ordemservico> findAllPaginator() {
        try {
            return em.createQuery("SELECT o FROM Ordemservico as o order by o.contOs").setMaxResults(40).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Ordemservico> findAllPaginatorById(Integer id) {
        try {
            return em.createQuery("Select o FROM Ordemservico o WHERE o.contOs > :cont order by o.contOs ",
                    Ordemservico.class).setMaxResults(40).setParameter("cont", id).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Ordemservico> findAllPaginatorByIdNative() {
        try {
            return em.createNativeQuery("SELECT * FROM Ordemservico ORDER BY contOs DESC LIMIT 50;", Ordemservico.class)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    public List<Ordemservico> findAllPaginatorByIdNative(Integer interval) {
        try {
            return em.createNativeQuery("SELECT * FROM Ordemservico where contOs < ? "
                    + "ORDER BY contOs DESC LIMIT 50;", Ordemservico.class).setParameter(1, interval)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    public List<Ordemservico> findAparelhosBydataSaida(Date data) {
        try {
            return em.createNativeQuery("SELECT * FROM Ordemservico JOIN aparelho ON"
                    + " aparelho.contOs = ordemservico.contOs where aparelho.dataSaida = ? ", Ordemservico.class).setParameter(1, data).getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Ordemservico> findAparelhosBydataEntrada(Date data) {
        try {
            return em.createNativeQuery("SELECT * FROM Ordemservico JOIN aparelho ON"
                    + " aparelho.contOs = ordemservico.contOs where aparelho.dataEntrada = ? ", Ordemservico.class).setParameter(1, data).getResultList();
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) throws Exception {
        OrdemServicoJpaController osjpa = new OrdemServicoJpaController();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String str = "02/09/2013";
        Date date = null;
        try {
            date = formatter.parse(str);

        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        System.out.println(osjpa.findAparelhosBydataSaida(date));
    }
}
