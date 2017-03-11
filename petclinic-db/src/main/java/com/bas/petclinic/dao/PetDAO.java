package com.bas.petclinic.dao;

import com.bas.petclinic.model.Owner;
import com.bas.petclinic.model.Pet;
import org.springframework.dao.DataAccessException;

/**
 * Created by dmitry on 3/10/17.
 */
public interface PetDAO {

    Pet createPet(String name, String description, Owner owner) throws DataAccessException;

    Pet getPetById(Long id);

    Pet updatePet(Pet pet) throws DataAccessException;
}
