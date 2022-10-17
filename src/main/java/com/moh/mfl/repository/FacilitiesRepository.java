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
    @Query(value = " SELECT f.id, f.name, f.\"disa_code\", f.\"hims_code\", f.\"smartcare_code\", f.\"elmis_code\",\n"
            + "f.\"hpcz_code\", f.\"phone\", f.\"mobile\", f.\"accesibility\",\n"
            + "CASE WHEN f.ownership_type = '1' THEN 'Public' ELSE 'Private' END\n"
            + "AS ownership_type,\n"
            + "f.physical_address, f.postal_address, f.town, f.email,\n"
            + "f.fax, f.catchment_population_head_count, f.catchment_population_cso,\n"
            + "f.street, f.plot_no, "
            + "CASE WHEN f.mobility_status ='1' THEN 'Fixed'\n"
            + "WHEN f.mobility_status ='2' THEN 'Mobile'\n"
            + "ELSE 'telemedicine' END\n"
            + "AS mobility_status, "
            + "f.number_of_households, w.name as ward,\n"
            + "c.name as constituency,\n"
            + "ft.name as facility_type, lt.name as location_type, ow.name as ownership,\n"
            + "os.name as operation_status, d.name as district,\n"
            + "f.longitude, f.latitude, ST_AsGeoJSON(f.geom) as geom, p.name as province\n"
            + "FROM \"facility\" f LEFT JOIN \"geography_ward\" w ON f.ward_id = w.id\n"
            + "LEFT JOIN \"geography_constituency\" c ON f.constituency_id = c.id\n"
            + "LEFT JOIN \"facility_types\" ft ON f.type = ft.id\n"
            + "LEFT JOIN \"geography_locationtype\" lt ON f.location = lt.id\n"
            + "LEFT JOIN \"ownership\" ow ON f.ownership = ow.id\n"
            + "LEFT JOIN \"operations_status\" os\n"
            + "ON f.operational_status = os.id LEFT JOIN \"geography_district\" d ON f.district_id = d.id\n"
            + "LEFT JOIN \"geography_province\" p ON d.province_id = p.id "
            + "WHERE f.longitude IS NOT NULL AND f.longitude !='' AND f.latitude IS NOT NULL AND  f.latitude !=''\n"
            + "ORDER BY f.geom <-> ST_GeomFromText ('POINT(' ||:lng || ' ' || :lat ||')', 4326)\n"
            //+ "WHERE ST_DWithin(f.geom, ST_GeogFromText('POINT(' ||:lng ||' ' || :lat ||')'), 10000, false)\n"
            + "LIMIT 10",
            nativeQuery = true)

    List<Facilities> findByLongitudeAndLatitude(@Param("lng") String longitude, @Param("lat") String latitude);

    /**
     *
     * @param name
     * @return list
     */
    @Query(value = " SELECT f.id, f.name, f.\"disa_code\", f.\"hims_code\", f.\"smartcare_code\", f.\"elmis_code\",\n"
            + "f.\"hpcz_code\", f.\"phone\", f.\"mobile\", f.\"accesibility\",\n"
            + "CASE WHEN f.ownership_type = '1' THEN 'Public' ELSE 'Private' END\n"
            + "AS ownership_type,\n"
            + "f.physical_address, f.postal_address, f.town, f.email,\n"
            + "f.fax, f.catchment_population_head_count, f.catchment_population_cso,\n"
            + "f.street, f.plot_no, "
            + "CASE WHEN f.mobility_status ='1' THEN 'Fixed' WHEN f.mobility_status ='2' THEN 'Mobile' ELSE 'telemedicine' END\n"
            + "AS mobility_status, "
            + "f.number_of_households, w.name as ward,\n"
            + "c.name as constituency,\n"
            + "ft.name as facility_type, lt.name as location_type, ow.name as ownership,\n"
            + "os.name as operation_status, d.name as district,\n"
            + "f.longitude, f.latitude, ST_AsGeoJSON(f.geom) as geom, p.name as province\n"
            + "FROM \"facility\" f LEFT JOIN \"geography_ward\" w ON f.ward_id = w.id\n"
            + "LEFT JOIN \"geography_constituency\" c ON f.constituency_id = c.id\n"
            + "LEFT JOIN \"facility_types\" ft ON f.type = ft.id\n"
            + "LEFT JOIN \"geography_locationtype\" lt ON f.location = lt.id\n"
            + "LEFT JOIN \"ownership\" ow ON f.ownership = ow.id\n"
            + "LEFT JOIN \"operations_status\" os\n"
            + "ON f.operational_status = os.id LEFT JOIN \"geography_district\" d ON f.district_id = d.id\n"
            + "LEFT JOIN \"geography_province\" p ON d.province_id = p.id\n"
            + "WHERE f.name ILIKE %:name% ORDER BY f.id DESC LIMIT 5", nativeQuery = true)
    List<Facilities> findByName(@Param("name") String name);

    /**
     *
     * @return list
     */
    @Query(value = " SELECT f.id, f.name, f.\"disa_code\", f.\"hims_code\", f.\"smartcare_code\", f.\"elmis_code\",\n"
            + "f.\"hpcz_code\", f.\"phone\", f.\"mobile\", f.\"accesibility\",\n"
            + "CASE WHEN f.ownership_type = '1' THEN 'Public' ELSE 'Private' END\n"
            + "AS ownership_type,\n"
            + "f.physical_address, f.postal_address, f.town, f.email,\n"
            + "f.fax, f.catchment_population_head_count, f.catchment_population_cso,\n"
            + "f.street, f.plot_no, "
            + "CASE WHEN f.mobility_status ='1' THEN 'Fixed' WHEN f.mobility_status ='2' THEN 'Mobile' ELSE 'telemedicine' END\n"
            + "AS mobility_status, "
            + "f.number_of_households, w.name as ward,\n"
            + "c.name as constituency,\n"
            + "ft.name as facility_type, lt.name as location_type, ow.name as ownership,\n"
            + "os.name as operation_status, d.name as district,\n"
            + "f.longitude, f.latitude, ST_AsGeoJSON(f.geom) as geom, p.name as province\n"
            + "FROM \"facility\" f LEFT JOIN \"geography_ward\" w ON f.ward_id = w.id\n"
            + "LEFT JOIN \"geography_constituency\" c ON f.constituency_id = c.id\n"
            + "LEFT JOIN \"facility_types\" ft ON f.type = ft.id\n"
            + "LEFT JOIN \"geography_locationtype\" lt ON f.location = lt.id\n"
            + "LEFT JOIN \"ownership\" ow ON f.ownership = ow.id\n"
            + "LEFT JOIN \"operations_status\" os\n"
            + "ON f.operational_status = os.id LEFT JOIN \"geography_district\" d ON f.district_id = d.id\n"
            + "LEFT JOIN \"geography_province\" p ON d.province_id = p.id\n"
            + " ORDER BY f.id DESC LIMIT 5", nativeQuery = true)
    List<Facilities> findAllFacilitieses();

    /**
     *
     * @param name
     * @param districtId
     * @return list
     */
    @Query(value = " SELECT f.id, f.name, f.\"disa_code\", f.\"hims_code\", f.\"smartcare_code\", f.\"elmis_code\",\n"
            + "f.\"hpcz_code\", f.\"phone\", f.\"mobile\", f.\"accesibility\",\n"
            + "CASE WHEN f.ownership_type = '1' THEN 'Public' ELSE 'Private' END\n"
            + "AS ownership_type,\n"
            + "f.physical_address, f.postal_address, f.town, f.email,\n"
            + "f.fax, f.catchment_population_head_count, f.catchment_population_cso,\n"
            + "f.street, f.plot_no, "
            + "CASE WHEN f.mobility_status ='1' THEN 'Fixed'\n"
            + "WHEN f.mobility_status ='2' THEN 'Mobile'\n"
            + "ELSE 'telemedicine' END\n"
            + "AS mobility_status, "
            + "f.number_of_households, w.name as ward,\n"
            + "c.name as constituency,\n"
            + "ft.name as facility_type, lt.name as location_type, ow.name as ownership,\n"
            + "os.name as operation_status, d.name as district,\n"
            + "f.longitude, f.latitude, ST_AsGeoJSON(f.geom) as geom, p.name as province\n"
            + "FROM \"facility\" f LEFT JOIN \"geography_ward\" w ON f.ward_id = w.id\n"
            + "LEFT JOIN \"geography_constituency\" c ON f.constituency_id = c.id\n"
            + "LEFT JOIN \"facility_types\" ft ON f.type = ft.id\n"
            + "LEFT JOIN \"geography_locationtype\" lt ON f.location = lt.id\n"
            + "LEFT JOIN \"ownership\" ow ON f.ownership = ow.id\n"
            + "LEFT JOIN \"operations_status\" os\n"
            + "ON f.operational_status = os.id LEFT JOIN \"geography_district\" d ON f.district_id = d.id\n"
            + "LEFT JOIN \"geography_province\" p ON d.province_id = p.id\n"
            + "WHERE f.name ILIKE %:name% AND f.district_id=:districtId ORDER BY f.id DESC LIMIT 5", nativeQuery = true)
    List<Facilities> findByNameAndDistrictId(@Param("name") String name, @Param("districtId") Long districtId);

    /**
     *
     * @param id
     * @return list
     */
    @Query(value = " SELECT f.id, f.name, f.\"disa_code\", f.\"hims_code\", f.\"smartcare_code\", f.\"elmis_code\",\n"
            + "f.\"hpcz_code\", f.\"phone\", f.\"mobile\", f.\"accesibility\",\n"
            + "CASE WHEN f.ownership_type = '1' THEN 'Public' ELSE 'Private' END\n"
            + "AS ownership_type,\n"
            + "f.physical_address, f.postal_address, f.town, f.email,\n"
            + "f.fax, f.catchment_population_head_count, f.catchment_population_cso,\n"
            + "f.street, f.plot_no, "
            + "CASE WHEN f.mobility_status ='1' THEN 'Fixed'\n"
            + "WHEN f.mobility_status ='2' THEN 'Mobile'\n"
            + "ELSE 'telemedicine' END\n"
            + "AS mobility_status, "
            + "f.number_of_households, w.name as ward,\n"
            + "c.name as constituency,\n"
            + "ft.name as facility_type, lt.name as location_type, ow.name as ownership,\n"
            + "os.name as operation_status, d.name as district,\n"
            + "f.longitude, f.latitude, ST_AsGeoJSON(f.geom) as geom, p.name as province\n"
            + "FROM \"facility\" f LEFT JOIN \"geography_ward\" w ON f.ward_id = w.id\n"
            + "LEFT JOIN \"geography_constituency\" c ON f.constituency_id = c.id\n"
            + "LEFT JOIN \"facility_types\" ft ON f.type = ft.id\n"
            + "LEFT JOIN \"geography_locationtype\" lt ON f.location = lt.id\n"
            + "LEFT JOIN \"ownership\" ow ON f.ownership = ow.id\n"
            + "LEFT JOIN \"operations_status\" os\n"
            + "ON f.operational_status = os.id LEFT JOIN \"geography_district\" d ON f.district_id = d.id\n"
            + "LEFT JOIN \"geography_province\" p ON d.province_id = p.id\n"
            + "WHERE f.ward_id = :id ORDER BY f.id DESC", nativeQuery = true)
    List<Facilities> findByWardId(@Param("id") Long id);

    /**
     *
     * @param id
     * @return list
     */
    @Query(value = " SELECT f.id, f.name, f.\"disa_code\", f.\"hims_code\", f.\"smartcare_code\", f.\"elmis_code\",\n"
            + "f.\"hpcz_code\", f.\"phone\", f.\"mobile\", f.\"accesibility\",\n"
            + "CASE WHEN f.ownership_type = '1' THEN 'Public' ELSE 'Private' END\n"
            + "AS ownership_type,\n"
            + "f.physical_address, f.postal_address, f.town, f.email,\n"
            + "f.fax, f.catchment_population_head_count, f.catchment_population_cso,\n"
            + "f.street, f.plot_no, "
            + "CASE WHEN f.mobility_status ='1' THEN 'Fixed'\n"
            + "WHEN f.mobility_status ='2' THEN 'Mobile'\n"
            + "ELSE 'telemedicine' END\n"
            + "AS mobility_status, "
            + "f.number_of_households, w.name as ward,\n"
            + "c.name as constituency,\n"
            + "ft.name as facility_type, lt.name as location_type, ow.name as ownership,\n"
            + "os.name as operation_status, d.name as district,\n"
            + "f.longitude, f.latitude, ST_AsGeoJSON(f.geom) as geom, p.name as province\n"
            + "FROM \"facility\" f LEFT JOIN \"geography_ward\" w ON f.ward_id = w.id\n"
            + "LEFT JOIN \"geography_constituency\" c ON f.constituency_id = c.id\n"
            + "LEFT JOIN \"facility_types\" ft ON f.type = ft.id\n"
            + "LEFT JOIN \"geography_locationtype\" lt ON f.location = lt.id\n"
            + "LEFT JOIN \"ownership\" ow ON f.ownership = ow.id\n"
            + "LEFT JOIN \"operations_status\" os\n"
            + "ON f.operational_status = os.id LEFT JOIN \"geography_district\" d ON f.district_id = d.id\n"
            + "LEFT JOIN \"geography_province\" p ON d.province_id = p.id\n"
            + "WHERE f.constituency_id = :id ORDER BY f.id DESC", nativeQuery = true)
    List<Facilities> findByConstituencyId(@Param("id") Long id);

    /**
     *
     * @param id
     * @return list
     */
    @Query(value = " SELECT f.id, f.name, f.\"disa_code\", f.\"hims_code\", f.\"smartcare_code\", f.\"elmis_code\",\n"
            + "f.\"hpcz_code\", f.\"phone\", f.\"mobile\", f.\"accesibility\",\n"
            + "CASE WHEN f.ownership_type = '1' THEN 'Public' ELSE 'Private' END\n"
            + "AS ownership_type,\n"
            + "f.physical_address, f.postal_address, f.town, f.email,\n"
            + "f.fax, f.catchment_population_head_count, f.catchment_population_cso,\n"
            + "f.street, f.plot_no, "
            + "CASE WHEN f.mobility_status ='1' THEN 'Fixed'\n"
            + "WHEN f.mobility_status ='2' THEN 'Mobile'\n"
            + "ELSE 'telemedicine' END\n"
            + "AS mobility_status, "
            + "f.number_of_households, w.name as ward,\n"
            + "c.name as constituency,\n"
            + "ft.name as facility_type, lt.name as location_type, ow.name as ownership,\n"
            + "os.name as operation_status, d.name as district,\n"
            + "f.longitude, f.latitude, ST_AsGeoJSON(f.geom) as geom, p.name as province\n"
            + "FROM \"facility\" f LEFT JOIN \"geography_ward\" w ON f.ward_id = w.id\n"
            + "LEFT JOIN \"geography_constituency\" c ON f.constituency_id = c.id\n"
            + "LEFT JOIN \"facility_types\" ft ON f.type = ft.id\n"
            + "LEFT JOIN \"geography_locationtype\" lt ON f.location = lt.id\n"
            + "LEFT JOIN \"ownership\" ow ON f.ownership = ow.id\n"
            + "LEFT JOIN \"operations_status\" os\n"
            + "ON f.operational_status = os.id LEFT JOIN \"geography_district\" d ON f.district_id = d.id\n"
            + "LEFT JOIN \"geography_province\" p ON d.province_id = p.id\n"
            + "WHERE f.district_id = :id ORDER BY f.id DESC", nativeQuery = true)
    List<Facilities> findByDistrictId(@Param("id") Long id);

    /**
     *
     * @param id
     * @return list
     */
    @Query(value = " SELECT f.id, f.name, f.\"disa_code\", f.\"hims_code\", f.\"smartcare_code\", f.\"elmis_code\",\n"
            + "f.\"hpcz_code\", f.\"phone\", f.\"mobile\", f.\"accesibility\",\n"
            + "CASE WHEN f.ownership_type = '1' THEN 'Public' ELSE 'Private' END\n"
            + "AS ownership_type,\n"
            + "f.physical_address, f.postal_address, f.town, f.email,\n"
            + "f.fax, f.catchment_population_head_count, f.catchment_population_cso,\n"
            + "f.street, f.plot_no, "
            + "CASE WHEN f.mobility_status ='1' THEN 'Fixed'\n"
            + "WHEN f.mobility_status ='2' THEN 'Mobile'\n"
            + "ELSE 'telemedicine' END\n"
            + "AS mobility_status, "
            + "f.number_of_households, w.name as ward,\n"
            + "c.name as constituency,\n"
            + "ft.name as facility_type, lt.name as location_type, ow.name as ownership,\n"
            + "os.name as operation_status, d.name as district,\n"
            + "f.longitude, f.latitude, ST_AsGeoJSON(f.geom) as geom, p.name as province\n"
            + "FROM \"facility\" f LEFT JOIN \"geography_ward\" w ON f.ward_id = w.id\n"
            + "LEFT JOIN \"geography_constituency\" c ON f.constituency_id = c.id\n"
            + "LEFT JOIN \"facility_types\" ft ON f.type = ft.id\n"
            + "LEFT JOIN \"geography_locationtype\" lt ON f.location = lt.id\n"
            + "LEFT JOIN \"ownership\" ow ON f.ownership = ow.id\n"
            + "LEFT JOIN \"operations_status\" os\n"
            + "ON f.operational_status = os.id LEFT JOIN \"geography_district\" d ON f.district_id = d.id\n"
            + "LEFT JOIN \"geography_province\" p ON d.province_id = p.id\n"
            + "WHERE f.type = :id ORDER BY f.id DESC", nativeQuery = true)
    List<Facilities> findByFacilityTypeId(@Param("id") Long id);

    /**
     *
     * @param id
     * @return list
     */
    @Query(value = " SELECT f.id, f.name, f.\"disa_code\", f.\"hims_code\", f.\"smartcare_code\", f.\"elmis_code\",\n"
            + "f.\"hpcz_code\", f.\"phone\", f.\"mobile\", f.\"accesibility\",\n"
            + "CASE WHEN f.ownership_type = '1' THEN 'Public' ELSE 'Private' END\n"
            + "AS ownership_type,\n"
            + "f.physical_address, f.postal_address, f.town, f.email,\n"
            + "f.fax, f.catchment_population_head_count, f.catchment_population_cso,\n"
            + "f.street, f.plot_no, "
            + "CASE WHEN f.mobility_status ='1' THEN 'Fixed'\n"
            + "WHEN f.mobility_status ='2' THEN 'Mobile'\n"
            + "ELSE 'telemedicine' END\n"
            + "AS mobility_status, "
            + "f.number_of_households, w.name as ward,\n"
            + "c.name as constituency,\n"
            + "ft.name as facility_type, lt.name as location_type, ow.name as ownership,\n"
            + "os.name as operation_status, d.name as district,\n"
            + "f.longitude, f.latitude, ST_AsGeoJSON(f.geom) as geom, p.name as province\n"
            + "FROM \"facility\" f LEFT JOIN \"geography_ward\" w ON f.ward_id = w.id\n"
            + "LEFT JOIN \"geography_constituency\" c ON f.constituency_id = c.id\n"
            + "LEFT JOIN \"facility_types\" ft ON f.type = ft.id\n"
            + "LEFT JOIN \"geography_locationtype\" lt ON f.location = lt.id\n"
            + "LEFT JOIN \"ownership\" ow ON f.ownership = ow.id\n"
            + "LEFT JOIN \"operations_status\" os\n"
            + "ON f.operational_status = os.id LEFT JOIN \"geography_district\" d ON f.district_id = d.id\n"
            + "LEFT JOIN \"geography_province\" p ON d.province_id = p.id\n"
            + "WHERE f.ownership = :id ORDER BY f.id DESC", nativeQuery = true)
    List<Facilities> findByOwnershipId(@Param("id") Long id);

    /**
     *
     * @param id
     * @return list
     */
    @Query(value = " SELECT f.id, f.name, f.\"disa_code\", f.\"hims_code\", f.\"smartcare_code\", f.\"elmis_code\",\n"
            + "f.\"hpcz_code\", f.\"phone\", f.\"mobile\", f.\"accesibility\",\n"
            + "CASE WHEN f.ownership_type = '1' THEN 'Public' ELSE 'Private' END\n"
            + "AS ownership_type,\n"
            + "f.physical_address, f.postal_address, f.town, f.email,\n"
            + "f.fax, f.catchment_population_head_count, f.catchment_population_cso,\n"
            + "f.street, f.plot_no, "
            + "CASE WHEN f.mobility_status ='1' THEN 'Fixed'\n"
            + "WHEN f.mobility_status ='2' THEN 'Mobile'\n"
            + "ELSE 'telemedicine' END\n"
            + "AS mobility_status, "
            + "f.number_of_households, w.name as ward,\n"
            + "c.name as constituency,\n"
            + "ft.name as facility_type, lt.name as location_type, ow.name as ownership,\n"
            + "os.name as operation_status, d.name as district,\n"
            + "f.longitude, f.latitude, ST_AsGeoJSON(f.geom) as geom, p.name as province\n"
            + "FROM \"facility\" f LEFT JOIN \"geography_ward\" w ON f.ward_id = w.id\n"
            + "LEFT JOIN \"geography_constituency\" c ON f.constituency_id = c.id\n"
            + "LEFT JOIN \"facility_types\" ft ON f.type = ft.id\n"
            + "LEFT JOIN \"geography_locationtype\" lt ON f.location = lt.id\n"
            + "LEFT JOIN \"ownership\" ow ON f.ownership = ow.id\n"
            + "LEFT JOIN \"operations_status\" os\n"
            + "ON f.operational_status = os.id LEFT JOIN \"geography_district\" d ON f.district_id = d.id\n"
            + "LEFT JOIN \"geography_province\" p ON d.province_id = p.id\n"
            + "WHERE f.operational_status = :id ORDER BY f.id DESC", nativeQuery = true)
    List<Facilities> findByOperationStatusId(@Param("id") Long id);

    /**
     *
     * @param id
     * @return list
     */
    @Query(value = " SELECT f.id, f.name, f.\"disa_code\", f.\"hims_code\", f.\"smartcare_code\", f.\"elmis_code\",\n"
            + "f.\"hpcz_code\", f.\"phone\", f.\"mobile\", f.\"accesibility\",\n"
            + "CASE WHEN f.ownership_type = '1' THEN 'Public' ELSE 'Private' END\n"
            + "AS ownership_type,\n"
            + "f.physical_address, f.postal_address, f.town, f.email,\n"
            + "f.fax, f.catchment_population_head_count, f.catchment_population_cso,\n"
            + "f.street, f.plot_no, "
            + "CASE WHEN f.mobility_status ='1' THEN 'Fixed'\n"
            + "WHEN f.mobility_status ='2' THEN 'Mobile'\n"
            + "ELSE 'telemedicine' END\n"
            + "AS mobility_status, "
            + "f.number_of_households, w.name as ward,\n"
            + "c.name as constituency,\n"
            + "ft.name as facility_type, lt.name as location_type, ow.name as ownership,\n"
            + "os.name as operation_status, d.name as district,\n"
            + "f.longitude, f.latitude, ST_AsGeoJSON(f.geom) as geom, p.name as province\n"
            + "FROM \"facility\" f LEFT JOIN \"geography_ward\" w ON f.ward_id = w.id\n"
            + "LEFT JOIN \"geography_constituency\" c ON f.constituency_id = c.id\n"
            + "LEFT JOIN \"facility_types\" ft ON f.type = ft.id\n"
            + "LEFT JOIN \"geography_locationtype\" lt ON f.location = lt.id\n"
            + "LEFT JOIN \"ownership\" ow ON f.ownership = ow.id\n"
            + "LEFT JOIN \"operations_status\" os\n"
            + "ON f.operational_status = os.id LEFT JOIN \"geography_district\" d ON f.district_id = d.id\n"
            + "LEFT JOIN \"geography_province\" p ON d.province_id = p.id\n"
            + "WHERE f.district_id IN (SELECT id from geography_district where province_id=:id) ORDER BY f.id DESC", nativeQuery = true)
    List<Facilities> findByProvinceId(@Param("id") Long id);

}
