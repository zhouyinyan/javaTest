package com.com.test.quartz.example5;

import com.test.quartz.example5.MisfireExample;
import org.junit.Test;

/**
 * Created by zyinyan on 2015/6/30.
 */
public class MisfireExampleTest {

    @Test
    public void test1() throws Exception {
        MisfireExample.schedule();
    }
}
