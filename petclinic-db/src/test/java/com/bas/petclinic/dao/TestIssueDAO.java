package com.bas.petclinic.dao;

import com.bas.petclinic.config.JpaConfig;
import com.bas.petclinic.model.Employee;
import com.bas.petclinic.model.Issue;
import com.bas.petclinic.model.IssueStatus;
import com.bas.petclinic.model.Pet;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import java.time.LocalDateTime;

/**
 * Tests for IssueDAO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfig.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:issue.xml")
@DatabaseTearDown(value = "classpath:issue.xml", type = DatabaseOperation.DELETE_ALL)
public class TestIssueDAO {

    @Autowired
    private IssueDAO issueDAO;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private PetDAO petDAO;

    @Test
    public void testGetIssue() throws Exception {
        Issue issue = issueDAO.getIssueById(1000L);
        Assert.assertEquals(new Long(1000), issue.getId());
        System.out.println(issue);
    }

    @Test
    @ExpectedDatabase(value = "classpath:issue_expected_create.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testCreateIssue() throws Exception {
        Employee employee = employeeDAO.getEmployeeById(1000L);
        Pet pet = petDAO.getPetById(1000L);
        Issue issue = new Issue("Новое о собаках", LocalDateTime.of(2017, 3, 12, 12, 0, 0), IssueStatus.NEW);
        issue.setPet(pet);
        issue.setEmployee(employee);
        issueDAO.createIssue(issue);
    }

    @Test
    @ExpectedDatabase(value = "classpath:issue_expected_update.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testUpdateIssue() throws Exception {
        Issue issue = issueDAO.getIssueById(1000L);
        issue.setStatus(IssueStatus.IN_PROGRESS);
        issue.setChangedAt(LocalDateTime.of(2017, 3, 12, 14, 0, 0));
        issueDAO.updateIssue(issue);
    }
}
