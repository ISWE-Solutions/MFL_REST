package com.moh.mfl.repository;

import com.moh.mfl.model.FacilityTypeCounts;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Francis Chulu
 */
@Repository
public interface CountFacilitiesByTypeRepository extends JpaRepository<FacilityTypeCounts, Long> {

    @Query(value = "select count(f.id) AS count,ft.name AS type FROM \"facility\" f "
            + "LEFT JOIN \"facility_types\" ft ON f.type=ft.id\n"
            + "LEFT JOIN \"operations_status\" os ON f.operational_status=os.id WHERE os.name='Functional'\n"
            + "group by ft.name order by count DESC", nativeQuery = true)
    List<FacilityTypeCounts> findByFacilityTypeId();

}
