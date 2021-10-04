/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moh.mfl.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chulu
 */
@Entity
@Table(name = "facility")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facility.findAll", query = "SELECT f FROM Facility f"),
    @NamedQuery(name = "Facility.findById", query = "SELECT f FROM Facility f WHERE f.id = :id"),
    @NamedQuery(name = "Facility.findByConstituencyId", query = "SELECT f FROM Facility f WHERE f.constituencyId = :constituencyId"),
    @NamedQuery(name = "Facility.findByWardId", query = "SELECT f FROM Facility f WHERE f.wardId = :wardId"),
    @NamedQuery(name = "Facility.findByHimsCode", query = "SELECT f FROM Facility f WHERE f.himsCode = :himsCode"),
    @NamedQuery(name = "Facility.findBySmartcareCode", query = "SELECT f FROM Facility f WHERE f.smartcareCode = :smartcareCode"),
    @NamedQuery(name = "Facility.findByElmisCode", query = "SELECT f FROM Facility f WHERE f.elmisCode = :elmisCode"),
    @NamedQuery(name = "Facility.findByHpczCode", query = "SELECT f FROM Facility f WHERE f.hpczCode = :hpczCode"),
    @NamedQuery(name = "Facility.findByDisaCode", query = "SELECT f FROM Facility f WHERE f.disaCode = :disaCode"),
    @NamedQuery(name = "Facility.findByName", query = "SELECT f FROM Facility f WHERE f.name = :name"),
    @NamedQuery(name = "Facility.findByCatchmentPopulationHeadCount", query = "SELECT f FROM Facility f WHERE f.catchmentPopulationHeadCount = :catchmentPopulationHeadCount"),
    @NamedQuery(name = "Facility.findByCatchmentPopulationCso", query = "SELECT f FROM Facility f WHERE f.catchmentPopulationCso = :catchmentPopulationCso"),
    @NamedQuery(name = "Facility.findByNumberOfHouseholds", query = "SELECT f FROM Facility f WHERE f.numberOfHouseholds = :numberOfHouseholds"),
    @NamedQuery(name = "Facility.findByMobilityStatus", query = "SELECT f FROM Facility f WHERE f.mobilityStatus = :mobilityStatus"),
    @NamedQuery(name = "Facility.findByOwnershipType", query = "SELECT f FROM Facility f WHERE f.ownershipType = :ownershipType"),
    @NamedQuery(name = "Facility.findByAccesibility", query = "SELECT f FROM Facility f WHERE f.accesibility = :accesibility"),
    @NamedQuery(name = "Facility.findByLatitude", query = "SELECT f FROM Facility f WHERE f.latitude = :latitude"),
    @NamedQuery(name = "Facility.findByLongitude", query = "SELECT f FROM Facility f WHERE f.longitude = :longitude"),
    @NamedQuery(name = "Facility.findByStatus", query = "SELECT f FROM Facility f WHERE f.status = :status"),
    @NamedQuery(name = "Facility.findByDateApproved", query = "SELECT f FROM Facility f WHERE f.dateApproved = :dateApproved"),
    @NamedQuery(name = "Facility.findByApprovedBy", query = "SELECT f FROM Facility f WHERE f.approvedBy = :approvedBy"),
    @NamedQuery(name = "Facility.findByDateCreated", query = "SELECT f FROM Facility f WHERE f.dateCreated = :dateCreated"),
    @NamedQuery(name = "Facility.findByCreatedBy", query = "SELECT f FROM Facility f WHERE f.createdBy = :createdBy"),
    @NamedQuery(name = "Facility.findByDateUpdated", query = "SELECT f FROM Facility f WHERE f.dateUpdated = :dateUpdated"),
    @NamedQuery(name = "Facility.findByUpdatedBy", query = "SELECT f FROM Facility f WHERE f.updatedBy = :updatedBy"),
    @NamedQuery(name = "Facility.findByDateVerified", query = "SELECT f FROM Facility f WHERE f.dateVerified = :dateVerified"),
    @NamedQuery(name = "Facility.findByVerifiedBy", query = "SELECT f FROM Facility f WHERE f.verifiedBy = :verifiedBy"),
    @NamedQuery(name = "Facility.findByVerifierComments", query = "SELECT f FROM Facility f WHERE f.verifierComments = :verifierComments"),
    @NamedQuery(name = "Facility.findByApproverComments", query = "SELECT f FROM Facility f WHERE f.approverComments = :approverComments"),
    @NamedQuery(name = "Facility.findByProvinceApprovalStatus", query = "SELECT f FROM Facility f WHERE f.provinceApprovalStatus = :provinceApprovalStatus"),
    @NamedQuery(name = "Facility.findByNationalApprovalStatus", query = "SELECT f FROM Facility f WHERE f.nationalApprovalStatus = :nationalApprovalStatus")})
