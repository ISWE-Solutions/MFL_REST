package com.moh.mfl.repository;

import com.moh.mfl.model.Constituencies;
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
public interface ConstituenciesRepository extends JpaRepository<Constituencies, Long> {

    /**
     *
     * @param id
     * @return Optional
     */
    @Query(value = "SELECT c.id,c.name,c.population,c.pop_density,c.area_sq_km,\n"
            + "ST_AsGeoJSON(c.geom) as geom,d.name as district,p.name as province\n"
            + "from geography_constituency c LEFT JOIN geography_district d \n"
            + "ON c.district_id=d.id LEFT JOIN geography_province p \n"
            + "ON d.province_id=p.id WHERE c.id=:id", nativeQuery = true)
    @Override
    Optional<Constituencies> findById(@Param("id") Long id);

    /**
     *
     * @param name
     * @return list
     */
    @Query(value = "SELECT c.id,c.name,c.population,c.pop_density,c.area_sq_km,\n"
            + "ST_AsGeoJSON(c.geom) as geom,d.name as district,p.name as province\n"
            + "from geography_constituency c LEFT JOIN geography_district d \n"
            + "ON c.district_id=d.id LEFT JOIN geography_province p \n"
            + "ON d.province_id=p.id WHERE c.name ILIKE %:name%", nativeQuery = true)
    List<Constituencies> findByName(@Param("name") String name);

    /**
     *
     * @param district_id
     * @return list
     */
    @Query(value = "SELECT c.id,c.name,c.population,c.pop_density,c.area_sq_km,\n"
            + "ST_AsGeoJSON(c.geom) as geom,d.name as district,p.name as province\n"
            + "from geography_constituency c LEFT JOIN geography_district d \n"
            + "ON c.district_id=d.id LEFT JOIN geography_province p \n"
            + "ON d.province_id=p.id WHERE c.district_id=:district_id", nativeQuery = true)
    List<Constituencies> findByDistrictId(@Param("district_id") Long district_id);

}
