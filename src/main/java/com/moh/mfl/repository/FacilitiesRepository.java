package com.moh.mfl.repository;

import com.moh.mfl.model.Facilities;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Francis Chulu
 */
@Repository
public interface FacilitiesRepository extends JpaRepository<Facilities, Long> {

    /**
     *
     * @param longitude
     * @param latitude
     * @return list
     */
    @Query(value = "SELECT f.id,f.name,f.\"DHIS2_UID\", f.\"HMIS_code\",f.\"smartcare_GUID\",f.\"eLMIS_ID\",\n"
            + "f.\"iHRIS_ID\",f.number_of_beds,f.number_of_cots,f.number_of_nurses,f.number_of_doctors,\n"
            + "f.address_line1,f.address_line2,f.postal_address,f.web_address,f.email,\n"
            + "f.phone,f.mobile,f.fax,f.catchment_population_head_count,f.catchment_population_cso,\n"
            + "f.number_of_paramedics,f.number_of_midwives,w.name as ward,c.name as constituency,\n"
            + "ft.name as facility_type,lt.name as location_type,ow.name as ownership,\n"
            + "os.name as operation_status,d.name as district,\n"
            + "f.longitude,f.latitude,ST_AsGeoJSON(f.geom) as geom,p.name as province \n"
            + "FROM \"MFL_facility\" f LEFT JOIN \"geography_ward\" w ON f.ward_id=w.id \n"
            + "LEFT JOIN \"geography_constituency\" c ON f.constituency_id=c.id\n"
            + "LEFT JOIN \"MFL_facilitytype\" ft ON f.facility_type_id=ft.id\n"
            + "LEFT JOIN \"geography_locationtype\" lt ON f.location_type_id=lt.id\n"
            + "LEFT JOIN \"MFL_ownership\" ow ON f.ownership_id=ow.id\n"
            + "LEFT JOIN \"MFL_operationstatus\" os ON f.operation_status_id=os.id\n"
            + "LEFT JOIN \"geography_district\" d ON f.district_id=d.id\n"
            + "LEFT JOIN \"geography_province\" p ON d.province_id=p.id\n"
            + "ORDER BY f.geom <-> ST_GeomFromText ('POINT(' ||:lng || ' ' || :lat ||')', 4326)\n"
            //+ "WHERE ST_DWithin(f.geom, ST_GeogFromText('POINT(' ||:lng ||' ' || :lat ||')'), 10000, false)\n"
            + "LIMIT 10;",
            nativeQuery = true)

    List<Facilities> findByLongitudeAndLatitude(@Param("lng") String longitude, @Param("lat") String latitude);

