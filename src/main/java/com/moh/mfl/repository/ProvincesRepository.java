package com.moh.mfl.repository;

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
    @Query(value = "SELECT id,name,population,pop_density,area_sq_km,ST_AsGeoJSON(geom) as geom FROM geography_province", nativeQuery = true)
    @Override
    List<Provinces> findAll();

    /**
     *
     * @param name
     * @return list
     */
    @Query(value = "SELECT id,name,population,pop_density,area_sq_km,ST_AsGeoJSON(geom) as geom FROM geography_province WHERE name ILIKE %:name%", nativeQuery = true)
    List<Provinces> findByName(@Param("name") String name);
}
