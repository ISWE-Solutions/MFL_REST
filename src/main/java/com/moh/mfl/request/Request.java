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
    @NotBlank(message = "Name is required")
    private String name;
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
    @NotNull(message = "Facility location is required")
    private Integer FacilityLocation;
    @NotNull(message = "Accessibility is required")
    private String accessibility;

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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

}
