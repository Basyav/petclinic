package com.bas.petclinic.dao;

import com.bas.petclinic.config.JpaConfig;
import com.bas.petclinic.model.Owner;
import com.bas.petclinic.model.Pet;
import com.bas.petclinic.model.User;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
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

import static org.junit.Assert.assertEquals;
/**
 * Tests for OwnerDAO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfig.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:owner.xml")
@DatabaseTearDown(value = "classpath:owner.xml", type = DatabaseOperation.DELETE_ALL)
public class TestOwnerDAO {

    @Autowired
    private OwnerDAO ownerDAO;

    @Autowired
    private UserDAO userDAO;

    @Test
    public void testGetOwner() throws Exception {
        Owner owner = ownerDAO.getOwnerById(1000L);
        assertEquals(new Long(1000), owner.getId());
    }

    @Test
    public void testGetOwnerByUserId() throws Exception {
        Owner owner = ownerDAO.getOwnerByUserId(1000L);
        assertEquals(new Long(1000), owner.getId());
    }

    @Test
    @ExpectedDatabase(value = "classpath:owner_expected_create.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testCreateOwner() throws Exception {
        User user = userDAO.getUserById(1002L);
        Owner owner = ownerDAO.createOwner("Снежана", "Самохвалова", "Владимировна", user, "ул. Стрелковой девизии");
        assertEquals(user, owner.getUsername());
    }

    @Test
    @ExpectedDatabase(value = "classpath:owner_expected_update.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testUpdateOwner() throws Exception {
        Owner owner = ownerDAO.getOwnerById(1000L);
        owner.setAddress("ул. Пушкина");
        ownerDAO.updateOwner(owner);
    }

    @Test
    @ExpectedDatabase(value = "classpath:owner_expected_delete.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testDeleteOwner() throws Exception {
        ownerDAO.deleteOwnerById(1000L);
    }
}
