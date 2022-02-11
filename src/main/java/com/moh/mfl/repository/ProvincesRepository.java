package com.moh.mfl.repository;

import com.moh.mfl.model.ProvinceList;
import com.moh.mfl.model.Provinces;
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
public interface ProvincesRepository extends JpaRepository<Provinces, Long> {

    // Provinces findByName(String name);

    /**
     *
     * @return list
     */
    @Query(value = "SELECT id,name,population,pop_density,area_sq_km,ST_AsGeoJSON(geom) as geom FROM geography_province ORDER BY id ASC", nativeQuery = true)
    @Override
    List<Provinces> findAll();
    
    @Query(value = "SELECT id,name FROM geography_province ORDER BY id ASC", nativeQuery = true)
    List<ProvinceList> getIdAndNameOnly();

    /**
     *
     * @param name
     * @return list
     */
    @Query(value = "SELECT id,name,population,pop_density,area_sq_km,ST_AsGeoJSON(geom) as geom FROM geography_province WHERE name ILIKE %:name% ORDER BY id ASC", nativeQuery = true)
    List<Provinces> findByName(@Param("name") String name);
}
