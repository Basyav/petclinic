package com.bas.petclinic.dao;

import com.bas.petclinic.config.JpaConfig;
import com.bas.petclinic.model.Employee;
import com.bas.petclinic.model.User;
import com.bas.petclinic.model.UserRole;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfig.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup(value = "classpath:employee.xml")
@DatabaseTearDown(value = "classpath:employee.xml", type = DatabaseOperation.DELETE_ALL)
public class TestEmployeeDAO {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private UserDAO userDAO;

    @Test
    public void testGetEmployee() throws Exception {
        Employee employee = employeeDAO.getEmployeeById(1000L);
        assertEquals(new Long(1000),employee.getId());
        System.out.println(employee);
    }

    @Test
    @ExpectedDatabase(value = "classpath:employee_expected_create.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testCreateEmployee() throws Exception {
        User user = userDAO.createUser("employee3", "pwd1", new UserRole(2, "EMPLOYEE"), LocalDate.of(2017,3,13));
        Employee employee = employeeDAO.createEmployee("Иван", "Собакевич", "Владимирович", user,  1);
        assertEquals(user, employee.getUsername());
    }

    @Test
    @ExpectedDatabase(value = "classpath:employee_expected_update.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testUpdateEmployee() throws Exception {
        Employee employee = employeeDAO.getEmployeeById(1000L);
        employee.setFirstName("Мария");
        employeeDAO.updateEmployee(employee);
    }

    @Test
    @ExpectedDatabase(value = "classpath:employee_expected_delete.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testDeleteEmployee() throws Exception {
        employeeDAO.deleteEmployeeById(1001L);
    }

}
