/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Boh
 */
@Entity
@Table(name = "sample_style")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SampleStyle.findAll", query = "SELECT s FROM SampleStyle s"),
    @NamedQuery(name = "SampleStyle.findByIdsampleStyle", query = "SELECT s FROM SampleStyle s WHERE s.idsampleStyle = :idsampleStyle")})
public class SampleStyle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idsample_style")
    private Integer idsampleStyle;
    @JoinColumn(name = "fk_sample", referencedColumnName = "idsample")
    @ManyToOne
    private Sample fkSample;
    @JoinColumn(name = "fk_style", referencedColumnName = "idstyle")
    @ManyToOne
    private Style fkStyle;

    public SampleStyle() {
    }

    public SampleStyle(Integer idsampleStyle) {
        this.idsampleStyle = idsampleStyle;
    }

    public Integer getIdsampleStyle() {
        return idsampleStyle;
    }

    public void setIdsampleStyle(Integer idsampleStyle) {
        this.idsampleStyle = idsampleStyle;
    }

    public Sample getFkSample() {
        return fkSample;
    }

    public void setFkSample(Sample fkSample) {
        this.fkSample = fkSample;
    }

    public Style getFkStyle() {
        return fkStyle;
    }

    public void setFkStyle(Style fkStyle) {
        this.fkStyle = fkStyle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsampleStyle != null ? idsampleStyle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SampleStyle)) {
            return false;
        }
        SampleStyle other = (SampleStyle) object;
        if ((this.idsampleStyle == null && other.idsampleStyle != null) || (this.idsampleStyle != null && !this.idsampleStyle.equals(other.idsampleStyle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SampleStyle[ idsampleStyle=" + idsampleStyle + " ]";
    }
    
}
