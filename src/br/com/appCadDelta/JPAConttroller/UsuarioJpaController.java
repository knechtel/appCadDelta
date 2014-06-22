/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.JPAConttroller;

import br.com.appCadDelta.entity.Usuario;
import br.com.appCadDelta.factory.EntityManagerFactory;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Maiquel
 */
public class UsuarioJpaController {

    private EntityManager em = null;

    public UsuarioJpaController() {
        EntityManagerFactory emf = new EntityManagerFactory();
        em = emf.getEntityManager();
    }

    public void create(Usuario usuario) {
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();
    }

    public List<Usuario> findAll() {
        try {
            return em.createNamedQuery("Usuario.findAll").getResultList();
        } finally {
            em.close();
        }
    }

    public void edit(Usuario usuario) {
        em.getTransaction().begin();
        em.merge(usuario);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Usuario usuario) {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Usuario u WHERE u.id = :id").setParameter("id", usuario.getId()).executeUpdate();
        em.getTransaction().commit();
        em.close();

    }

    public List<Usuario> findByLoginAndSenha(Usuario usuario) {
        try {
            return  em.createNamedQuery("Usuario.findByNomeAndSenha").setParameter("login",
                    usuario.getLogin()).setParameter("senha", usuario.getSenha()).getResultList();
        } finally {
            em.close();
        }
    }
    
    public static void main(String[] args) {
        Usuario usuario = new Usuario();
        usuario.setLogin("vanessa");
        usuario.setSenha("123");
        UsuarioJpaController userJpa = new UsuarioJpaController();
        
        List<Usuario> listUser = userJpa.findByLoginAndSenha(usuario);
        
        if(listUser.size()==1){
            System.out.println("Um");
        }else{
            System.out.println("zero");
        }
        
        System.out.println("FIM");
    }
}
