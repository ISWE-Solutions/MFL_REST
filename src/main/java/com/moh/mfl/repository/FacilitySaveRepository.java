package com.moh.mfl.repository;

import com.moh.mfl.model.Facility;
import com.moh.mfl.model.FacilitySave;
import javax.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chulu
 */
@Repository
public interface FacilitySaveRepository extends JpaRepository<FacilitySave, Id> {
    
}
