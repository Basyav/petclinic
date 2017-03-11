package com.bas.petclinic.dao.impl;

import com.bas.petclinic.dao.IssueDAO;
import com.bas.petclinic.model.Employee;
import com.bas.petclinic.model.Issue;
import com.bas.petclinic.model.IssueStatus;
import com.bas.petclinic.model.Pet;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

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
    public Issue createIssue(Employee employee, Pet pet, String description) throws DataAccessException{
        Issue issue = new Issue();
        issue.setEmployee(employee);
        issue.setPet(pet);
        issue.setDescription(description);
        issue.setStatus(IssueStatus.NEW);
        entityManager.persist(issue);
        return issue;
    }

    @Override
    public Issue getIssueById(Long id) {
        return entityManager.find(Issue.class, id);
    }

    @Override
    public Issue updateIssue(Issue issue) throws DataAccessException {
        entityManager.merge(issue);
        return issue;
    }
}
