package com.com.test.quartz.example4;

import com.test.quartz.example4.JobStateExample;
import org.junit.Test;

/**
 * Created by zyinyan on 2015/6/30.
 */
public class JobStateExampleTest {

    @Test
    public void test1() throws Exception {
        JobStateExample.schedule();
    }
}
