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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "sample")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sample.findAll", query = "SELECT s FROM Sample s")
    , @NamedQuery(name = "Sample.findByIdsample", query = "SELECT s FROM Sample s WHERE s.idsample = :idsample")
    , @NamedQuery(name = "Sample.findByTitle", query = "SELECT s FROM Sample s WHERE s.title = :title")
    , @NamedQuery(name = "Sample.findByTag", query = "SELECT s FROM Sample s WHERE s.tag = :tag")
    , @NamedQuery(name = "Sample.findByUrl", query = "SELECT s FROM Sample s WHERE s.url = :url")})
public class Sample implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsample")
    private Integer idsample;
    @Size(max = 45)
    @Column(name = "title")
    private String title;
    @Size(max = 45)
    @Column(name = "tag")
    private String tag;
    @Size(max = 255)
    @Column(name = "url")
    private String url;
    @OneToMany(mappedBy = "fkSample")
    private Collection<SampleStyle> sampleStyleCollection;
    @JoinColumn(name = "fk_folder", referencedColumnName = "idfolder")
    @ManyToOne(optional = false)
    private Folder fkFolder;
    @JoinColumn(name = "fk_author", referencedColumnName = "iduser")
    @ManyToOne(optional = false)
    private User fkAuthor;

    public Sample() {
    }

    public Sample(Integer idsample) {
        this.idsample = idsample;
    }

    public Integer getIdsample() {
        return idsample;
    }

    public void setIdsample(Integer idsample) {
        this.idsample = idsample;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlTransient
    public Collection<SampleStyle> getSampleStyleCollection() {
        return sampleStyleCollection;
    }

    public void setSampleStyleCollection(Collection<SampleStyle> sampleStyleCollection) {
        this.sampleStyleCollection = sampleStyleCollection;
    }

    public Folder getFkFolder() {
        return fkFolder;
    }

    public void setFkFolder(Folder fkFolder) {
        this.fkFolder = fkFolder;
    }

    public User getFkAuthor() {
        return fkAuthor;
    }

    public void setFkAuthor(User fkAuthor) {
        this.fkAuthor = fkAuthor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsample != null ? idsample.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sample)) {
            return false;
        }
        Sample other = (Sample) object;
        if ((this.idsample == null && other.idsample != null) || (this.idsample != null && !this.idsample.equals(other.idsample))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "samplecenter.Sample[ idsample=" + idsample + " ]";
    }
    
}
