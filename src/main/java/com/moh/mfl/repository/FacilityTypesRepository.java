package com.moh.mfl.repository;

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
public interface FacilityTypesRepository extends JpaRepository<FacilityTypes, Long> {

  
    /**
     *
     * @param name
     * @return list
     */
    @Query(value = "SELECT * FROM \"MFL_facilitytype\" WHERE name ILIKE %:name% ORDER BY id ASC", nativeQuery = true)
    List<FacilityTypes> findByName(@Param("name") String name);
}
