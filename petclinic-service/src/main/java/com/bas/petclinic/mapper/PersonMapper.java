package com.bas.petclinic.mapper;

import com.bas.petclinic.dto.PersonDTO;
import com.bas.petclinic.model.Person;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Translates between Person and PersonDTO
 */
@Mapper(componentModel = "spring")
public abstract class PersonMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "middleName", target = "middleName")
    @Mapping(source = "username", target = "user")
    public abstract PersonDTO toPersonDTO(Person person);

    @InheritInverseConfiguration
    public abstract Person toPerson(PersonDTO personDTO);
}
