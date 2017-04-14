package com.bas.petclinic.dao;

import com.bas.petclinic.config.JpaConfig;
import com.bas.petclinic.model.IssueStatus;
import com.bas.petclinic.model.Pet;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Tests for PetDAO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfig.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:pet.xml")
@DatabaseTearDown(value = "classpath:pet.xml", type = DatabaseOperation.DELETE_ALL)
public class TestPetDAO {

    @Autowired
    PetDAO petDAO;

    @Test
    public void testGetPet() throws Exception {
        Pet pet = petDAO.getPetById(1000L);
        assertEquals(new Long(1000), pet.getId());
        System.out.println(pet);
    }

    @Test
    public void testGetPetWithIssuesById() throws Exception {
        Pet pet = petDAO.getPetWithIssuesById(1000L);
        assertEquals(new Long(1000), pet.getId());
        assertEquals(IssueStatus.DONE, pet.getIssues().get(0).getStatus());
    }

    @Test
    public void testGetAllPetsByOwnerId() throws Exception {
        List<Pet> pets = petDAO.getAllPetsByOwnerId(1000L);
        assertThat(pets.size(), is(2));
        assertEquals(IssueStatus.DONE, pets.get(0).getIssues().get(0).getStatus());
    }
}
