/*
 * To change this template, choose Tools | Templates
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
 * @author Maiquel
 */
@Entity
@Table(name = "aparelho", catalog = "test1", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aparelho.findAll", query = "SELECT a FROM Aparelho a"),
    @NamedQuery(name = "Aparelho.findById", query = "SELECT a FROM Aparelho a WHERE a.id = :id"),
    @NamedQuery(name = "Aparelho.findBySerial", query = "SELECT a FROM Aparelho a WHERE a.serial = :serial"),
    @NamedQuery(name = "Aparelho.findByDescricao", query = "SELECT a FROM Aparelho a WHERE a.descricao = :descricao"),
    @NamedQuery(name = "Aparelho.findByMarca", query = "SELECT a FROM Aparelho a WHERE a.marca = :marca"),
    @NamedQuery(name = "Aparelho.findByModelo", query = "SELECT a FROM Aparelho a WHERE a.modelo = :modelo")})
public class Aparelho implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "Serial")
    private String serial;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "marca")
    private String marca;
    @Column(name = "modelo")
    private String modelo;

    public Aparelho() {
    }

    public Aparelho(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
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
        if (!(object instanceof Aparelho)) {
            return false;
        }
        Aparelho other = (Aparelho) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }
    
}
