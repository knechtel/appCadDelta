/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.util;

import br.com.appCadDelta.entity.Aparelho;
import br.com.appCadDelta.entity.Cliente;
import br.com.appCadDelta.entity.Ordemservico;
import br.com.appCadDelta.entity.Usuario;
import javax.swing.ProgressMonitor;


/**
 *
 * @author MaiquelKl
 */
public class SessionDesktop {

    
    private static int flagAtzCadOS =0;
    
    private static Aparelho aparelho;
    
    private static Ordemservico os;
    
    private static Integer idRelatorio =0;
    
    private static Usuario usuario;

    private static ProgressMonitor processMonitor;
    
    private static Integer flagCliente = 0;

    private static Ordemservico os1;
    
    private static Cliente cliente;
    
    private static Integer flagSwap = 0;
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

    public static Integer getFlagCliente() {
        return flagCliente;
    }

    public static void setFlagCliente(Integer flagCliente) {
        SessionDesktop.flagCliente = flagCliente;
    }

    public static Cliente getCliente() {
        return cliente;
    }

    public static void setCliente(Cliente cliente) {
     SessionDesktop.cliente = cliente;
    }

    public static Ordemservico getOs() {
        return os;
    }

    public static void setOs(Ordemservico os) {
        SessionDesktop.os = os;
    }

    public static Aparelho getAparelho() {
        return aparelho;
    }

    public static void setAparelho(Aparelho aparelho) {
        SessionDesktop.aparelho = aparelho;
    }

    public static Ordemservico getOs1() {
        return os1;
    }

    public static void setOs1(Ordemservico os1) {
        SessionDesktop.os1 = os1;
    }

    public static int getFlagAtzCadOS() {
        return flagAtzCadOS;
    }

    public static void setFlagAtzCadOS(int flagAtzCadOS) {
        SessionDesktop.flagAtzCadOS = flagAtzCadOS;
    }
    
    
    
}
