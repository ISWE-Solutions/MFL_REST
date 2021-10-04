/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moh.mfl.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author chulu
 */
@Entity
@Table(name = "geography_locationtype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Locationtype.findAll", query = "SELECT l FROM Locationtype l"),
    @NamedQuery(name = "Locationtype.findById", query = "SELECT l FROM Locationtype l WHERE l.id = :id"),
    @NamedQuery(name = "Locationtype.findByName", query = "SELECT l FROM Locationtype l WHERE l.name = :name"),
    @NamedQuery(name = "Locationtype.findBySharedId", query = "SELECT l FROM Locationtype l WHERE l.sharedId = :sharedId")})
public class Locationtype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "name")
    private String name;
    @Column(name = "shared_id")
    private Integer sharedId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
    private Collection<Facility> facilityCollection;

    public Locationtype() {
    }

    public Locationtype(Integer id) {
        this.id = id;
    }

    public Locationtype(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSharedId() {
        return sharedId;
    }

    public void setSharedId(Integer sharedId) {
        this.sharedId = sharedId;
    }

    @XmlTransient
    public Collection<Facility> getFacilityCollection() {
        return facilityCollection;
    }

    public void setFacilityCollection(Collection<Facility> facilityCollection) {
        this.facilityCollection = facilityCollection;
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
        if (!(object instanceof Locationtype)) {
            return false;
        }
        Locationtype other = (Locationtype) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.moh.mfl.model.Locationtype[ id=" + id + " ]";
    }
    
}
