package com.bas.petclinic.service;

import com.bas.petclinic.config.ServiceConfig;
import com.bas.petclinic.dto.UserDTO;
import com.bas.petclinic.enumeration.UserRoleType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class})
public class TestUserService {

    @Autowired
    private UserService userService;

    @Test
    public void testSaveUser() throws Exception {
        Set<UserRoleType> roles = new HashSet<>();
        roles.add(UserRoleType.СLIENT);
        UserDTO user =  userService.saveUser("owner", "owner123", roles);
        assertNotNull(user.getId());
    }
}
