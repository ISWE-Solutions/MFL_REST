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

    @Query(value = "select count(f.id) as count,ft.name as type FROM \"facility\" f\n"
            + "LEFT JOIN \"facility_types\" ft ON f.type=ft.id\n"
            + "LEFT JOIN \"operations_status\" os ON f.f.operational_status=os.id\n"
            + "WHERE os.name='Functional'\n"
            + "group by type order by count DESC ", nativeQuery = true)
    List<FacilityTypeCounts> findByFacilityTypeId();


}
