package com.bas.petclinic.service;

import com.bas.petclinic.dto.PetDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
public interface PetService {

    List<PetDTO> getAllPetsByOwnerId(Long id);

    PetDTO getPetById(Long id);
}
