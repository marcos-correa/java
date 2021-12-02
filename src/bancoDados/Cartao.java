/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancoDados;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dinha
 */
@Entity
@Table(name = "cartao")
@NamedQueries({
    @NamedQuery(name = "Cartao.findAll", query = "SELECT c FROM Cartao c"),
    @NamedQuery(name = "Cartao.findByIdCartao", query = "SELECT c FROM Cartao c WHERE c.idCartao = :idCartao"),
    @NamedQuery(name = "Cartao.findByBandeira", query = "SELECT c FROM Cartao c WHERE c.bandeira = :bandeira"),
    @NamedQuery(name = "Cartao.findByNome", query = "SELECT c FROM Cartao c WHERE c.nome = :nome"),
    @NamedQuery(name = "Cartao.findByDataValidade", query = "SELECT c FROM Cartao c WHERE c.dataValidade = :dataValidade")})
public class Cartao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idCartao")
    private String idCartao;
    @Basic(optional = false)
    @Column(name = "bandeira")
    private String bandeira;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "dataValidade")
    @Temporal(TemporalType.DATE)
    private Date dataValidade;
    @OneToMany(mappedBy = "idCartao")
    private Collection<Gasto> gastoCollection;

    public Cartao() {
    }

    public Cartao(String idCartao) {
        this.idCartao = idCartao;
    }

    public Cartao(String idCartao, String bandeira, String nome, Date dataValidade) {
        this.idCartao = idCartao;
        this.bandeira = bandeira;
        this.nome = nome;
        this.dataValidade = dataValidade;
    }

    public String getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(String idCartao) {
        this.idCartao = idCartao;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public Collection<Gasto> getGastoCollection() {
        return gastoCollection;
    }

    public void setGastoCollection(Collection<Gasto> gastoCollection) {
        this.gastoCollection = gastoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCartao != null ? idCartao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cartao)) {
            return false;
        }
        Cartao other = (Cartao) object;
        if ((this.idCartao == null && other.idCartao != null) || (this.idCartao != null && !this.idCartao.equals(other.idCartao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bancoDados.Cartao[ idCartao=" + idCartao + " ]";
    }
    
}
