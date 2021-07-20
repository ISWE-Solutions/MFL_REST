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

    @Query(value = "select count(f.id) as count,ft.name as type from \"MFL_facility\" f\n"
            + "LEFT JOIN \"MFL_facilitytype\" ft ON f.facility_type_id=ft.id\n"
            + "LEFT JOIN \"MFL_operationstatus\" os ON f.operation_status_id=os.id\n"
            + "WHERE os.name='Operational'\n"
            + "group by type order by count DESC ", nativeQuery = true)
    List<FacilityTypeCounts> findByFacilityTypeId();


}
