package com.bas.petclinic.mapper;

import com.bas.petclinic.dto.OwnerDTO;
import com.bas.petclinic.model.Owner;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

/**
 * Translates between Owner and OwnerDTO
 */
@Mapper(componentModel = "spring")
public abstract class OwnerMapper {

    @Mapping(source = "address", target = "address")
    public abstract OwnerDTO toOwnerDTO(Owner owner);

    @InheritInverseConfiguration
    public abstract Owner toOwner(OwnerDTO ownerDTO);

    public abstract List<OwnerDTO> toOwnerDTOs(Collection<Owner> owners);

    public abstract List<Owner> toOwners(Collection<OwnerDTO> ownerDTOs);
}
