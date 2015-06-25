package com.gzt.test.base;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;
/**
 * Created by zyinyan on 2015/6/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/*.xml"})
public class BaseSpringContextTest {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    static {
        System.setProperty("spring.profiles.active", "dev");
    }

    @Before
    public void setUp() throws Exception {
        logger.info("begin test......");
    }

    @After
    public void tearDown() throws Exception {
        logger.info("end test......");
    }

    @Test
    public void testName() throws Exception {
        assertTrue(true);
    }
}
