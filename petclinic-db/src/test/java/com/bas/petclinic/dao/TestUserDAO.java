package com.bas.petclinic.dao;

import com.bas.petclinic.config.JpaConfig;
import com.bas.petclinic.model.User;
import com.bas.petclinic.model.UserRole;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Tests for UserDAO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfig.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup(value = "classpath:user.xml")
@DatabaseTearDown(value = "classpath:user.xml", type = DatabaseOperation.DELETE_ALL)
public class TestUserDAO {

    @Autowired
    private UserDAO userDAO;

    @Test
    @ExpectedDatabase(value = "classpath:user_expected_create.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testCreateUser() throws Exception{
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(1,"CLIENT"));
        User user = userDAO.createUser("client2", "pwd1", roles);
        System.out.println(user);
    }

    @Test
    @ExpectedDatabase(value = "classpath:user.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testGetAllUsers() throws Exception {
        List<User> users = userDAO.getUsers();
        users.forEach(System.out::println);
    }

    @Test
    @ExpectedDatabase(value = "classpath:user_expected_update.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testUpdateUser() throws Exception {
        User user = userDAO.getUserById(1000L);
        user.setUsername("client2");
        userDAO.updateUser(user);
    }

    @Test
    @ExpectedDatabase(value = "classpath:user_expected_delete.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testDeleteUser() throws Exception {
        userDAO.deleteUserById(1000L);
    }
}
