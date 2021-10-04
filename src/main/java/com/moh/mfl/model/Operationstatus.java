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
@Table(name = "MFL_operationstatus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operationstatus.findAll", query = "SELECT o FROM Operationstatus o"),
    @NamedQuery(name = "Operationstatus.findById", query = "SELECT o FROM Operationstatus o WHERE o.id = :id"),
    @NamedQuery(name = "Operationstatus.findByName", query = "SELECT o FROM Operationstatus o WHERE o.name = :name"),
    @NamedQuery(name = "Operationstatus.findByDescription", query = "SELECT o FROM Operationstatus o WHERE o.description = :description"),
    @NamedQuery(name = "Operationstatus.findBySharedId", query = "SELECT o FROM Operationstatus o WHERE o.sharedId = :sharedId")})
public class Operationstatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "name")
    private String name;
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    @Column(name = "shared_id")
    private Integer sharedId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "operationalStatus")
    private Collection<Facility> facilityCollection;

    public Operationstatus() {
    }

    public Operationstatus(Integer id) {
        this.id = id;
    }

    public Operationstatus(Integer id, String name) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof Operationstatus)) {
            return false;
        }
        Operationstatus other = (Operationstatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.moh.mfl.model.Operationstatus[ id=" + id + " ]";
    }
    
}
