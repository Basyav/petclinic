package com.bas.petclinic.dao;

import com.bas.petclinic.model.Employee;
import com.bas.petclinic.model.Issue;
import com.bas.petclinic.model.IssueStatus;
import com.bas.petclinic.model.Pet;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 *
 */
public interface IssueDAO {

    Issue createIssue(Issue issue) throws DataAccessException;

    Issue getIssueById(Long id);
    
    List<Issue> getAllIssuesByOwnerId(Long id);

    Issue updateIssue(Issue issue) throws DataAccessException;
}
