package com.moh.mfl.controller;

import com.moh.mfl.config.SecurityConfig;
import com.moh.mfl.model.ApiUsers;
import com.moh.mfl.repository.ApiUserRepo;
import com.moh.mfl.response.AuthResponse;
import com.moh.mfl.response.JwtAuthenticationResponse;
import com.moh.mfl.security.JwtTokenProvider;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import io.jsonwebtoken.Claims;
import org.springframework.core.env.Environment;

@RestController
@RequestMapping("/mfl/v1/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    ApiUserRepo clientRepository;
    private ApiUsers client;
    private ResponseEntity resp;
    @Autowired
    Environment env;
    private final SecurityConfig config;

    public AuthController() {
        this.config = new SecurityConfig();
    }

    /**
     * Generate access token using clientCode and secretKey
     *
     * @param password
     * @param username
     * @param servletRequest
     * @return Response Entity
     */
    @PostMapping("/token")
    public ResponseEntity<?> token(@RequestHeader("password") String password,
            @RequestHeader("username") String username, HttpServletRequest servletRequest) {
        try {
            this.client = this.clientRepository.findByUsernameAndStatus(username, 1);
            if (this.client != null) {
                resp = this.authenticateClient(password);
            } else {
                resp = new ResponseEntity(new AuthResponse(false, "Username:" + username + " provided is invalid or does not exist!", "", ""), HttpStatus.NOT_ACCEPTABLE);
            }
            return resp;
        } catch (Exception ex) {
            return new ResponseEntity(new AuthResponse(false, "Internal server error occured. Could not generate access token. Contact MFL administrator", "", ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    /**
     * Authenticate client
     *
     * @param secretKey
     * @return Response Entity
     */
    private ResponseEntity authenticateClient(String secretKey) throws Exception {
        if (this.config.verifyKey(secretKey + client.getAuthKey(), client.getPassword())) {
            resp = this.getToken(secretKey);
        } else {
           resp = new ResponseEntity(new AuthResponse(false, "Authentication has failed. Invalid Username/password provided!", "", ""), HttpStatus.UNAUTHORIZED);
        }
        return resp;
    }

    /**
     * Get token
     *
     * @param secretKey
     * @return Response Entity
     */
    private ResponseEntity getToken(String secretKey) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(this.client.getUsername(), secretKey + this.client.getAuthKey()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return new ResponseEntity(new AuthResponse(true, "Success", new JwtAuthenticationResponse(jwt, env.getProperty("jwtExpirationInMs")), ""), HttpStatus.OK);
    }

    /**
     * Validate token and return claims if its still valid
     *
     * @param token
     * @return
     */
    @GetMapping("/validateToken/{token}")
    public ResponseEntity<String> validateToken(@PathVariable(value = "token") String token) {
        if (tokenProvider.validateToken(token)) {
            Claims claims = tokenProvider.getClaims(token);
            return new ResponseEntity(new AuthResponse(true, "Success", claims, ""), HttpStatus.OK);
        }

        return new ResponseEntity(new AuthResponse(false, "Token expired", "Token has expired!", ""), HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/encode/{key}")
    public ResponseEntity<String> encodeKey(@PathVariable(value = "key") String key
    ) {
        String response = "";
        if (!key.isEmpty()) {
            response = this.config.encodeKey(key);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

}
