package com.bas.petclinic.service.impl;

import com.bas.petclinic.dao.UserDAO;
import com.bas.petclinic.dto.UserDTO;
import com.bas.petclinic.enumeration.UserRoleType;
import com.bas.petclinic.mapper.UserMapper;
import com.bas.petclinic.mapper.UserRoleMapper;
import com.bas.petclinic.model.User;
import com.bas.petclinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO saveUser(String login, String password, Set<UserRoleType> roles) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = userDAO.createUser(login, encoder.encode(password), userRoleMapper.roleTypesToRoles(roles));
        return userMapper.toUserDTO(user);
    }

    @Override
    public List<UserDTO> getUsers() {
        return userMapper.toUserDTOs(userDAO.getUsers());
    }

    @Override
    public UserDTO findByUsername(String username) {
        User user = userDAO.getUserByLogin(username);
        return (user != null ? userMapper.toUserDTO(user) : null);
    }
}
