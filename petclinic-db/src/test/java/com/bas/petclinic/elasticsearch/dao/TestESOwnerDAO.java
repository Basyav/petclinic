package com.bas.petclinic.elasticsearch.dao;

import com.bas.petclinic.elasticsearch.config.ESConfig;
import com.bas.petclinic.elasticsearch.model.ESOwner;
import org.junit.*;
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

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Tests for ESOwnerDAO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ESConfig.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class})
public class TestESOwnerDAO {

    private static EmbeddedElastic embeddedElastic;

    private ESOwner expectedOwner;
    private ESOwner actualOwner;

    @Autowired
    private ESOwnerDAO esOwnerDAO;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    private static Logger logger = LoggerFactory.getLogger(TestESOwnerDAO.class);

    @BeforeClass
    public static void  setUpDB() throws Exception {
        embeddedElastic = EmbeddedElastic.builder()
                .withElasticVersion("2.3.2")
                .withSetting(PopularProperties.TRANSPORT_TCP_PORT, 9300)
                .withSetting(PopularProperties.CLUSTER_NAME, "es-cluster")
//                .withIndex("petclinic", IndexSettings.builder()
//                        .withType("owners", getSystemResourceAsStream("src/test/resources/owners-mapping.json"))
//                        .build())
                .build()
                .start();
    }

    @Before
    public void setUp() throws Exception {
        esTemplate.deleteIndex(ESOwner.class);
        esTemplate.createIndex(ESOwner.class);
        esTemplate.putMapping(ESOwner.class);
        esTemplate.refresh(ESOwner.class);
        expectedOwner = new ESOwner();
        expectedOwner.setId("1");
        expectedOwner.setFio("Кошкина Любовь Владимировна");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        embeddedElastic.stop();
    }

    @Test
    public void testCreateOwner() throws Exception {
        esOwnerDAO.save(expectedOwner);
        actualOwner = esOwnerDAO.findOne(expectedOwner.getId());
        assertEquals(expectedOwner.getFio(), actualOwner.getFio());
    }

    @Test
    public void testDeleteOwner() throws Exception {
        esOwnerDAO.save(expectedOwner);
        esOwnerDAO.delete(expectedOwner);
        actualOwner = esOwnerDAO.findOne(expectedOwner.getId());
        assertNull(actualOwner);
    }

    @Test
    public void testGetOwner() throws Exception {
        esOwnerDAO.save(expectedOwner);
        List<ESOwner> owners = esOwnerDAO.findByFio("Любовь Кошкина");
        logger.info(owners.toString());
        assertThat(owners.size(), is(1));
    }
}