    /**
     *
     * @param name
     * @return list
     */
    @Query(value = "SELECT f.id,f.name,f.\"DHIS2_UID\", f.\"HMIS_code\",f.\"smartcare_GUID\",f.\"eLMIS_ID\",\n"
            + "f.\"iHRIS_ID\",f.number_of_beds,f.number_of_cots,f.number_of_nurses,f.number_of_doctors,\n"
            + "f.address_line1,f.address_line2,f.postal_address,f.web_address,f.email,\n"
            + "f.phone,f.mobile,f.fax,f.catchment_population_head_count,f.catchment_population_cso,"
            + "f.number_of_paramedics,f.number_of_midwives,\n"
            + "f.longitude,f.latitude,ST_AsGeoJSON(f.geom) as geom,w.name as ward,c.name as constituency,\n"
            + "ft.name as facility_type,lt.name as location_type,ow.name as ownership,\n"
            + "os.name as operation_status,d.name as district,p.name as province \n"
            + "FROM \"MFL_facility\" f LEFT JOIN \"geography_ward\" w ON f.ward_id=w.id \n"
            + "LEFT JOIN \"geography_constituency\" c ON f.constituency_id=c.id \n"
            + "LEFT JOIN \"MFL_facilitytype\" ft ON f.facility_type_id=ft.id \n"
            + "LEFT JOIN \"geography_locationtype\" lt ON f.location_type_id=lt.id\n"
            + "LEFT JOIN \"MFL_ownership\" ow ON f.ownership_id=ow.id\n"
            + "LEFT JOIN \"MFL_operationstatus\" os ON f.operation_status_id=os.id\n"
            + "LEFT JOIN \"geography_district\" d ON f.district_id=d.id\n"
            + "LEFT JOIN \"geography_province\" p ON d.province_id=p.id\n"
            + "WHERE f.name ILIKE %:name% ORDER BY f.id DESC LIMIT 5", nativeQuery = true)
    List<Facilities> findByName(@Param("name") String name);
    /**
     *
     * @param name
     * @param districtId
     * @return list
     */
    @Query(value = "SELECT f.id,f.name,f.\"DHIS2_UID\", f.\"HMIS_code\",f.\"smartcare_GUID\",f.\"eLMIS_ID\",\n"
            + "f.\"iHRIS_ID\",f.number_of_beds,f.number_of_cots,f.number_of_nurses,f.number_of_doctors,\n"
            + "f.address_line1,f.address_line2,f.postal_address,f.web_address,f.email,\n"
            + "f.phone,f.mobile,f.fax,f.catchment_population_head_count,f.catchment_population_cso,"
            + "f.number_of_paramedics,f.number_of_midwives,\n"
            + "f.longitude,f.latitude,ST_AsGeoJSON(f.geom) as geom,w.name as ward,c.name as constituency,\n"
            + "ft.name as facility_type,lt.name as location_type,ow.name as ownership,\n"
            + "os.name as operation_status,d.name as district,p.name as province \n"
            + "FROM \"MFL_facility\" f LEFT JOIN \"geography_ward\" w ON f.ward_id=w.id \n"
            + "LEFT JOIN \"geography_constituency\" c ON f.constituency_id=c.id \n"
            + "LEFT JOIN \"MFL_facilitytype\" ft ON f.facility_type_id=ft.id \n"
            + "LEFT JOIN \"geography_locationtype\" lt ON f.location_type_id=lt.id\n"
            + "LEFT JOIN \"MFL_ownership\" ow ON f.ownership_id=ow.id\n"
            + "LEFT JOIN \"MFL_operationstatus\" os ON f.operation_status_id=os.id\n"
            + "LEFT JOIN \"geography_district\" d ON f.district_id=d.id\n"
            + "LEFT JOIN \"geography_province\" p ON d.province_id=p.id\n"
            + "WHERE f.name ILIKE %:name% AND f.district_id=:districtId ORDER BY f.id DESC LIMIT 5", nativeQuery = true)
    List<Facilities> findByNameAndDistrictId(@Param("name") String name,@Param("districtId") Long districtId);

    /**
     *
     * @param id
     * @return list
     */
    @Query(value = "SELECT f.id,f.name,f.\"DHIS2_UID\", f.\"HMIS_code\",f.\"smartcare_GUID\",f.\"eLMIS_ID\",\n"
            + "f.\"iHRIS_ID\",f.number_of_beds,f.number_of_cots,f.number_of_nurses,f.number_of_doctors,\n"
            + "f.address_line1,f.address_line2,f.postal_address,f.web_address,f.email,\n"
            + "f.phone,f.mobile,f.fax,f.catchment_population_head_count,f.catchment_population_cso,\n"
            + "f.number_of_paramedics,f.number_of_midwives,\n"
            + "f.longitude,f.latitude,ST_AsGeoJSON(f.geom) as geom,w.name as ward,c.name as constituency,\n"
            + "ft.name as facility_type,lt.name as location_type,ow.name as ownership,\n"
            + "os.name as operation_status,d.name as district,p.name as province \n"
            + "FROM \"MFL_facility\" f LEFT JOIN \"geography_ward\" w ON f.ward_id=w.id \n"
            + "LEFT JOIN \"geography_constituency\" c ON f.constituency_id=c.id \n"
            + "LEFT JOIN \"MFL_facilitytype\" ft ON f.facility_type_id=ft.id \n"
            + "LEFT JOIN \"geography_locationtype\" lt ON f.location_type_id=lt.id\n"
            + "LEFT JOIN \"MFL_ownership\" ow ON f.ownership_id=ow.id\n"
            + "LEFT JOIN \"MFL_operationstatus\" os ON f.operation_status_id=os.id\n"
            + "LEFT JOIN \"geography_district\" d ON f.district_id=d.id\n"
            + "LEFT JOIN \"geography_province\" p ON d.province_id=p.id\n"
            + "WHERE f.ward_id = :id ORDER BY f.id DESC", nativeQuery = true)
    List<Facilities> findByWardId(@Param("id") Long id);

