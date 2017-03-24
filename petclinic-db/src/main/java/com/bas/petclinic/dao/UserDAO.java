package com.bas.petclinic.dao;

import com.bas.petclinic.model.User;
import com.bas.petclinic.model.UserRole;
import org.springframework.dao.DataAccessException;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 *
 */
public interface UserDAO {

    User createUser(String login, String password, Set<UserRole> roles) throws DataAccessException;
    
    User getUserById(Long id);

    List<User> getUsers();

    User updateUser(User user) throws DataAccessException;

    void deleteUserById(Long id) throws DataAccessException;
}
