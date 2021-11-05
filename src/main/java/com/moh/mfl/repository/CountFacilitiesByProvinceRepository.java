package com.moh.mfl.repository;

import com.moh.mfl.model.FacilityByProvince;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Francis Chulu
 */
@Repository
public interface CountFacilitiesByProvinceRepository extends JpaRepository<FacilityByProvince, Long> {

    @Query(value = "select count(f.id) as count,p.name as province from \"facility\" f\n"
            + "LEFT JOIN \"operations_status\" os ON f.operational_status=os.id\n"
            + "LEFT JOIN \"geography_district\" d ON f.district_id=d.id\n"
            + "LEFT JOIN \"geography_province\" p ON d.province_id=p.id\n"
            + "WHERE os.name='Functional'\n"
            + "group by province order by count DESC", nativeQuery = true)
    List<FacilityByProvince> findByDistrictId();


}