    /**
     *
     * @param id
     * @return list
     */
    @Query(value = "SELECT f.id,f.name,f.\"DHIS2_UID\", f.\"HMIS_code\",f.\"smartcare_GUID\",f.\"eLMIS_ID\",\n"
            + "f.\"iHRIS_ID\",f.number_of_beds,f.number_of_cots,f.number_of_nurses,f.number_of_doctors,\n"
            + "f.address_line1,f.address_line2,f.postal_address,f.web_address,f.email,\n"
            + "f.phone,f.mobile,f.fax,f.catchment_population_head_count,f.catchment_population_cso,\n"
            + "f.number_of_paramedics,f.number_of_midwives,\n"
            + "f.longitude,f.latitude,ST_AsGeoJSON(f.geom) as geom,w.name as ward,c.name as constituency,\n"
            + "ft.name as facility_type,lt.name as location_type,ow.name as ownership,\n"
            + "os.name as operation_status,d.name as district,p.name as province \n"
            + "FROM \"MFL_facility\" f LEFT JOIN \"geography_ward\" w ON f.ward_id=w.id \n"
            + "LEFT JOIN \"geography_constituency\" c ON f.constituency_id=c.id \n"
            + "LEFT JOIN \"MFL_facilitytype\" ft ON f.facility_type_id=ft.id \n"
            + "LEFT JOIN \"geography_locationtype\" lt ON f.location_type_id=lt.id\n"
            + "LEFT JOIN \"MFL_ownership\" ow ON f.ownership_id=ow.id\n"
            + "LEFT JOIN \"MFL_operationstatus\" os ON f.operation_status_id=os.id\n"
            + "LEFT JOIN \"geography_district\" d ON f.district_id=d.id\n"
            + "LEFT JOIN \"geography_province\" p ON d.province_id=p.id\n"
            + "WHERE f.constituency_id = :id ORDER BY f.id DESC", nativeQuery = true)
    List<Facilities> findByConstituencyId(@Param("id") Long id);

    /**
     *
     * @param id
     * @return list
     */
    @Query(value = "SELECT f.id,f.name,f.\"DHIS2_UID\", f.\"HMIS_code\",f.\"smartcare_GUID\",f.\"eLMIS_ID\",\n"
            + "f.\"iHRIS_ID\",f.number_of_beds,f.number_of_cots,f.number_of_nurses,f.number_of_doctors,\n"
            + "f.address_line1,f.address_line2,f.postal_address,f.web_address,f.email,\n"
            + "f.phone,f.mobile,f.fax,f.catchment_population_head_count,f.catchment_population_cso,\n"
            + "f.number_of_paramedics,f.number_of_midwives,\n"
            + "f.longitude,f.latitude,ST_AsGeoJSON(f.geom) as geom,w.name as ward,c.name as constituency,\n"
            + "ft.name as facility_type,lt.name as location_type,ow.name as ownership,\n"
            + "os.name as operation_status,d.name as district,p.name as province \n"
            + "FROM \"MFL_facility\" f LEFT JOIN \"geography_ward\" w ON f.ward_id=w.id \n"
            + "LEFT JOIN \"geography_constituency\" c ON f.constituency_id=c.id \n"
            + "LEFT JOIN \"MFL_facilitytype\" ft ON f.facility_type_id=ft.id \n"
            + "LEFT JOIN \"geography_locationtype\" lt ON f.location_type_id=lt.id\n"
            + "LEFT JOIN \"MFL_ownership\" ow ON f.ownership_id=ow.id\n"
            + "LEFT JOIN \"MFL_operationstatus\" os ON f.operation_status_id=os.id\n"
            + "LEFT JOIN \"geography_district\" d ON f.district_id=d.id\n"
            + "LEFT JOIN \"geography_province\" p ON d.province_id=p.id\n"
            + "WHERE f.district_id = :id ORDER BY f.id DESC", nativeQuery = true)
    List<Facilities> findByDistrictId(@Param("id") Long id);

