package com.test.quartz.example3;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by zyinyan on 2015/6/29.
 */
public class CronTriggerExample {

    public static void cronTiggerSchedule1() throws SchedulerException, InterruptedException {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class)
                                .withIdentity("job1", "group1")
                                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                                .withIdentity("trigger1","group1")
                                .withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ?"))
                                .build();

        scheduler.scheduleJob(jobDetail, trigger);

        scheduler.start();

        Thread.sleep(300L * 1000L);

        scheduler.shutdown(true);
    }


    public static void cronTiggerSchedule2() throws SchedulerException, InterruptedException {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class)
                .withIdentity("job2", "group2")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger2","group2")
                .withSchedule(CronScheduleBuilder.cronSchedule("15 0/2 * * * ?"))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);

        scheduler.start();

        Thread.sleep(300L * 1000L);

        scheduler.shutdown(true);
    }


}
