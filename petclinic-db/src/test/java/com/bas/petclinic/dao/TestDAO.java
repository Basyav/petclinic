package com.bas.petclinic.dao;

import com.bas.petclinic.config.DBConfig;
import com.bas.petclinic.model.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dmitry on 3/11/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DBConfig.class)
public class TestDAO {

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Autowired
    private UserDAO userDAO;

    @Test
    @Transactional
    public void testCreateUser() {
        UserRole userRole = userRoleDAO.getUserRoleById(1);
        userDAO.createUser("login1", "pwd1", userRole);
    }
}
