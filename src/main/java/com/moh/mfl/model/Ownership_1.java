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
@Table(name = "ownership")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ownership_1.findAll", query = "SELECT o FROM Ownership_1 o"),
    @NamedQuery(name = "Ownership_1.findById", query = "SELECT o FROM Ownership_1 o WHERE o.id = :id"),
    @NamedQuery(name = "Ownership_1.findBySharedId", query = "SELECT o FROM Ownership_1 o WHERE o.sharedId = :sharedId"),
    @NamedQuery(name = "Ownership_1.findByName", query = "SELECT o FROM Ownership_1 o WHERE o.name = :name")})
public class Ownership_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "shared_id")
    private int sharedId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ownership")
    private Collection<Facility> facilityCollection;

    public Ownership_1() {
    }

    public Ownership_1(Integer id) {
        this.id = id;
    }

    public Ownership_1(Integer id, int sharedId, String name) {
        this.id = id;
        this.sharedId = sharedId;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSharedId() {
        return sharedId;
    }

    public void setSharedId(int sharedId) {
        this.sharedId = sharedId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof Ownership_1)) {
            return false;
        }
        Ownership_1 other = (Ownership_1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.moh.mfl.model.Ownership_1[ id=" + id + " ]";
    }
    
}
