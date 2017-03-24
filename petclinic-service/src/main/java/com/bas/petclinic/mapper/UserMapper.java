package com.bas.petclinic.mapper;

import com.bas.petclinic.dto.UserDTO;
import com.bas.petclinic.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * Translates between User and UserDTO
 */
@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected UserRoleMapper mapper;

    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "passwordAndSalt", target = "password")
    @Mapping(expression = "java(mapper.roleToRoleType(user.getRole()))", target = "roleType")
    @Mapping(source = "createdAt", target = "createdAt")
    public abstract UserDTO toUserDTO(User user);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "passwordAndSalt")
    @Mapping(expression = "java(mapper.roleTypeToRole(userDTO.getRoleType()))", target = "role")
    @Mapping(source = "createdAt", target = "createdAt")
    public abstract User toUser(UserDTO userDTO);

    public abstract List<UserDTO> toUserDTOs(Collection<User> users);

    public abstract List<User> toUsers(Collection<UserDTO> userDTOs);
}