    /**
     *
     * @param id
     * @return list
     */
    @Query(value = "SELECT f.id,f.name,f.\"DHIS2_UID\", f.\"HMIS_code\",f.\"smartcare_GUID\",f.\"eLMIS_ID\",\n"
            + "f.\"iHRIS_ID\",f.number_of_beds,f.number_of_cots,f.number_of_nurses,f.number_of_doctors,\n"
            + "f.address_line1,f.address_line2,f.postal_address,f.web_address,f.email,\n"
            + "f.phone,f.mobile,f.fax,f.catchment_population_head_count,f.catchment_population_cso,\n"
            + "f.number_of_paramedics,f.number_of_midwives,\n"
            + "f.longitude,f.latitude,ST_AsGeoJSON(f.geom) as geom,w.name as ward,c.name as constituency,\n"
            + "ft.name as facility_type,lt.name as location_type,ow.name as ownership,\n"
            + "os.name as operation_status,d.name as district,p.name as province \n"
            + "FROM \"MFL_facility\" f LEFT JOIN \"geography_ward\" w ON f.ward_id=w.id \n"
            + "LEFT JOIN \"geography_constituency\" c ON f.constituency_id=c.id \n"
            + "LEFT JOIN \"MFL_facilitytype\" ft ON f.facility_type_id=ft.id \n"
            + "LEFT JOIN \"geography_locationtype\" lt ON f.location_type_id=lt.id\n"
            + "LEFT JOIN \"MFL_ownership\" ow ON f.ownership_id=ow.id\n"
            + "LEFT JOIN \"MFL_operationstatus\" os ON f.operation_status_id=os.id\n"
            + "LEFT JOIN \"geography_district\" d ON f.district_id=d.id\n"
            + "LEFT JOIN \"geography_province\" p ON d.province_id=p.id\n"
            + "WHERE f.facility_type_id = :id ORDER BY f.id DESC", nativeQuery = true)
    List<Facilities> findByFacilityTypeId(@Param("id") Long id);

    /**
     *
     * @param id
     * @return list
     */
    @Query(value = "SELECT f.id,f.name,f.\"DHIS2_UID\", f.\"HMIS_code\",f.\"smartcare_GUID\",f.\"eLMIS_ID\",\n"
            + "f.\"iHRIS_ID\",f.number_of_beds,f.number_of_cots,f.number_of_nurses,f.number_of_doctors,\n"
            + "f.address_line1,f.address_line2,f.postal_address,f.web_address,f.email,\n"
            + "f.phone,f.mobile,f.fax,f.catchment_population_head_count,f.catchment_population_cso,\n"
            + "f.number_of_paramedics,f.number_of_midwives,\n"
            + "f.longitude,f.latitude,ST_AsGeoJSON(f.geom) as geom,w.name as ward,c.name as constituency,\n"
            + "ft.name as facility_type,lt.name as location_type,ow.name as ownership,\n"
            + "os.name as operation_status,d.name as district,p.name as province \n"
            + "FROM \"MFL_facility\" f LEFT JOIN \"geography_ward\" w ON f.ward_id=w.id \n"
            + "LEFT JOIN \"geography_constituency\" c ON f.constituency_id=c.id \n"
            + "LEFT JOIN \"MFL_facilitytype\" ft ON f.facility_type_id=ft.id \n"
            + "LEFT JOIN \"geography_locationtype\" lt ON f.location_type_id=lt.id\n"
            + "LEFT JOIN \"MFL_ownership\" ow ON f.ownership_id=ow.id\n"
            + "LEFT JOIN \"MFL_operationstatus\" os ON f.operation_status_id=os.id\n"
            + "LEFT JOIN \"geography_district\" d ON f.district_id=d.id\n"
            + "LEFT JOIN \"geography_province\" p ON d.province_id=p.id\n"
            + "WHERE f.ownership_id = :id ORDER BY f.id DESC", nativeQuery = true)
    List<Facilities> findByOwnershipId(@Param("id") Long id);

