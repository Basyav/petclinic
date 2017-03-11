package com.bas.petclinic.dao.impl;

import com.bas.petclinic.dao.UserRoleDAO;
import com.bas.petclinic.model.UserRole;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by dmitry on 3/10/17.
 */
@Repository
public class UserRoleDAOImpl implements UserRoleDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserRole getUserRoleById(Integer id) {
        return entityManager.find(UserRole.class, id);
    }
}
