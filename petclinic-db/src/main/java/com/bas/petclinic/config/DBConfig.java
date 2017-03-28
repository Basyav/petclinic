package com.bas.petclinic.config;

import com.bas.petclinic.elasticsearch.config.ESConfig;
import com.bas.petclinic.mongo.config.MongoDBConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({JpaConfig.class, ESConfig.class, MongoDBConfig.class})
public class DBConfig {
}
