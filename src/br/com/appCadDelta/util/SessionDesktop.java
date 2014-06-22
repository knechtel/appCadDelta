/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.util;

import br.com.appCadDelta.entity.Usuario;

/**
 *
 * @author MaiquelKl
 */
public class SessionDesktop {
    
    private static Usuario usuario;

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
    
    
    
}
