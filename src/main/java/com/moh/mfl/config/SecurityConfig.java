package com.moh.mfl.config;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 *
 * @author Francis chulu
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final long MAX_AGE_SECS = 3600;

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .maxAge(MAX_AGE_SECS);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling();
                // .authenticationEntryPoint(unauthorizedHandler)
                // .and()
                //.sessionManagement()
                //  .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/mfl/**")
//                .permitAll()
//                .antMatchers("/swagger-ui/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated();

    }

    public String encodeKey(String key) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedKey = passwordEncoder.encode(key);
        return hashedKey;
    }

    public boolean verifyKey(String key, String keyHash) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(key, keyHash);
    }

    public String generateHash(String key) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(key);
    }

    public String generateRandomString(Integer length) {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                //.selectFrom("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray())
                .withinRange('0', 'z')
                .build();
        String authKey = generator.generate(length);
        return !authKey.isEmpty() ? authKey : "";
    }
}
