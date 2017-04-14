package com.bas.petclinic.dao.impl;

import com.bas.petclinic.dao.PetDAO;
import com.bas.petclinic.model.Pet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 */
@Repository
@Transactional
public class PetDAOImpl implements PetDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Pet getPetById(Long id) {
        return entityManager.find(Pet.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pet getPetWithIssuesById(Long id) {
        return (Pet) entityManager.createQuery("SELECT DISTINCT p FROM Pet p " +
                "LEFT JOIN fetch p.issues i " +
                "WHERE p.id = :id " +
                "ORDER BY i.changedAt DESC")
                .setParameter("id", id).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pet> getAllPetsByOwnerId(Long id) {
        return entityManager.createQuery("SELECT DISTINCT p FROM Pet p " +
                "LEFT JOIN fetch p.issues i " +
                "WHERE p.owner.id = :id " +
                "ORDER BY i.changedAt DESC")
                .setParameter("id", id).getResultList();
    }
}
