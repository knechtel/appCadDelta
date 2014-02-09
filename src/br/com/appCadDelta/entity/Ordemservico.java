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
import javax.persistence.JoinTable;
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
@Table(name = "ordemservico", catalog = "test1", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ordemservico.findAll", query = "SELECT o FROM Ordemservico o"),
    @NamedQuery(name = "Ordemservico.findById", query = "SELECT o FROM Ordemservico o WHERE o.id = :id"),
    @NamedQuery(name = "Ordemservico.findByDataEntrada", query = "SELECT o FROM Ordemservico o WHERE o.dataEntrada = :dataEntrada"),
    @NamedQuery(name = "Ordemservico.findByDataSaida", query = "SELECT o FROM Ordemservico o WHERE o.dataSaida = :dataSaida"),
    @NamedQuery(name = "Ordemservico.findByObs", query = "SELECT o FROM Ordemservico o WHERE o.obs = :obs"),
    @NamedQuery(name = "Ordemservico.findByPecas", query = "SELECT o FROM Ordemservico o WHERE o.pecas = :pecas"),
    @NamedQuery(name = "Ordemservico.findByTotalOrcamento", query = "SELECT o FROM Ordemservico o WHERE o.totalOrcamento = :totalOrcamento"),
    @NamedQuery(name = "Ordemservico.findAparelhosByIdOS", query = "SELECT os FROM Ordemservico os JOIN FETCH os.listaAparelho WHERE os.id=:idOs")})
public class Ordemservico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "dataEntrada")
    @Temporal(TemporalType.DATE)
    private Date dataEntrada;
    @Column(name = "dataSaida")
    @Temporal(TemporalType.DATE)
    private Date dataSaida;
    @Column(name = "obs")
    private String obs;
    @Column(name = "pecas")
    private String pecas;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalOrcamento")
    private Double totalOrcamento;
    @JoinColumn(name = "usuarioEntregou_id", referencedColumnName = "id")
    @ManyToOne
    private Usuario usuarioEntregouid;
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    @ManyToOne
    private Cliente clienteId;
    @JoinColumn(name = "usuarioRecebeu_id", referencedColumnName = "id")
    @ManyToOne
    private Usuario usuarioRecebeuid;
    @JoinTable(name = "Aparelho_ordemServicos",
    joinColumns = {
        @JoinColumn(name = "ordemServico_id", referencedColumnName = "id")},
    inverseJoinColumns = {
        @JoinColumn(name = "aparelho_id", referencedColumnName = "id")})
    @OneToMany(fetch = FetchType.LAZY)
    private List<Aparelho> listaAparelho;

    public Ordemservico() {
    }

    public Ordemservico(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getPecas() {
        return pecas;
    }

    public void setPecas(String pecas) {
        this.pecas = pecas;
    }

    public Double getTotalOrcamento() {
        return totalOrcamento;
    }

    public void setTotalOrcamento(Double totalOrcamento) {
        this.totalOrcamento = totalOrcamento;
    }

    public Usuario getUsuarioEntregouid() {
        return usuarioEntregouid;
    }

    public void setUsuarioEntregouid(Usuario usuarioEntregouid) {
        this.usuarioEntregouid = usuarioEntregouid;
    }

    public Cliente getClienteId() {
        return clienteId;
    }

    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
    }

    public Usuario getUsuarioRecebeuid() {
        return usuarioRecebeuid;
    }

    public void setUsuarioRecebeuid(Usuario usuarioRecebeuid) {
        this.usuarioRecebeuid = usuarioRecebeuid;
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
        if (!(object instanceof Ordemservico)) {
            return false;
        }
        Ordemservico other = (Ordemservico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    /**
     * @return the listaAparelho
     */
    public List<Aparelho> getListaAparelho() {
        return listaAparelho;
    }

    /**
     * @param listaAparelho the listaAparelho to set
     */
    public void setListaAparelho(List<Aparelho> listaAparelho) {
        this.listaAparelho = listaAparelho;
    }
}
