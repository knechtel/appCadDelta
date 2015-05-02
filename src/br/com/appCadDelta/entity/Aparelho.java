/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.appCadDelta.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Aparelho.findByModelo", query = "SELECT a FROM Aparelho a WHERE a.modelo = :modelo"),
    @NamedQuery(name = "Aparelho.delete", query = "DELETE FROM Aparelho a WHERE a.id = :id")})
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
    @Column(name = "autorizado")
    private Integer autorizado;
    @Column(name = "pronto")
    private Integer pronto;
    @Column(name = "defeito")
    private String defeito;
    @Column(name = "preco")
    private Double preco;
    @Column(name = "contOS")
    private Integer contOs;
    @Column(name = "contador")
    private Integer contador;
    @Column(name = "dataEntrada")
    @Temporal(TemporalType.DATE)
    private Date dataEntrada;
    @Column(name = "dataSaida")
    @Temporal(TemporalType.DATE)
    private Date dataSaida;
    @Column(name = "dataEntradaRetorno")
    @Temporal(TemporalType.DATE)
    private Date dataEntradaRetorno;
    @Column(name = "SaidaRetorno")
    @Temporal(TemporalType.DATE)
    private Date saidaRetorno;
    @Column(name = "defeitoApresentado")
    private String defeitoApresentado;
    @Column(name = "defeitoCostado")
    private String defeitoCostado;
    
    
    
    @Column(name = "usuarioRecebedor")
    private String usuarioRecebedor;
    
    @Column(name = "usuarioEntregador")
    private String usuarioEntregador;
    
    
    
    @Column(name = "aspecto")
    private String aspecto;

    
    @Column(name = "maoDeObra")
    private Double maoDeObra;

    @Column(name = "orcamento")
    private Double orcamento;
    
    @Column(name = "totalPeca")
    private Double totalPeca;
    
    @Column(name = "pago")
    private String pago;
    
    
    @Column(name = "tecResponsavel")
    private String tecResponsavel;

    @Column(name = "desconto")
    private Double desconto;

    
    @Column(name = "semConserto")
    private String semConserto;

    @Column(name = "impreso")
    private Integer impresso;

    
    public Aparelho() {
    }

    public Integer getImpresso() {
        return impresso;
    }

    public void setImpresso(Integer impresso) {
        this.impresso = impresso;
    }
    
    public String getSemConserto() {
        return semConserto;
    }

    public void setSemConserto(String semConserto) {
        this.semConserto = semConserto;
    }
    
    
    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    
    public Double getTotalPeca() {
        return totalPeca;
    }

    public void setTotalPeca(Double totalPeca) {
        this.totalPeca = totalPeca;
    }
    
    
    public Integer getContOs() {
        return contOs;
    }

    public void setContOs(Integer contOs) {
        this.contOs = contOs;
    }

    public Integer getContador() {
        return contador;
    }

    public void setContador(Integer contador) {
        this.contador = contador;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Date getDataEntradaRetorno() {
        return dataEntradaRetorno;
    }

    public void setDataEntradaRetorno(Date dataEntradaRetorno) {
        this.dataEntradaRetorno = dataEntradaRetorno;
    }

    public Date getSaidaRetorno() {
        return saidaRetorno;
    }

    public void setSaidaRetorno(Date saidaRetorno) {
        this.saidaRetorno = saidaRetorno;
    }

    public String getDefeitoApresentado() {
        return defeitoApresentado;
    }

    public void setDefeitoApresentado(String defeitoApresentado) {
        this.defeitoApresentado = defeitoApresentado;
    }

    public String getDefeitoCostado() {
        return defeitoCostado;
    }

    public void setDefeitoCostado(String defeitoCostado) {
        this.defeitoCostado = defeitoCostado;
    }

    public String getUsuarioRecebedor() {
        return usuarioRecebedor;
    }

    public void setUsuarioRecebedor(String usuarioRecebedor) {
        this.usuarioRecebedor = usuarioRecebedor;
    }

    public String getUsuarioEntregador() {
        return usuarioEntregador;
    }

    public void setUsuarioEntregador(String usuarioEntregador) {
        this.usuarioEntregador = usuarioEntregador;
    }

    public String getAspecto() {
        return aspecto;
    }

    public void setAspecto(String aspecto) {
        this.aspecto = aspecto;
    }

    public Double getMaoDeObra() {
        return maoDeObra;
    }

    public void setMaoDeObra(Double maoDeObra) {
        this.maoDeObra = maoDeObra;
    }

    public Double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Double orcamento) {
        this.orcamento = orcamento;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public String getTecResponsavel() {
        return tecResponsavel;
    }

    public void setTecResponsavel(String tecResponsavel) {
        this.tecResponsavel = tecResponsavel;
    }

    
    
    
    public String getDefeito() {
        return defeito;
    }

    public void setDefeito(String defeito) {
        this.defeito = defeito;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
    
    

    public Aparelho(Integer id) {
        this.id = id;
    }

    public Integer getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(Integer autorizado) {
        this.autorizado = autorizado;
    }

    public Integer getPronto() {
        return pronto;
    }

    public void setPronto(Integer pronto) {
        this.pronto = pronto;
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
