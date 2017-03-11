package com.bas.petclinic.dao.impl;

import com.bas.petclinic.dao.OwnerDAO;
import com.bas.petclinic.model.Owner;
import com.bas.petclinic.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by dmitry on 3/10/17.
 */
@Repository
public class OwnerDAOImpl implements OwnerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Owner createOwner(String firstName, String lastName, String middleName,
                             User username, String address) throws DataAccessException {
        Owner owner = new Owner();
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        if (middleName != null) {
            owner.setMiddleName(middleName);
        }
        else {
            owner.setMiddleName("");
        }
        owner.setUsername(username);
        owner.setAddress(address);
        entityManager.persist(owner);
        return owner;
    }

    @Override
    public Owner getOwnerById(Long id) {
        return entityManager.find(Owner.class, id);
    }

    @Override
    public Owner updateOwner(Owner owner) throws DataAccessException {
        entityManager.merge(owner);
        return owner;
    }

    @Override
    public void deleteOwnerById(Long id) throws DataAccessException {
        Owner owner = getOwnerById(id);
        entityManager.remove(owner);
    }
}
