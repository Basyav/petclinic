package com.bas.petclinic.mapper;

import com.bas.petclinic.config.ServiceConfig;
import com.bas.petclinic.dto.ESPetDTO;
import com.bas.petclinic.dto.OwnerDTO;
import com.bas.petclinic.dto.PetDTO;
import com.bas.petclinic.dto.UserDTO;
import com.bas.petclinic.elasticsearch.model.ESPet;
import com.bas.petclinic.model.Owner;
import com.bas.petclinic.model.Pet;
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
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class})
public class MapperTest {

    private User user;
    private Owner owner;

    private static final Logger logger = LoggerFactory.getLogger(MapperTest.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    OwnerMapper ownerMapper;

    @Autowired
    PetMapper petMapper;

    @Autowired
    ESPetMapper esPetMapper;

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
        owner = new Owner();
        owner.setId(1L);
        owner.setUsername(user);
        owner.setFirstName("Любовь");
        owner.setLastName("Кошкина");
        owner.setMiddleName("Владимировна");
        owner.setAddress("ул. Стрелковой девизии");
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

    @Test
    public void testOwnerMapper() throws Exception {
        OwnerDTO ownerDTO = ownerMapper.toOwnerDTO(owner);
        logger.info(ownerDTO.getUser().toString());
        Owner actualOwner = ownerMapper.toOwner(ownerDTO);
        logger.info(actualOwner.toString());
    }

    @Test
    public void testPetMapper() throws Exception {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setDescription("Собака");
        pet.setName("Шарик");
        pet.setCreatedAt(LocalDate.of(2017, 3, 17));
        pet.setOwner(owner);
        PetDTO petDTO = petMapper.toPetDTO(pet);
        logger.info(petDTO.getOwner().toString());
        Pet actualPet = petMapper.toPet(petDTO);
        logger.info(actualPet.getOwner().toString());
    }

    @Test
    public void testESPetMapper() throws Exception {
        ESPet esPet = new ESPet();
        esPet.setId("1");
        esPet.setName("Шарик");
        esPet.setOwner("Любовь Кошкина");
        ESPetDTO esPetDTO = esPetMapper.toESPetDTO(esPet);
        logger.info(esPetDTO.toString());
        assertThat(esPetDTO.getId(), is(1L));
        ESPet actualEsPet = esPetMapper.toESPet(esPetDTO);
        assertThat(actualEsPet.getId(), is("1"));
    }
}
