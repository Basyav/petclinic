package com.bas.petclinic.dao;

import com.bas.petclinic.model.Employee;
import com.bas.petclinic.model.Issue;
import com.bas.petclinic.model.IssueStatus;
import com.bas.petclinic.model.Pet;
import org.springframework.dao.DataAccessException;

/**
 * Created by dmitry on 3/10/17.
 */
public interface IssueDAO {

    Issue createIssue(Employee employee, Pet pet, String description) throws DataAccessException;

    Issue getIssueById(Long id);

    Issue updateIssue(Issue issue) throws DataAccessException;
}
