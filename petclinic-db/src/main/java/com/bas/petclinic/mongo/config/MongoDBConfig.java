package com.bas.petclinic.mongo.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Spring MongoDB config
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.bas.petclinic.mongo.dao")
@ComponentScan(basePackages = "com.bas.petclinic.mongo.dao")
public class MongoDBConfig extends AbstractMongoConfiguration {

    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }

    @Override
    protected String getDatabaseName() {
        return "petclinic";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient();
    }
}
