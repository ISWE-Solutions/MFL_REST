/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moh.mfl.repository;

import com.moh.mfl.model.ServiceScope;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author chulu
 */
public interface ServiceScopeRepository extends JpaRepository<ServiceScope, Long> {
     Optional<ServiceScope> findBySharedId(Integer sharedId);
}
