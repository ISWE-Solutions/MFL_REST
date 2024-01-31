package com.moh.mfl.repository;

import com.moh.mfl.model.ApiUsers;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiUserRepo extends JpaRepository<ApiUsers, Long> {
    ApiUsers findByUsernameAndStatus(String username, int status);
    List<ApiUsers> findByIdIn(List<Long> userIds);
    Optional<ApiUsers> findByUsername(String username);
    //User findByUsername(String username);
    Boolean existsByUsername(String username);
    Optional<ApiUsers> findByUsernameOrEmail(String username, String email);
    Boolean existsByEmail(String email);
    Optional<ApiUsers> findByEmail(String email);
}
