package com.moh.mfl.repository;

import com.moh.mfl.model.FacilityIdAndName;
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
public interface FacilitiesIdAndNameOnlyRepository extends JpaRepository<FacilityIdAndName, Long> {

    /**
     *
     * @param id
     * @return list
     */
    @Query(value = "SELECT f.id, f.name,d.name as district FROM \"facility\" f "
            + "LEFT JOIN \"geography_district\" d ON f.district_id = d.id "
            + "WHERE f.district_id=:id ORDER BY f.id DESC", nativeQuery = true)
    List<FacilityIdAndName> findByDistrictId(@Param("id") Long id);
//    @Query(value = " SELECT f.id, f.name,p.name as province FROM \"facility\" f "
//            + "LEFT JOIN \"geography_district\" d ON f.district_id = d.id\n"
//            + "LEFT JOIN \"geography_province\" p ON d.province_id = p.id\n"
//            + "WHERE f.district_id IN (SELECT id from geography_district where province_id=:id) ORDER BY f.id DESC", nativeQuery = true)
//    List<FacilityIdAndName> findByProvinceId(@Param("id") Long id);

}
