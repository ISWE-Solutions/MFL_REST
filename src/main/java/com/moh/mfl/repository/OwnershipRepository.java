package com.moh.mfl.repository;

import com.moh.mfl.model.Ownership;
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
public interface OwnershipRepository extends JpaRepository<Ownership, Long> {

    /**
     *
     * @param name
     * @return list
     */
    @Query(value = "SELECT * FROM \"ownership\" WHERE name ILIKE %:name% ORDER BY id ASC", nativeQuery = true)
    List<Ownership> findByName(@Param("name") String name);
}
