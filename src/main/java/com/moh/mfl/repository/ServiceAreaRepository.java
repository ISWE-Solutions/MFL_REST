/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moh.mfl.repository;

import com.moh.mfl.model.ServiceArea;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author chulu
 */
public interface ServiceAreaRepository extends JpaRepository<ServiceArea, Long> {

    Optional<ServiceArea> findBySharedId(Long sharedId);
}
