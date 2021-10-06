/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moh.mfl.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chulu
 */
@Entity
@Table(name = "`MFL_facility_services`")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FacilityServices.findAll", query = "SELECT f FROM FacilityServices f"),
    @NamedQuery(name = "FacilityServices.findById", query = "SELECT f FROM FacilityServices f WHERE f.id = :id"),
    @NamedQuery(name = "FacilityServices.findByServiceAreaId", query = "SELECT f FROM FacilityServices f WHERE f.serviceAreaId = :serviceAreaId")})
public class FacilityServices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "facility_id")
    private Integer facilityId;
    @Column(name = "service_id")
    private Integer serviceId;
    @Column(name = "service_area_id")
    private Integer serviceAreaId;

    public FacilityServices() {
    }

    public FacilityServices(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServiceAreaId() {
        return serviceAreaId;
    }

    public void setServiceAreaId(Integer serviceAreaId) {
        this.serviceAreaId = serviceAreaId;
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
        if (!(object instanceof FacilityServices)) {
            return false;
        }
        FacilityServices other = (FacilityServices) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.moh.mfl.model.FacilityServices[ id=" + id + " ]";
    }

    /**
     * @return the facilityId
     */
    public Integer getFacilityId() {
        return facilityId;
    }

    /**
     * @param facilityId the facilityId to set
     */
    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }

    /**
     * @return the serviceId
     */
    public Integer getServiceId() {
        return serviceId;
    }

    /**
     * @param serviceId the serviceId to set
     */
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

}
