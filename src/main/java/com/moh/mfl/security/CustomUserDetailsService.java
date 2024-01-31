package com.moh.mfl.security;

import com.moh.mfl.model.ApiUsers;
import com.moh.mfl.repository.ApiUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    ApiUserRepo userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Check by username/clientcode
        ApiUsers user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Client with username" + username + " does not exist!"));

        return UserPrincipal.create(user);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id) {
        ApiUsers user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Client not found with id : " + id));

        return UserPrincipal.create(user);
    }
}
