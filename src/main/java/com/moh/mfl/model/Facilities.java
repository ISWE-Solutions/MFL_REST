package com.moh.mfl.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author chulu
 */
@Entity
@Table(name = "facility")
public class Facilities implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ward")
    private String ward;
    @Column(name = "constituency")
    private String constituency;
    @Column(name = "district")
    private String district;
    @Column(name = "province")
    private String province;
    @Column(name = "hims_code")
    private String himsCode;
    @Column(name = "smartcare_code")
    private String smartcareCode;
    @Column(name = "elmis_code")
    private String elmisCode;
    @Column(name = "hpcz_code")
    private String hpczCode;
    @Column(name = "disa_code")
    private String disaCode;
    @Column(name = "name")
    private String name;

    @Column(name = "catchment_population_head_count")
    private String catchmentPopulationHeadCount;

    @Column(name = "catchment_population_cso")
    private String catchmentPopulationCso;

    @Column(name = "number_of_households")
    private String numberOfHouseholds;
    @Column(name = "location_type")
    private String locationType;
    @Column(name = "operation_status")
    private String operationStatus;
    @Column(name = "facility_type")
    private String facilityType;
    @Column(name = "ownership")
    private String ownersip;
    @Column(name = "mobility_status")
    private String mobilityStatus;
    @Column(name = "ownership_type")
    private String ownershipType;
 
    @Column(name = "accesibility")
    private String accesibility;
 
    @Column(name = "latitude")
    private String latitude;
  
    @Column(name = "longitude")
    private String longitude;
    @Column(name = "geom")
    private String geom;
//    @Column(name = "status")
//    private Integer status;
//    @Column(name = "date_approved")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date dateApproved;
//    @Column(name = "approved_by")
//    private Integer approvedBy;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "date_created")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date dateCreated;
//    @Column(name = "created_by")
//    private Integer createdBy;
//    @Column(name = "date_updated")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date dateUpdated;
//    @Column(name = "updated_by")
//    private Integer updatedBy;
//    @Column(name = "date_verified")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date dateVerified;
//    @Column(name = "verified_by")
//    private Integer verifiedBy;
//    @Size(max = 2147483647)
//    @Column(name = "verifier_comments")
//    private String verifierComments;
//    @Size(max = 2147483647)
//    @Column(name = "approver_comments")
//    private String approverComments;
//    @Column(name = "province_approval_status")
//    private Integer provinceApprovalStatus;
//    @Column(name = "national_approval_status")
//    private Integer nationalApprovalStatus;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
   
    @Column(name = "email")
    private String email;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "phone")
    private String phone;
   
    @Column(name = "plot_no")
    private String plotNo;
   
    @Column(name = "street")
    private String street;
   
    @Column(name = "town")
    private String town;
 
    @Column(name = "postal_address")
    private String postalAddress;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
  
    @Column(name = "fax")
    private String fax;
    
    @Column(name = "physical_address")
    private String physicalAddress;

    public Facilities() {
    }

    public Facilities(Integer id) {
        this.id = id;
    }

    public Facilities(Integer id, String name, String mobilityStatus, String ownershipType, String accesibility) {
        this.id = id;
        this.name = name;
        this.mobilityStatus = mobilityStatus;
        this.ownershipType = ownershipType;
        this.accesibility = accesibility;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getMobilityStatus() {
        return mobilityStatus;
    }

    public void setMobilityStatus(String mobilityStatus) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPlotNo() {
        return plotNo;
    }

    public void setPlotNo(String plotNo) {
        this.plotNo = plotNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
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
        if (!(object instanceof Facilities)) {
            return false;
        }
        Facilities other = (Facilities) object;
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
     * @return the locationType
     */
    public String getLocationType() {
        return locationType;
    }

    /**
     * @param locationType the locationType to set
     */
    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    /**
     * @return the operationStatus
     */
    public String getOperationStatus() {
        return operationStatus;
    }

    /**
     * @param operationStatus the operationStatus to set
     */
    public void setOperationStatus(String operationStatus) {
        this.operationStatus = operationStatus;
    }

    /**
     * @return the facilityType
     */
    public String getFacilityType() {
        return facilityType;
    }

    /**
     * @param facilityType the facilityType to set
     */
    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }

    /**
     * @return the ownersip
     */
    public String getOwnersip() {
        return ownersip;
    }

    /**
     * @param ownersip the ownersip to set
     */
    public void setOwnersip(String ownersip) {
        this.ownersip = ownersip;
    }

    /**
     * @return the ward
     */
    public String getWard() {
        return ward;
    }

    /**
     * @param ward the ward to set
     */
    public void setWard(String ward) {
        this.ward = ward;
    }

    /**
     * @return the constituency
     */
    public String getConstituency() {
        return constituency;
    }

    /**
     * @param constituency the constituency to set
     */
    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }

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
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

}
