package com.com.test.quartz.example6;

import com.test.quartz.example6.JobExceptionExample;
import org.junit.Test;

/**
 * Created by zyinyan on 2015/6/30.
 */
public class JobExceptionExampleTest {
    @Test
    public void test1() throws Exception {
        JobExceptionExample.schedule();
    }
}
