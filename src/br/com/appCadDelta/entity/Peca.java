/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maiquelknechtel
 */
@Entity
@Table(name = "ospecas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Peca.findAll", query = "SELECT o FROM Peca o"),
    @NamedQuery(name = "Peca.findByDescricao", query = "SELECT o FROM Peca o WHERE o.descricao = :descricao"),
    @NamedQuery(name = "Peca.findById", query = "SELECT o FROM Peca o WHERE o.id = :id"),
    @NamedQuery(name = "Peca.findByValor", query = "SELECT o FROM Peca o WHERE o.valor = :valor"),
    @NamedQuery(name = "Peca.findByIdAparelho", query = "SELECT o FROM Peca o WHERE o.idAparelho = :idAparelho")})
public class Peca implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "descricao")
    private String descricao;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Double valor;
    @Basic(optional = false)
    @Column(name = "idAparelho")
    private int idAparelho;

    public Peca() {
    }

    public Peca(Integer id) {
        this.id = id;
    }

    public Peca(Integer id, int idAparelho) {
        this.id = id;
        this.idAparelho = idAparelho;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getIdAparelho() {
        return idAparelho;
    }

    public void setIdAparelho(int idAparelho) {
        this.idAparelho = idAparelho;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Peca)) {
            return false;
        }
        Peca other = (Peca) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  id.toString();
    }
    
}
