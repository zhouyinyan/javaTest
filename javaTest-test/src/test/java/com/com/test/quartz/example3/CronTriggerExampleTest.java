package com.com.test.quartz.example3;

import com.test.quartz.example3.CronTriggerExample;
import org.junit.Test;

/**
 * Created by zyinyan on 2015/6/29.
 */
public class CronTriggerExampleTest {

    @Test
    public void test1() throws Exception {
        CronTriggerExample.cronTiggerSchedule1();
    }

    @Test
    public void test2() throws Exception {
        CronTriggerExample.cronTiggerSchedule2();
    }
}
