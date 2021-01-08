package com.moh.mfl.repository;

import com.moh.mfl.model.Districts;
import java.util.List;
import java.util.Map;
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
public interface DistrictsRepository extends JpaRepository<Districts, Long> {

    /**
     *
     * @return list
     */
    @Query(value = "SELECT id,name,population,pop_density,area_sq_km,ST_AsGeoJSON(geom) as geom,district_type_id,province_id FROM geography_district", nativeQuery = true)
    @Override
    List<Districts> findAll();

    /**
     *
     * @param id
     * @return optional
     */
    @Query(value = "SELECT gd.id,gd.name,gd.population,gd.pop_density,gd.area_sq_km,\n"
            + "ST_AsGeoJSON(gd.geom) as geom,gdt.name as districtType,gp.name as province\n"
            + "from geography_district gd \n"
            + "LEFT JOIN geography_districttype gdt ON \n"
            + "gd.district_type_id=gdt.id\n"
            + "LEFT JOIN geography_province gp ON\n"
            + "gd.province_id=gp.id WHERE gd.id=:id", nativeQuery = true)
    @Override
    Optional<Districts> findById(@Param("id") Long id);

    /**
     *
     * @param name
     * @return list
     */
    @Query(value = "SELECT gd.id,gd.name,gd.population,gd.pop_density,gd.area_sq_km,\n"
            + "ST_AsGeoJSON(gd.geom) as geom,gdt.name as districtType,gp.name as province\n"
            + "from geography_district gd \n"
            + "LEFT JOIN geography_districttype gdt ON \n"
            + "gd.district_type_id=gdt.id\n"
            + "LEFT JOIN geography_province gp ON\n"
            + "gd.province_id=gp.id WHERE gd.name ILIKE %:name%", nativeQuery = true)
    List<Districts> findByName(@Param("name") String name);

    /**
     *
     * @param province_id
     * @return list
     */
    @Query(value = "SELECT gd.id,gd.name,gd.population,gd.pop_density,gd.area_sq_km,\n"
            + "ST_AsGeoJSON(gd.geom) as geom,gdt.name as districtType,gp.name as province\n"
            + "from geography_district gd \n"
            + "LEFT JOIN geography_districttype gdt ON \n"
            + "gd.district_type_id=gdt.id\n"
            + "LEFT JOIN geography_province gp ON\n"
            + "gd.province_id=gp.id WHERE province_id=:province_id", nativeQuery = true)
    List<Districts> findByProvinceId(@Param("province_id") Long province_id);

    /**
     *
     * @param district_type_id
     * @return list
     */
    @Query(value = "SELECT gd.id,gd.name,gd.population,gd.pop_density,gd.area_sq_km,\n"
            + "ST_AsGeoJSON(gd.geom) as geom,gdt.name as districtType,gp.name as province\n"
            + "from geography_district gd \n"
            + "LEFT JOIN geography_districttype gdt ON \n"
            + "gd.district_type_id=gdt.id\n"
            + "LEFT JOIN geography_province gp ON\n"
            + "gd.province_id=gp.id WHERE district_type_id=:district_type_id", nativeQuery = true)
    List<Districts> findByDistrictTypeId(@Param("district_type_id") Long district_type_id);

    /**
     *
     * @param id
     * @return list
     */
    @Query(value = "SELECT id FROM \"geography_district\" where province_id=:id", nativeQuery = true)
    List<Map> getIdByProvinceId(@Param("id") Long id);
}
