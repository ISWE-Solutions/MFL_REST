package com.moh.mfl.repository;

import com.moh.mfl.model.FacilitySave;
import java.util.Optional;
import javax.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chulu
 */
@Repository
public interface FacilitySaveRepository extends JpaRepository<FacilitySave, Id> {

    Optional<FacilitySave> findByFacilityName(String name);

}
