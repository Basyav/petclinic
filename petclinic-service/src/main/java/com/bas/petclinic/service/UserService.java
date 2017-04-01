package com.bas.petclinic.service;

import com.bas.petclinic.dto.UserDTO;
import com.bas.petclinic.enumeration.UserRoleType;

import java.util.List;
import java.util.Set;

/**
 *
 */
public interface UserService {

    UserDTO saveUser(String login, String password, Set<UserRoleType> roles);

    List<UserDTO> getUsers();

    UserDTO findByUsername(String username);
}
