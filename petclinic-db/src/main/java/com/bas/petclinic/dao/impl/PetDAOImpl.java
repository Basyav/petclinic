package com.bas.petclinic.dao.impl;

import com.bas.petclinic.dao.PetDAO;
import com.bas.petclinic.model.Pet;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 */
@Repository
public class PetDAOImpl implements PetDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Pet getPetById(Long id) {
        return entityManager.find(Pet.class, id);
    }
}
