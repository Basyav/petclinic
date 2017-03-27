package com.bas.petclinic.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({JpaConfig.class, ESConfig.class, MongoDBConfig.class})
@ComponentScan(basePackages = "com.bas.petclinic.dao")
public class DBConfig {
}
