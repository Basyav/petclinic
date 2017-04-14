package com.bas.petclinic.service.impl;

import com.bas.petclinic.dao.PetDAO;
import com.bas.petclinic.dto.PetDTO;
import com.bas.petclinic.mapper.PetMapper;
import com.bas.petclinic.model.Pet;
import com.bas.petclinic.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetDAO petDAO;

    @Autowired
    private PetMapper mapper;

    @Override
    public List<PetDTO> getAllPetsByOwnerId(Long id) {
        List<Pet> pets = petDAO.getAllPetsByOwnerId(id);
        return (pets.isEmpty() ? null : mapper.toPetDTOs(pets)) ;
    }

    @Override
    public PetDTO getPetById(Long id) {
        Pet pet = petDAO.getPetWithIssuesById(id);
        return mapper.toPetDTO(pet);
    }
}
