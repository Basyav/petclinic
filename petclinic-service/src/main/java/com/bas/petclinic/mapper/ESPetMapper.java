package com.bas.petclinic.mapper;

import com.bas.petclinic.dto.ESPetDTO;
import com.bas.petclinic.elasticsearch.model.ESPet;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

/**
 *
 */
@Mapper(componentModel = "spring")
public interface ESPetMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "owner", target = "ownerFIO")
    ESPetDTO toESPetDTO(ESPet esPet);

    @InheritInverseConfiguration
    ESPet toESPet(ESPetDTO esPetDTO);

    List<ESPetDTO> toESPetDTOs(Collection<ESPet> esPets);

    List<ESPet> toESPets(Collection<ESPetDTO> esPetDTOs);

}
