package com.moh.mfl.repository;

import com.moh.mfl.model.Facilities;
import com.moh.mfl.model.FacilityTypes;
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
     * @param name
     * @return list
     */
    @Query(value = "SELECT f.id,f.name,f.\"DHIS2_UID\", f.\"HMIS_code\",f.\"smartcare_GUID\",f.\"eLMIS_ID\",\n"
            + "f.\"iHRIS_ID\",f.number_of_beds,f.number_of_cots,f.number_of_nurses,f.number_of_doctors,\n"
            + "f.address_line1,f.address_line2,f.postal_address,f.web_address,f.email,\n"
            + "f.phone,f.mobile,f.fax,f.catchment_population_head_count,f.catchment_population_cso,\n"
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
            + "WHERE f.name ILIKE %:name%", nativeQuery = true)
    List<Facilities> findByName(@Param("name") String name);

    /**
     *
     * @param id
     * @return list
     */
    @Query(value = "SELECT f.id,f.name,f.\"DHIS2_UID\", f.\"HMIS_code\",f.\"smartcare_GUID\",f.\"eLMIS_ID\",\n"
            + "f.\"iHRIS_ID\",f.number_of_beds,f.number_of_cots,f.number_of_nurses,f.number_of_doctors,\n"
            + "f.address_line1,f.address_line2,f.postal_address,f.web_address,f.email,\n"
            + "f.phone,f.mobile,f.fax,f.catchment_population_head_count,f.catchment_population_cso,\n"
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
            + "WHERE f.ward_id = :id", nativeQuery = true)
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
            + "WHERE f.constituency_id = :id", nativeQuery = true)
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
            + "WHERE f.district_id = :id", nativeQuery = true)
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
            + "WHERE f.district_id IN (SELECT id from geography_district where province_id=:id)", nativeQuery = true)
    List<Facilities> findByProvinceId(@Param("id") Long id);
}
