package com.bas.petclinic.dao.impl;

import com.bas.petclinic.dao.PetDAO;
import com.bas.petclinic.model.Owner;
import com.bas.petclinic.model.Pet;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by dmitry on 3/10/17.
 */
@Repository
public class PetDAOImpl implements PetDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Pet createPet(String name, String description, Owner owner) {
        Pet pet = new Pet();
        pet.setName(name);
        pet.setDescription(description);
        pet.setOwner(owner);
        entityManager.persist(pet);
        return pet;
    }

    @Override
    public Pet getPetById(Long id) {
        return entityManager.find(Pet.class, id);
    }

    @Override
    @Transactional
    public Pet updatePet(Pet pet) throws DataAccessException {
        entityManager.merge(pet);
        return pet;
    }
}
