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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author chulu
 */
@Entity
@Table(name = "facility")
public class FacilitySave implements Serializable {

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
    private String facilityName;
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
    @Column(name = "status")
    private Integer status;
    @Basic(optional = false)
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
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
    @Column(name = "email")
    private String email;
    @Column(name = "mobile")
    private String mobileNumber;
    @Column(name = "phone")
    private String telephone;
    @Column(name = "postal_address")
    private String postalAddress;
    @Column(name = "plot_no")
    private String plotNo;
    @Column(name = "street")
    private String street;
    @Column(name = "town")
    private String town;
    @Column(name = "fax")
    private String fax;
    @Column(name = "physical_address")
    private String physicalAddress;

    public FacilitySave() {
    }

    public FacilitySave(Integer id) {
        this.id = id;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    /**
     * @return the facilityName
     */
    public String getFacilityName() {
        return facilityName;
    }

    /**
     * @param facilityName the facilityName to set
     */
    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * @param mobileNumber the mobileNumber to set
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the postalAddress
     */
    public String getPostalAddress() {
        return postalAddress;
    }

    /**
     * @param postalAddress the postalAddress to set
     */
    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    /**
     * @return the plotNo
     */
    public String getPlotNo() {
        return plotNo;
    }

    /**
     * @param plotNo the plotNo to set
     */
    public void setPlotNo(String plotNo) {
        this.plotNo = plotNo;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the town
     */
    public String getTown() {
        return town;
    }

    /**
     * @param town the town to set
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return the physicalAddress
     */
    public String getPhysicalAddress() {
        return physicalAddress;
    }

    /**
     * @param physicalAddress the physicalAddress to set
     */
    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

}
