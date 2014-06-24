/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.util;

import br.com.appCadDelta.entity.Usuario;
import javax.swing.ProgressMonitor;


/**
 *
 * @author MaiquelKl
 */
public class SessionDesktop {
    
    private static Integer idRelatorio =0;
    
    private static Usuario usuario;

    private static ProgressMonitor processMonitor;

    public static ProgressMonitor getProcessMonitor() {
        return processMonitor;
    }

    public static void setProcessMonitor(ProgressMonitor processMonitor) {
        SessionDesktop.processMonitor = processMonitor;
    }
    
    /**
     * @return the usuario
     */
    public static Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param aUsuario the usuario to set
     */
    public static void setUsuario(Usuario aUsuario) {
        usuario = aUsuario;
    }

    /**
     * @return the idRelatorio
     */
    public static Integer getIdRelatorio() {
        return idRelatorio;
    }

    /**
     * @param aIdRelatorio the idRelatorio to set
     */
    public static void setIdRelatorio(Integer aIdRelatorio) {
        idRelatorio = aIdRelatorio;
    }
    
    
    
}
