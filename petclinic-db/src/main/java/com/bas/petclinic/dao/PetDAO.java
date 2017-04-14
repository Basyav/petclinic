package com.bas.petclinic.dao;


import com.bas.petclinic.model.Pet;

import java.util.List;

/**
 *
 */
public interface PetDAO {

    Pet getPetById(Long id);

    Pet getPetWithIssuesById(Long id);

    List<Pet> getAllPetsByOwnerId(Long id);
}