public class Facility implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "constituency_id")
    private Integer constituencyId;
    @Column(name = "ward_id")
    private Integer wardId;
    @Size(max = 2147483647)
    @Column(name = "hims_code")
    private String himsCode;
    @Size(max = 2147483647)
    @Column(name = "smartcare_code")
    private String smartcareCode;
    @Size(max = 2147483647)
    @Column(name = "elmis_code")
    private String elmisCode;
    @Size(max = 2147483647)
    @Column(name = "hpcz_code")
    private String hpczCode;
    @Size(max = 2147483647)
    @Column(name = "disa_code")
    private String disaCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "name")
    private String name;
    @Size(max = 2147483647)
    @Column(name = "catchment_population_head_count")
    private String catchmentPopulationHeadCount;
    @Size(max = 2147483647)
    @Column(name = "catchment_population_cso")
    private String catchmentPopulationCso;
    @Size(max = 2147483647)
    @Column(name = "number_of_households")
    private String numberOfHouseholds;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mobility_status")
    private Integer mobilityStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "ownership_type")
    private String ownershipType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "accesibility")
    private String accesibility;
    @Size(max = 2147483647)
    @Column(name = "latitude")
    private String latitude;
    @Size(max = 2147483647)
    @Column(name = "longitude")
    private String longitude;
    @Lob
    @Column(name = "geom")
    private Object geom;
    @Column(name = "status")
    private Integer status;
    @Column(name = "date_approved")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateApproved;
    @Column(name = "approved_by")
    private Integer approvedBy;
    @Basic(optional = false)
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @Column(name = "created_by")
    private int createdBy;
    @Column(name = "date_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;
    @Column(name = "updated_by")
    private Integer updatedBy;
    @Column(name = "date_verified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateVerified;
    @Column(name = "verified_by")
    private Integer verifiedBy;
    @Size(max = 2147483647)
    @Column(name = "verifier_comments")
    private String verifierComments;
    @Size(max = 2147483647)
    @Column(name = "approver_comments")
    private String approverComments;
    @Column(name = "province_approval_status")
    private Integer provinceApprovalStatus;
    @Column(name = "national_approval_status")
    private Integer nationalApprovalStatus;

//    @JoinColumn(name = "operational_status", referencedColumnName = "id")
//    @ManyToOne(optional = false)
//    private Operationstatus operationalStatus;
//    @JoinColumn(name = "type", referencedColumnName = "id")
//    @ManyToOne(optional = false)
//    private Facilitytypes type;
//    @JoinColumn(name = "district_id", referencedColumnName = "id")
//    @ManyToOne(optional = false)
//    private Districts districtId;
//    @JoinColumn(name = "location", referencedColumnName = "id")
//    @ManyToOne(optional = false)
//    private Locationtype location;
    @Column(name = "type")
    private Integer type;
    @Column(name = "location")
    private Integer location;
    @Column(name = "district_id")
    private Integer district;
    @Column(name = "ownership")
    private Integer ownership;
    @Column(name = "operational_status")
    private Integer operationalStatus;
//    @JoinColumn(name = "ownership", referencedColumnName = "id")
//    @ManyToOne(optional = false)
//    private Ownership_1 ownership;
    @Column(name = "zone_id")
    private Integer zoneId;

    public Facility() {
    }

    public Facility(Integer id) {
        this.id = id;
    }

    public Facility(Integer id, String name, int mobilityStatus, String ownershipType, String accesibility, Date dateCreated, int createdBy) {
        this.id = id;
        this.name = name;
        this.mobilityStatus = mobilityStatus;
        this.ownershipType = ownershipType;
        this.accesibility = accesibility;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConstituencyId() {
        return constituencyId;
    }

    public void setConstituencyId(Integer constituencyId) {
        this.constituencyId = constituencyId;
    }

    public Integer getWardId() {
        return wardId;
    }

    public void setWardId(Integer wardId) {
        this.wardId = wardId;
    }

    public String getHimsCode() {
        return himsCode;
    }

    public void setHimsCode(String himsCode) {
        this.himsCode = himsCode;
    }

    public String getSmartcareCode() {
        return smartcareCode;
    }

    public void setSmartcareCode(String smartcareCode) {
        this.smartcareCode = smartcareCode;
    }

    public String getElmisCode() {
        return elmisCode;
    }

    public void setElmisCode(String elmisCode) {
        this.elmisCode = elmisCode;
    }

    public String getHpczCode() {
        return hpczCode;
    }

    public void setHpczCode(String hpczCode) {
        this.hpczCode = hpczCode;
    }

    public String getDisaCode() {
        return disaCode;
    }

    public void setDisaCode(String disaCode) {
        this.disaCode = disaCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchmentPopulationHeadCount() {
        return catchmentPopulationHeadCount;
    }

    public void setCatchmentPopulationHeadCount(String catchmentPopulationHeadCount) {
        this.catchmentPopulationHeadCount = catchmentPopulationHeadCount;
    }

    public String getCatchmentPopulationCso() {
        return catchmentPopulationCso;
    }

    public void setCatchmentPopulationCso(String catchmentPopulationCso) {
        this.catchmentPopulationCso = catchmentPopulationCso;
    }

    public String getNumberOfHouseholds() {
        return numberOfHouseholds;
    }

    public void setNumberOfHouseholds(String numberOfHouseholds) {
        this.numberOfHouseholds = numberOfHouseholds;
    }

    public Integer getMobilityStatus() {
        return mobilityStatus;
    }

    public void setMobilityStatus(Integer mobilityStatus) {
        this.mobilityStatus = mobilityStatus;
    }

    public String getOwnershipType() {
        return ownershipType;
    }

    public void setOwnershipType(String ownershipType) {
        this.ownershipType = ownershipType;
    }

    public String getAccesibility() {
        return accesibility;
    }

    public void setAccesibility(String accesibility) {
        this.accesibility = accesibility;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Object getGeom() {
        return geom;
    }

    public void setGeom(Object geom) {
        this.geom = geom;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDateApproved() {
        return dateApproved;
    }

    public void setDateApproved(Date dateApproved) {
        this.dateApproved = dateApproved;
    }

    public Integer getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Integer approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getDateVerified() {
        return dateVerified;
    }

    public void setDateVerified(Date dateVerified) {
        this.dateVerified = dateVerified;
    }

    public Integer getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(Integer verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public String getVerifierComments() {
        return verifierComments;
    }

    public void setVerifierComments(String verifierComments) {
        this.verifierComments = verifierComments;
    }

    public String getApproverComments() {
        return approverComments;
    }

    public void setApproverComments(String approverComments) {
        this.approverComments = approverComments;
    }

    public Integer getProvinceApprovalStatus() {
        return provinceApprovalStatus;
    }

    public void setProvinceApprovalStatus(Integer provinceApprovalStatus) {
        this.provinceApprovalStatus = provinceApprovalStatus;
    }

    public Integer getNationalApprovalStatus() {
        return nationalApprovalStatus;
    }

    public void setNationalApprovalStatus(Integer nationalApprovalStatus) {
        this.nationalApprovalStatus = nationalApprovalStatus;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
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
        if (!(object instanceof Facility)) {
            return false;
        }
        Facility other = (Facility) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.moh.mfl.model.Facility[ id=" + id + " ]";
    }

    /**
     * @return the location
     */
    public Integer getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Integer location) {
        this.location = location;
    }

    /**
     * @return the district
     */
    public Integer getDistrict() {
        return district;
    }

    /**
     * @param district the district to set
     */
    public void setDistrict(Integer district) {
        this.district = district;
    }

    /**
     * @return the ownership
     */
    public Integer getOwnership() {
        return ownership;
    }

    /**
     * @param ownership the ownership to set
     */
    public void setOwnership(Integer ownership) {
        this.ownership = ownership;
    }

    /**
     * @return the operationalStatus
     */
    public Integer getOperationalStatus() {
        return operationalStatus;
    }

    /**
     * @param operationalStatus the operationalStatus to set
     */
    public void setOperationalStatus(Integer operationalStatus) {
        this.operationalStatus = operationalStatus;
    }

}
