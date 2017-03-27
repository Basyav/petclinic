package com.bas.petclinic.dao;

import com.bas.petclinic.config.ESConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

/**
 * Tests for ESOwnerDAO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ESConfig.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class})
public class TestESOwnerDAO {


}
