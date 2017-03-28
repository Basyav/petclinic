package com.bas.petclinic.elasticsearch.dao;

import com.bas.petclinic.elasticsearch.model.ESPet;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 *
 */
public interface ESPetDAO extends ElasticsearchRepository<ESPet, String> {

    List<ESPet> getByName(String name);

    List<ESPet> getByOwner(String owner);
}
