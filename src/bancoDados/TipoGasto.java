/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancoDados;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author dinha
 */
@Entity
@Table(name = "tipogasto")
@NamedQueries({
    @NamedQuery(name = "TipoGasto.findAll", query = "SELECT t FROM TipoGasto t"),
    @NamedQuery(name = "TipoGasto.findByIdTipoGasto", query = "SELECT t FROM TipoGasto t WHERE t.idTipoGasto = :idTipoGasto"),
    @NamedQuery(name = "TipoGasto.findByDescricaoTipo", query = "SELECT t FROM TipoGasto t WHERE t.descricaoTipo = :descricaoTipo")})
public class TipoGasto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idTipoGasto")
    private Integer idTipoGasto;
    @Basic(optional = false)
    @Column(name = "descricaoTipo")
    private String descricaoTipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoGasto")
    private Collection<Gasto> gastoCollection;

    public TipoGasto() {
    }

    public TipoGasto(Integer idTipoGasto) {
        this.idTipoGasto = idTipoGasto;
    }

    public TipoGasto(Integer idTipoGasto, String descricaoTipo) {
        this.idTipoGasto = idTipoGasto;
        this.descricaoTipo = descricaoTipo;
    }

    public Integer getIdTipoGasto() {
        return idTipoGasto;
    }

    public void setIdTipoGasto(Integer idTipoGasto) {
        this.idTipoGasto = idTipoGasto;
    }

    public String getDescricaoTipo() {
        return descricaoTipo;
    }

    public void setDescricaoTipo(String descricaoTipo) {
        this.descricaoTipo = descricaoTipo;
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
        hash += (idTipoGasto != null ? idTipoGasto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoGasto)) {
            return false;
        }
        TipoGasto other = (TipoGasto) object;
        if ((this.idTipoGasto == null && other.idTipoGasto != null) || (this.idTipoGasto != null && !this.idTipoGasto.equals(other.idTipoGasto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descricaoTipo;
    }
    
}
