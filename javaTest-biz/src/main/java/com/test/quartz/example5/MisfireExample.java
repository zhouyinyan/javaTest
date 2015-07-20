package com.test.quartz.example5;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by zyinyan on 2015/6/30.
 */
public class MisfireExample {

    public static void schedule() throws SchedulerException, InterruptedException {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail jobDetail = JobBuilder.newJob(StatefulDumbJob.class)
                .withIdentity("statefulJob1", "group1")
                .usingJobData(StatefulDumbJob.EXECUTION_DELAY, 10000L)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1","group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?"))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);

        jobDetail = JobBuilder.newJob(StatefulDumbJob.class)
                .withIdentity("statefulJob2", "group1")
                .usingJobData(StatefulDumbJob.EXECUTION_DELAY, 10000L)
                .build();

        trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger2","group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?").withMisfireHandlingInstructionFireAndProceed())
                .build();

        scheduler.scheduleJob(jobDetail, trigger);

        scheduler.start();

        Thread.sleep(300L * 1000L);

        scheduler.shutdown(true);
    }
}
