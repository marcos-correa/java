/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancoDados;

import java.io.Serializable;
import java.util.Date;
import javafx.scene.control.DatePicker;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dinha
 */
@Entity
@Table(name = "gasto")
@NamedQueries({
    @NamedQuery(name = "Gasto.findAll", query = "SELECT g FROM Gasto g"),
    @NamedQuery(name = "Gasto.findByIdGasto", query = "SELECT g FROM Gasto g WHERE g.idGasto = :idGasto"),
    @NamedQuery(name = "Gasto.findByData", query = "SELECT g FROM Gasto g WHERE g.data = :data"),
    @NamedQuery(name = "Gasto.findByValor", query = "SELECT g FROM Gasto g WHERE g.valor = :valor"),
    @NamedQuery(name = "Gasto.findByIdFormaPagamento", query = "SELECT g FROM Gasto g WHERE g.idFormaPagamento = :idFormaPagamento")})
public class Gasto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idGasto")
    private Integer idGasto;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @Column(name = "valor")
    private float valor;
    @Basic(optional = false)
    @Column(name = "idFormaPagamento")
    private String idFormaPagamento;
    @JoinColumn(name = "idCartao", referencedColumnName = "idCartao")
    @ManyToOne
    private Cartao idCartao;
    @JoinColumn(name = "idTipoGasto", referencedColumnName = "idTipoGasto")
    @ManyToOne(optional = false)
    private TipoGasto idTipoGasto;

    public Gasto() {
    }

    public Gasto(Integer idGasto) {
        this.idGasto = idGasto;
    }

    public Gasto(Integer idGasto, Date data, float valor, String idFormaPagamento) {
        this.idGasto = idGasto;
        this.data = data;
        this.valor = valor;
        this.idFormaPagamento = idFormaPagamento;
    }

    public Integer getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(Integer idGasto) {
        this.idGasto = idGasto;
    }
    
    public TipoGasto getTipoGasto() {
       return idTipoGasto;
    }
    
    public void setTipoGasto(TipoGasto idTipoGasto) {
       this.idTipoGasto = idTipoGasto;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(String idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public Cartao getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(Cartao idCartao) {
        this.idCartao = idCartao;
    }

    public TipoGasto getIdTipoGasto() {
        return idTipoGasto;
    }

    public void setIdTipoGasto(TipoGasto idTipoGasto) {
        this.idTipoGasto = idTipoGasto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGasto != null ? idGasto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gasto)) {
            return false;
        }
        Gasto other = (Gasto) object;
        if ((this.idGasto == null && other.idGasto != null) || (this.idGasto != null && !this.idGasto.equals(other.idGasto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bancoDados.Gasto[ idGasto=" + idGasto + " ]";
    }

    public void setData(DatePicker cxData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
