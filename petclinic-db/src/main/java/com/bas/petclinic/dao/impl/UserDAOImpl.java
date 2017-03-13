package com.bas.petclinic.dao.impl;

import com.bas.petclinic.dao.UserDAO;
import com.bas.petclinic.model.User;
import com.bas.petclinic.model.UserRole;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by dmitry on 3/10/17.
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public User createUser(String login, String password, UserRole role, LocalDate createdAt) throws DataAccessException {
        User user = new User();
        user.setUsername(login);
        user.setPasswordAndSalt(password);
        user.setRole(role);
        user.setCreatedAt(createdAt);
        entityManager.persist(user);
        return user;
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    @Transactional
    public User updateUser(User user) throws DataAccessException{
        entityManager.merge(user);
        return user;
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

}
