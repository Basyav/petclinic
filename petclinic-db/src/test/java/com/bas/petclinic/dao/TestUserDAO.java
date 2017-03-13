package com.bas.petclinic.dao;

import com.bas.petclinic.config.JpaConfig;
import com.bas.petclinic.model.User;
import com.bas.petclinic.model.UserRole;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.After;
import org.junit.Before;
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
import java.util.List;

/**
 * Created by dmitry on 3/11/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfig.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:user.xml")
public class TestUserDAO {

    @Autowired
    private UserDAO userDAO;

    private UserRole userRole;

    @Before
    public void setUp() {
        userRole = new UserRole();
        userRole.setId(1);
        userRole.setName("CLIENT");
    }

    @After
    public void tearDown() {
        userRole = null;
    }

    @Test
    @ExpectedDatabase(value = "classpath:expected_create_user.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testCreateUser() throws Exception{
        userDAO.createUser("client2", "pwd1", userRole, LocalDate.now());
    }

    @Test
    @ExpectedDatabase(value = "classpath:user.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testGetAllUser() throws Exception {
        List<User> users = userDAO.getUsers();
        users.forEach(System.out::println);
    }

    @Test
    @ExpectedDatabase(value = "classpath:expected_update_user.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testUpdateUser() throws Exception {
        User user = userDAO.createUser("client2", "pwd1", userRole, LocalDate.now());
        user.setUsername("client3");
        userDAO.updateUser(user);
    }

    @Test
    @ExpectedDatabase(value = "classpath:user.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testDeleteUser() throws Exception {
        User user = userDAO.createUser("client2", "pwd1", userRole, LocalDate.now());
        userDAO.deleteUserById(user.getId());
    }
}
