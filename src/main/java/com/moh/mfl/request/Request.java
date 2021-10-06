package com.moh.mfl.request;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Francis chulu
 */
public class Request implements Serializable {

    @NotBlank(message = "District is required")
    private String district;
    @NotBlank(message = "Facility name is required")
    private String facilityName;
    @NotBlank(message = "Operational status is required")
    private Integer operationalStatus;
    @NotBlank(message = "Facility type is required")
    private Integer FacilityType;
    @NotNull(message = "Mobility status is required")
    private Integer mobilityStatus;
    @NotNull(message = "HPCZ Code is required")
    private String hpczCode;
    @NotNull(message = "Ownership is required")
    private Integer ownership;
    @NotNull(message = "Facility location is required (1=Rural,2=Urban)")
    private Integer FacilityLocation;
    @NotNull(message = "Accessibility is required")
    private String accessibility;
    private String email;
    private String mobileNumber;
    private String telephone;
    private String postalAddress;
    private String plotNo;
    private String street;
    private String town;
    private String fax;
    private String physicalAddress;
    private String services;

    /**
     * @return the district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @param district the district to set
     */
    public void setDistrict(String district) {
        this.district = district;
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
     * @return the FacilityType
     */
    public Integer getFacilityType() {
        return FacilityType;
    }

    /**
     * @param FacilityType the FacilityType to set
     */
    public void setFacilityType(Integer FacilityType) {
        this.FacilityType = FacilityType;
    }

    /**
     * @return the mobilityStatus
     */
    public Integer getMobilityStatus() {
        return mobilityStatus;
    }

    /**
     * @param mobilityStatus the mobilityStatus to set
     */
    public void setMobilityStatus(Integer mobilityStatus) {
        this.mobilityStatus = mobilityStatus;
    }

    /**
     * @return the hpczCode
     */
    public String getHpczCode() {
        return hpczCode;
    }

    /**
     * @param hpczCode the hpczCode to set
     */
    public void setHpczCode(String hpczCode) {
        this.hpczCode = hpczCode;
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
     * @return the FacilityLocation
     */
    public Integer getFacilityLocation() {
        return FacilityLocation;
    }

    /**
     * @param FacilityLocation the FacilityLocation to set
     */
    public void setFacilityLocation(Integer FacilityLocation) {
        this.FacilityLocation = FacilityLocation;
    }

    /**
     * @return the accessibility
     */
    public String getAccessibility() {
        return accessibility;
    }

    /**
     * @param accessibility the accessibility to set
     */
    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
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

    /**
     * @return the services
     */
    public String getServices() {
        return services;
    }

    /**
     * @param services the services to set
     */
    public void setServices(String services) {
        this.services = services;
    }

}
