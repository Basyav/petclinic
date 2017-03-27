package com.bas.petclinic.dao.impl;

import com.bas.petclinic.model.ESOwner;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 *
 */
public interface ESOwnerDAO extends ElasticsearchRepository<ESOwner, String> {

    List<ESOwner> getByName(String name);
}
