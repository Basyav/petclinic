package com.bas.petclinic.elasticsearch.dao;

import com.bas.petclinic.elasticsearch.config.ESConfig;
import com.bas.petclinic.elasticsearch.model.ESPet;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import pl.allegro.tech.embeddedelasticsearch.EmbeddedElastic;
import pl.allegro.tech.embeddedelasticsearch.PopularProperties;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

/**
 * Tests for ESOwnerDAO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ESConfig.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class})
public class TestESPetDAO {

    private static EmbeddedElastic embeddedElastic;

    private ESPet expectedPet;
    private ESPet actualPet;

    @Autowired
    private ESPetDAO esPetDAO;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    private static Logger logger = LoggerFactory.getLogger(TestESPetDAO.class);

    @BeforeClass
    public static void  setUpDB() throws Exception {
        embeddedElastic = EmbeddedElastic.builder()
                .withElasticVersion("2.3.2")
                .withSetting(PopularProperties.TRANSPORT_TCP_PORT, 9300)
                .withSetting(PopularProperties.CLUSTER_NAME, "es-cluster")
                .build()
                .start();
    }

    @Before
    public void setUp() throws Exception {
        esTemplate.deleteIndex(ESPet.class);
        esTemplate.createIndex(ESPet.class);
        esTemplate.putMapping(ESPet.class);
        esTemplate.refresh(ESPet.class);
        expectedPet = new ESPet();
        expectedPet.setId("1");
        expectedPet.setName("Шарик");
        expectedPet.setOwner("Любовь Кошкина");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        embeddedElastic.stop();
    }

    @Test
    public void createPet() throws Exception {
        esPetDAO.save(expectedPet);
        actualPet = esPetDAO.findOne(expectedPet.getId());
        assertEquals(expectedPet.getName(), actualPet.getName());
    }

    @Test
    public void updatePet() throws Exception {
        esPetDAO.save(expectedPet);
        expectedPet.setOwner("Любовь Кошкина Владимировна");
        esPetDAO.save(expectedPet);
        actualPet = esPetDAO.findOne(expectedPet.getId());
        logger.info(actualPet.toString());
        assertEquals(expectedPet.getOwner(), actualPet.getOwner());
    }

    @Test
    public void deletePet() throws Exception {
        esPetDAO.save(expectedPet);
        esPetDAO.delete(expectedPet);
        actualPet = esPetDAO.findOne(expectedPet.getId());
        assertNull(actualPet);
    }

    @Test
    public void findPet() throws Exception {
        ESPet secondPet = new ESPet();
        secondPet.setId("2");
        secondPet.setName("Мурзик");
        secondPet.setOwner("Любовь Кошкина");
        esPetDAO.save(expectedPet);
        esPetDAO.save(secondPet);
        List<ESPet> petsByName = esPetDAO.getByName("Шарик");
        assertThat(petsByName.contains(expectedPet), is(true));
        assertThat(petsByName.contains(secondPet), is(false));
        List<ESPet> petsByOwner = esPetDAO.getByOwner("Кошкина");
        assertThat(petsByOwner.size(), is(2));
        logger.info(petsByOwner.toString());
    }
}
