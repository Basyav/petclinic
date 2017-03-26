package com.bas.petclinic.mapper;

import com.bas.petclinic.dto.OwnerDTO;
import com.bas.petclinic.model.Owner;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * Translates between Owner and OwnerDTO
 */
@Mapper(componentModel = "spring")
public abstract class OwnerMapper {

    @Autowired
    protected UserMapper mapper;

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "middleName", target = "middleName")
    @Mapping(source = "address", target = "address")
    @Mapping(expression = "java(mapper.toUserDTO(owner.getUsername()))", target = "user")
    public abstract OwnerDTO toOwnerDTO(Owner owner);

    @InheritInverseConfiguration
    @Mapping(expression = "java(mapper.toUser(ownerDTO.getUser()))", target = "username")
    public abstract Owner toOwner(OwnerDTO ownerDTO);

    public abstract List<OwnerDTO> toOwnerDTOs(Collection<Owner> owners);

    public abstract List<Owner> toOwners(Collection<OwnerDTO> ownerDTOs);
}
