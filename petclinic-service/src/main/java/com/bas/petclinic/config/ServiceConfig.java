package com.bas.petclinic.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 */
@Configuration
@Import(DBConfig.class)
@ComponentScan(basePackages = {"com.bas.petclinic.service",
        "com.bas.petclinic.mapper", "com.bas.petclinic.security"})
public class ServiceConfig {

}
