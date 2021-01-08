package com.moh.mfl.repository;

import com.moh.mfl.model.Wards;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Francis Chulu
 */
@Repository
public interface WardsRepository extends JpaRepository<Wards, Long> {

    /**
     *
     * @param id
     * @return Optional
     */
    @Query(value = "SELECT w.id,w.name,w.population,w.pop_density,w.area_sq_km,\n"
            + "ST_AsGeoJSON(w.geom) as geom,c.name as constituency ,d.name as district,p.name as province \n"
            + "from geography_ward w LEFT JOIN geography_district d \n"
            + "ON w.district_id=d.id LEFT JOIN geography_constituency c ON w.constituency_id=c.id\n"
            + "LEFT JOIN geography_province p ON d.province_id=p.id WHERE w.id=:id", nativeQuery = true)
    @Override
    Optional<Wards> findById(@Param("id") Long id);

    /**
     * 
     * @param name
     * @return list
     */
    @Query(value = "SELECT w.id,w.name,w.population,w.pop_density,w.area_sq_km,\n"
            + "ST_AsGeoJSON(w.geom) as geom,c.name as constituency ,d.name as district,p.name as province \n"
            + "from geography_ward w LEFT JOIN geography_district d \n"
            + "ON w.district_id=d.id LEFT JOIN geography_constituency c ON w.constituency_id=c.id\n"
            + "LEFT JOIN geography_province p ON d.province_id=p.id WHERE w.name ILIKE %:name%", nativeQuery = true)
    List<Wards> findByName(@Param("name") String name);

    /**
     *
     * @param district_id
     * @return list
     */
    @Query(value = "SELECT w.id,w.name,w.population,w.pop_density,w.area_sq_km,\n"
            + "ST_AsGeoJSON(w.geom) as geom,c.name as constituency ,d.name as district,p.name as province \n"
            + "from geography_ward w LEFT JOIN geography_district d \n"
            + "ON w.district_id=d.id LEFT JOIN geography_constituency c ON w.constituency_id=c.id\n"
            + "LEFT JOIN geography_province p ON d.province_id=p.id "
            + "WHERE w.district_id=:district_id", nativeQuery = true)
    List<Wards> findByDistrictId(@Param("district_id") Long district_id);

    /**
     *
     * @param constituency_id
     * @return list
     */
    @Query(value = "SELECT w.id,w.name,w.population,w.pop_density,w.area_sq_km,\n"
            + "ST_AsGeoJSON(w.geom) as geom,c.name as constituency ,d.name as district,p.name as province \n"
            + "from geography_ward w LEFT JOIN geography_district d \n"
            + "ON w.district_id=d.id LEFT JOIN geography_constituency c ON w.constituency_id=c.id\n"
            + "LEFT JOIN geography_province p ON d.province_id=p.id "
            + "WHERE w.constituency_id=:constituency_id", nativeQuery = true)
    List<Wards> findByConstituencyId(@Param("constituency_id") Long constituency_id);
}
