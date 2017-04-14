package com.bas.petclinic.dao.impl;

import com.bas.petclinic.dao.IssueDAO;
import com.bas.petclinic.model.Issue;
import org.springframework.dao.DataAccessException;
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
public class IssueDAOImpl implements IssueDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Issue createIssue(Issue issue) throws DataAccessException{
        entityManager.persist(issue);
        return issue;
    }

    @Override
    @Transactional(readOnly = true)
    public Issue getIssueById(Long id) {
        return entityManager.find(Issue.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Issue> getAllIssuesByOwnerId(Long id) {
        return entityManager.createQuery("SELECT i FROM Issue i " +
                "LEFT JOIN fetch i.pet " +
                "LEFT JOIN fetch i.employee " +
                "WHERE i.pet.owner.id = :id " +
                "ORDER BY i.changedAt DESC").setParameter("id", id).getResultList();
    }

    @Override
    public Issue updateIssue(Issue issue) throws DataAccessException {
        entityManager.merge(issue);
        return issue;
    }
}
