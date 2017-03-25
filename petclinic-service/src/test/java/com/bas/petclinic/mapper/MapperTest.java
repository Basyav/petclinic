package com.bas.petclinic.mapper;

import com.bas.petclinic.config.ServiceConfig;
import com.bas.petclinic.dto.UserDTO;
import com.bas.petclinic.model.User;
import com.bas.petclinic.model.UserRole;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("classpath:roles.xml")
public class MapperTest {

    private User user;

    private static final Logger logger = LoggerFactory.getLogger(MapperTest.class);

    @Autowired
    UserMapper userMapper;

    @Before
    public void setUp() throws  Exception {
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(1,"CLIENT"));
        user = new User();
        user.setId(1L);
        user.setUsername("client");
        user.setRoles(roles);
        user.setPasswordAndSalt("pwd");
        user.setCreatedAt(LocalDate.of(2017, 3, 17));
    }

    @Test
    public void testUserMapper() throws Exception {
        UserDTO userDTO = userMapper.toUserDTO(user);
        logger.info(userDTO.getRoleTypes().toString());
        assertEquals(user.getUsername(), userDTO.getUsername());
        User actualUser = userMapper.toUser(userDTO);
        assertEquals(userDTO.getPassword(), actualUser.getPasswordAndSalt());
        logger.info(actualUser.getRoles().toString());
    }


}
