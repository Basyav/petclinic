package com.bas.petclinic.elasticsearch.dao;

import com.bas.petclinic.elasticsearch.model.ESOwner;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 *
 */
public interface ESOwnerDAO extends ElasticsearchRepository<ESOwner, String> {

    List<ESOwner> findByFio(String name);
}
