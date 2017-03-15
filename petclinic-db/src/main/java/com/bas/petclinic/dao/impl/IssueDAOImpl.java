package com.bas.petclinic.dao.impl;

import com.bas.petclinic.dao.IssueDAO;
import com.bas.petclinic.model.Issue;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by dmitry on 3/10/17.
 */
@Repository
public class IssueDAOImpl implements IssueDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Issue createIssue(Issue issue) throws DataAccessException{
        entityManager.persist(issue);
        return issue;
    }

    @Override
    public Issue getIssueById(Long id) {
        return entityManager.find(Issue.class, id);
    }

    @Override
    @Transactional
    public Issue updateIssue(Issue issue) throws DataAccessException {
        entityManager.merge(issue);
        return issue;
    }
}
