package com.bas.petclinic.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ServiceConfig.class, SecurityConfig.class})
@ComponentScan(basePackages = "com.bas.petclinic.controller")
public class RootConfig {
}
