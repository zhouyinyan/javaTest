package com.test.quartz.example6;

import com.test.quartz.example5.StatefulDumbJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by zyinyan on 2015/6/30.
 */
public class JobExceptionExample {

    public static void schedule() throws SchedulerException, InterruptedException {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail jobDetail = JobBuilder.newJob(BadJob1.class)
                .withIdentity("BadJob1", "group1")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1","group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?"))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);

        scheduler.start();

        Thread.sleep(300L * 1000L);

        scheduler.shutdown(true);
    }

}
