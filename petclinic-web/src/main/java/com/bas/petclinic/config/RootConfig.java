package com.bas.petclinic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ServiceConfig.class, SecurityConfig.class})
public class RootConfig {
}
