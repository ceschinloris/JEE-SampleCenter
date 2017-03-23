/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samplecenter;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "folder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Folder.findAll", query = "SELECT f FROM Folder f")
    , @NamedQuery(name = "Folder.findByIdfolder", query = "SELECT f FROM Folder f WHERE f.idfolder = :idfolder")
    , @NamedQuery(name = "Folder.findByName", query = "SELECT f FROM Folder f WHERE f.name = :name")})
public class Folder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfolder")
    private Integer idfolder;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "fkFolder")
    private Collection<Folder> folderCollection;
    @JoinColumn(name = "fk_folder", referencedColumnName = "idfolder")
    @ManyToOne
    private Folder fkFolder;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkFolder")
    private Collection<Sample> sampleCollection;

    public Folder() {
    }

    public Folder(Integer idfolder) {
        this.idfolder = idfolder;
    }

    public Integer getIdfolder() {
        return idfolder;
    }

    public void setIdfolder(Integer idfolder) {
        this.idfolder = idfolder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Folder> getFolderCollection() {
        return folderCollection;
    }

    public void setFolderCollection(Collection<Folder> folderCollection) {
        this.folderCollection = folderCollection;
    }

    public Folder getFkFolder() {
        return fkFolder;
    }

    public void setFkFolder(Folder fkFolder) {
        this.fkFolder = fkFolder;
    }

    @XmlTransient
    public Collection<Sample> getSampleCollection() {
        return sampleCollection;
    }

    public void setSampleCollection(Collection<Sample> sampleCollection) {
        this.sampleCollection = sampleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfolder != null ? idfolder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Folder)) {
            return false;
        }
        Folder other = (Folder) object;
        if ((this.idfolder == null && other.idfolder != null) || (this.idfolder != null && !this.idfolder.equals(other.idfolder))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "samplecenter.Folder[ idfolder=" + idfolder + " ]";
    }
    
}
