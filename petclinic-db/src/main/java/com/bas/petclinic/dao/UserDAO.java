package com.bas.petclinic.dao;

import com.bas.petclinic.model.User;
import com.bas.petclinic.model.UserRole;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by dmitry on 3/10/17.
 */
public interface UserDAO {

    User createUser(String login, String password, UserRole role) throws DataAccessException;
    
    List<User> getUsers();

    User updateUser(User user) throws DataAccessException;

    void deleteUserById(Long id) throws DataAccessException;
}
