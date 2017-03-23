/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samplecenter;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author loris
 */
@Entity
@Table(name = "style")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Style.findAll", query = "SELECT s FROM Style s")
    , @NamedQuery(name = "Style.findByIdstyle", query = "SELECT s FROM Style s WHERE s.idstyle = :idstyle")
    , @NamedQuery(name = "Style.findByName", query = "SELECT s FROM Style s WHERE s.name = :name")})
public class Style implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idstyle")
    private Integer idstyle;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "fkStyle")
    private Collection<SampleStyle> sampleStyleCollection;

    public Style() {
    }

    public Style(Integer idstyle) {
        this.idstyle = idstyle;
    }

    public Integer getIdstyle() {
        return idstyle;
    }

    public void setIdstyle(Integer idstyle) {
        this.idstyle = idstyle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<SampleStyle> getSampleStyleCollection() {
        return sampleStyleCollection;
    }

    public void setSampleStyleCollection(Collection<SampleStyle> sampleStyleCollection) {
        this.sampleStyleCollection = sampleStyleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idstyle != null ? idstyle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Style)) {
            return false;
        }
        Style other = (Style) object;
        if ((this.idstyle == null && other.idstyle != null) || (this.idstyle != null && !this.idstyle.equals(other.idstyle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "samplecenter.Style[ idstyle=" + idstyle + " ]";
    }
    
}
