package com.moh.mfl.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author Francis Chulu
 */
@Entity
@Table(name = "MFL_facility")
@EntityListeners(AuditingEntityListener.class)
public class Facilities implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank
    @Column(name = "name")
    private String name;
    @Column(name = "DHIS2_UID")
    private String dhis2Uid;
    @Column(name = "HMIS_code")
    private String hmisUid;
    @Column(name = "smartcare_GUID")
    private String smartcareGuid;
    @Column(name = "eLMIS_ID")
    private String elmisId;
    @Column(name = "iHRIS_ID")
    private String ihrisId;
    @Column(name = "ownership")
    private String ownersip;
    @Column(name = "operation_status")
    private String operationStatus;
    @Column(name = "facility_type")
    private String facilityType;
    @Column(name = "number_of_beds")
    private Integer numberOfBeds;
    @Column(name = "number_of_cots")
    private Integer numberOfCots;
    @Column(name = "number_of_nurses")
    private Integer numberOfNurses;
    @Column(name = "number_of_doctors")
    private Integer numberOfDoctors;
    @Column(name = "address_line1")
    private String addressLine;
    @Column(name = "address_line2")
    private String addressLine2;
    @Column(name = "postal_address")
    private String postalAddress;
    @Column(name = "web_address")
    private String webAddress;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "fax")
    private String fax;
    @Column(name = "catchment_population_head_count")
    private Integer catchmentPopulationHeadCount;
    @Column(name = "catchment_population_cso")
    private Integer catchmentPopulationCso;
    @Column(name = "longitude")
    private String longitude;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "geom")
    private String geom;
    @Column(name = "location_type")
    private String locationType;
    @Column(name = "ward")
    private String ward;
    @Column(name = "constituency")
    private String constituency;
    @Column(name = "district")
    private String district;
    @Column(name = "province")
    private String province;
    @Column(name = "number_of_paramedics")
    private Integer numberOfParamedics;
    @Column(name = "number_of_midwives")
    private Integer numberOfMidwives;

    public Facilities() {
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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
     * @return the dhis2Uid
     */
    public String getDhis2Uid() {
        return dhis2Uid;
    }

    /**
     * @param dhis2Uid the dhis2Uid to set
     */
    public void setDhis2Uid(String dhis2Uid) {
        this.dhis2Uid = dhis2Uid;
    }

    /**
     * @return the hmisUid
     */
    public String getHmisUid() {
        return hmisUid;
    }

    /**
     * @param hmisUid the hmisUid to set
     */
    public void setHmisUid(String hmisUid) {
        this.hmisUid = hmisUid;
    }

    /**
     * @return the smartcareGuid
     */
    public String getSmartcareGuid() {
        return smartcareGuid;
    }

    /**
     * @param smartcareGuid the smartcareGuid to set
     */
    public void setSmartcareGuid(String smartcareGuid) {
        this.smartcareGuid = smartcareGuid;
    }

    /**
     * @return the elmisId
     */
    public String getElmisId() {
        return elmisId;
    }

    /**
     * @param elmisId the elmisId to set
     */
    public void setElmisId(String elmisId) {
        this.elmisId = elmisId;
    }

    /**
     * @return the ihrisId
     */
    public String getIhrisId() {
        return ihrisId;
    }

    /**
     * @param ihrisId the ihrisId to set
     */
    public void setIhrisId(String ihrisId) {
        this.ihrisId = ihrisId;
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
     * @return the numberOfBeds
     */
    public Integer getNumberOfBeds() {
        return numberOfBeds;
    }

    /**
     * @param numberOfBeds the numberOfBeds to set
     */
    public void setNumberOfBeds(Integer numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    /**
     * @return the numberOfCots
     */
    public Integer getNumberOfCots() {
        return numberOfCots;
    }

    /**
     * @param numberOfCots the numberOfCots to set
     */
    public void setNumberOfCots(Integer numberOfCots) {
        this.numberOfCots = numberOfCots;
    }

    /**
     * @return the numberOfNurses
     */
    public Integer getNumberOfNurses() {
        return numberOfNurses;
    }

    /**
     * @param numberOfNurses the numberOfNurses to set
     */
    public void setNumberOfNurses(Integer numberOfNurses) {
        this.numberOfNurses = numberOfNurses;
    }

    /**
     * @return the numberOfDoctors
     */
    public Integer getNumberOfDoctors() {
        return numberOfDoctors;
    }

    /**
     * @param numberOfDoctors the numberOfDoctors to set
     */
    public void setNumberOfDoctors(Integer numberOfDoctors) {
        this.numberOfDoctors = numberOfDoctors;
    }

    /**
     * @return the addressLine
     */
    public String getAddressLine() {
        return addressLine;
    }

    /**
     * @param addressLine the addressLine to set
     */
    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    /**
     * @return the addressLine2
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * @param addressLine2 the addressLine2 to set
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
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
     * @return the webAddress
     */
    public String getWebAddress() {
        return webAddress;
    }

    /**
     * @param webAddress the webAddress to set
     */
    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
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
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
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
     * @return the catchmentPopulationHeadCount
     */
    public Integer getCatchmentPopulationHeadCount() {
        return catchmentPopulationHeadCount;
    }

    /**
     * @param catchmentPopulationHeadCount the catchmentPopulationHeadCount to
     * set
     */
    public void setCatchmentPopulationHeadCount(Integer catchmentPopulationHeadCount) {
        this.catchmentPopulationHeadCount = catchmentPopulationHeadCount;
    }

    /**
     * @return the catchmentPopulationCso
     */
    public Integer getCatchmentPopulationCso() {
        return catchmentPopulationCso;
    }

    /**
     * @param catchmentPopulationCso the catchmentPopulationCso to set
     */
    public void setCatchmentPopulationCso(Integer catchmentPopulationCso) {
        this.catchmentPopulationCso = catchmentPopulationCso;
    }

    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the geom
     */
    public String getGeom() {
        return geom;
    }

    /**
     * @param geom the geom to set
     */
    public void setGeom(String geom) {
        this.geom = geom;
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

    /**
     * @return the numberOfParamedics
     */
    public Integer getNumberOfParamedics() {
        return numberOfParamedics;
    }

    /**
     * @param numberOfParamedics the numberOfParamedics to set
     */
    public void setNumberOfParamedics(Integer numberOfParamedics) {
        this.numberOfParamedics = numberOfParamedics;
    }

    /**
     * @return the numberOfMidwives
     */
    public Integer getNumberOfMidwives() {
        return numberOfMidwives;
    }

    /**
     * @param numberOfMidwives the numberOfMidwives to set
     */
    public void setNumberOfMidwives(Integer numberOfMidwives) {
        this.numberOfMidwives = numberOfMidwives;
    }

    

}
