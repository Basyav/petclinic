package com.bas.petclinic.dao.impl;

import com.bas.petclinic.dao.OwnerDAO;
import com.bas.petclinic.model.Owner;
import com.bas.petclinic.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by dmitry on 3/10/17.
 */
@Repository
public class OwnerDAOImpl implements OwnerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
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
    public Owner getOwnerByUserId(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery();
        Root<Owner> i = criteria.from(Owner.class);
        Owner owner = (Owner) entityManager.createQuery(
                criteria.select(i).where(
                        builder.equal(
                                i.get("username").get("id"),
                                builder.parameter(Long.class, "userId")
                        )
                )
        ).setParameter("userId", id).getSingleResult();
        return owner;
    }

    @Override
    @Transactional
    public Owner updateOwner(Owner owner) throws DataAccessException {
        entityManager.merge(owner);
        return owner;
    }

    @Override
    @Transactional
    public void deleteOwnerById(Long id) throws DataAccessException {
        Owner owner = getOwnerById(id);
        entityManager.remove(owner);
    }
}
