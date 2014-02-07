/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.factory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Maiquel
 */
public class EntityManagerFactory {
    private EntityManager em= null;
    public EntityManagerFactory(){
        javax.persistence.EntityManagerFactory emf = Persistence.createEntityManagerFactory("appDeltaCadPU");
        this.em =  emf.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return em;
    }
    
}