    /**
     *
     * @param id
     * @return list
     */
    @Query(value = "SELECT f.id,f.name,f.\"DHIS2_UID\", f.\"HMIS_code\",f.\"smartcare_GUID\",f.\"eLMIS_ID\",\n"
            + "f.\"iHRIS_ID\",f.number_of_beds,f.number_of_cots,f.number_of_nurses,f.number_of_doctors,\n"
            + "f.address_line1,f.address_line2,f.postal_address,f.web_address,f.email,\n"
            + "f.phone,f.mobile,f.fax,f.catchment_population_head_count,f.catchment_population_cso,\n"
            + "f.number_of_paramedics,f.number_of_midwives,\n"
            + "f.longitude,f.latitude,ST_AsGeoJSON(f.geom) as geom,w.name as ward,c.name as constituency,\n"
            + "ft.name as facility_type,lt.name as location_type,ow.name as ownership,\n"
            + "os.name as operation_status,d.name as district,p.name as province \n"
            + "FROM \"MFL_facility\" f LEFT JOIN \"geography_ward\" w ON f.ward_id=w.id \n"
            + "LEFT JOIN \"geography_constituency\" c ON f.constituency_id=c.id \n"
            + "LEFT JOIN \"MFL_facilitytype\" ft ON f.facility_type_id=ft.id \n"
            + "LEFT JOIN \"geography_locationtype\" lt ON f.location_type_id=lt.id\n"
            + "LEFT JOIN \"MFL_ownership\" ow ON f.ownership_id=ow.id\n"
            + "LEFT JOIN \"MFL_operationstatus\" os ON f.operation_status_id=os.id\n"
            + "LEFT JOIN \"geography_district\" d ON f.district_id=d.id\n"
            + "LEFT JOIN \"geography_province\" p ON d.province_id=p.id\n"
            + "WHERE f.operation_status_id = :id ORDER BY f.id DESC", nativeQuery = true)
    List<Facilities> findByOperationStatusId(@Param("id") Long id);

    /**
     *
     * @param id
     * @return list
     */
    @Query(value = "SELECT f.id,f.name,f.\"DHIS2_UID\", f.\"HMIS_code\",f.\"smartcare_GUID\",f.\"eLMIS_ID\",\n"
            + "f.\"iHRIS_ID\",f.number_of_beds,f.number_of_cots,f.number_of_nurses,f.number_of_doctors,\n"
            + "f.address_line1,f.address_line2,f.postal_address,f.web_address,f.email,\n"
            + "f.phone,f.mobile,f.fax,f.catchment_population_head_count,f.catchment_population_cso,\n"
            + "f.number_of_paramedics,f.number_of_midwives,\n"
            + "f.longitude,f.latitude,ST_AsGeoJSON(f.geom) as geom,w.name as ward,c.name as constituency,\n"
            + "ft.name as facility_type,lt.name as location_type,ow.name as ownership,\n"
            + "os.name as operation_status,d.name as district,p.name as province \n"
            + "FROM \"MFL_facility\" f LEFT JOIN \"geography_ward\" w ON f.ward_id=w.id \n"
            + "LEFT JOIN \"geography_constituency\" c ON f.constituency_id=c.id \n"
            + "LEFT JOIN \"MFL_facilitytype\" ft ON f.facility_type_id=ft.id \n"
            + "LEFT JOIN \"geography_locationtype\" lt ON f.location_type_id=lt.id\n"
            + "LEFT JOIN \"MFL_ownership\" ow ON f.ownership_id=ow.id\n"
            + "LEFT JOIN \"MFL_operationstatus\" os ON f.operation_status_id=os.id\n"
            + "LEFT JOIN \"geography_district\" d ON f.district_id=d.id\n"
            + "LEFT JOIN \"geography_province\" p ON d.province_id=p.id\n"
            + "WHERE f.district_id IN (SELECT id from geography_district where province_id=:id) ORDER BY f.id DESC", nativeQuery = true)
    List<Facilities> findByProvinceId(@Param("id") Long id);

}
