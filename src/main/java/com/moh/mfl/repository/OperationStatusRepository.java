package com.moh.mfl.repository;

import com.moh.mfl.model.DistrictTypes;
import com.moh.mfl.model.OperationStatus;
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
public interface OperationStatusRepository extends JpaRepository<OperationStatus, Long> {

    /**
     *
     * @param name
     * @return list
     */
    @Query(value = "SELECT * FROM \"MFL_operationstatus\" WHERE name ILIKE %:name%", nativeQuery = true)
    List<OperationStatus> findByName(@Param("name") String name);
}
