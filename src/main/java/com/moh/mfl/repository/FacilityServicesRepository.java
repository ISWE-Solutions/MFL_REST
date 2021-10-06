/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moh.mfl.repository;

import com.moh.mfl.model.FacilityServices;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author chulu
 */
public interface FacilityServicesRepository extends CrudRepository<FacilityServices, Long> {

    Optional<FacilityServices> findByFacilityIdAndServiceId(Integer facilityId, Integer serviceId);
}
