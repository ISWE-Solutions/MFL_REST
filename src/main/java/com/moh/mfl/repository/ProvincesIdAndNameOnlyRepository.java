package com.moh.mfl.repository;

import com.moh.mfl.model.ProvinceList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Francis Chulu
 */
@Repository
public interface ProvincesIdAndNameOnlyRepository extends JpaRepository<ProvinceList, Long> {

    
    @Query(value = "SELECT id,name FROM geography_province ORDER BY id ASC", nativeQuery = true)
    List<ProvinceList> getIdAndNameOnly();

}
