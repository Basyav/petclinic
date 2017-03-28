package com.bas.petclinic.mapper;

import com.bas.petclinic.dto.ESOwnerDTO;
import com.bas.petclinic.elasticsearch.model.ESOwner;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

/**
 *
 */
@Mapper(componentModel = "spring")
public interface ESOwnerMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "fio", target = "FIO")
    ESOwnerDTO toESOwnerDTO(ESOwner esOwner);

    @InheritInverseConfiguration
    ESOwner toESOwner(ESOwnerDTO esOwnerDTO);

    List<ESOwnerDTO> toESOwnerDTOs(Collection<ESOwner> esOwners);

    List<ESOwner> toESOwners(Collection<ESOwnerDTO> esOwnerDTOs);
}
