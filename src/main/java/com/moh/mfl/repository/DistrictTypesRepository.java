package com.moh.mfl.repository;

import com.moh.mfl.model.Districttypes;
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
public interface DistrictTypesRepository extends JpaRepository<Districttypes, Long> {

    /**
     *
     * @param name
     * @return list
     */
    @Query(value = "SELECT * FROM geography_districttype WHERE name ILIKE %:name%", nativeQuery = true)
    List<Districttypes> findByName(@Param("name") String name);
}
