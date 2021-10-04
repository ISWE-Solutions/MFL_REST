/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moh.mfl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 *
 * @author chulu
 */
@EnableSwagger2WebMvc
@Configuration
public class SwaggerConfigs {

    public static final Contact SUPPORTED_CONTACTS = new Contact("Francis Chulu", "", "chulu1francis@gmail.com");

    @Bean
    public Docket MFLApi() {
        Set produce = new HashSet<>(Arrays.asList("application/json", "application/xml"));
        Set consume = new HashSet<>(Arrays.asList("application/json", "application/xml"));

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(mFLInfo())
                .produces(produce)
                .consumes(consume);
    }

    private ApiInfo mFLInfo() {
        return new ApiInfoBuilder()
                .title("MFL REST API")
                .description("MFL REST API Documentation")
                .termsOfServiceUrl("https://www.mfl.gov.zm/")
                .contact(SUPPORTED_CONTACTS)
                .license("MFL REST v1.0")
                .licenseUrl("https://www.mfl.gov.zm/licence")
                .version("1.0")
                .build();
    }
}
