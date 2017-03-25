package com.bas.petclinic.mapper;

import com.bas.petclinic.dto.PetDTO;
import com.bas.petclinic.model.Pet;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

/**
 * Translates between Pet and PetDTO
 */
@Mapper(componentModel = "spring")
public abstract class PetMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "owner", target = "owner")
    @Mapping(source = "issues", target = "issues")
    public abstract PetDTO toPetDTO(Pet pet);

    @InheritInverseConfiguration
    public abstract Pet toPet(PetDTO petDTO);

    public abstract List<PetDTO> toPetDTOs(Collection<Pet> pets);

    public abstract List<Pet> toPets(Collection<PetDTO> petDTOs);
}
