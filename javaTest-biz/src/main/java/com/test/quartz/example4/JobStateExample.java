package com.test.quartz.example4;

import com.test.quartz.example3.SimpleJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by zyinyan on 2015/6/30.
 */
public class JobStateExample {

    public static void schedule() throws SchedulerException, InterruptedException {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail jobDetail = JobBuilder.newJob(ColorJob.class)
                .withIdentity("job2", "group2")
                .build();

//        jobDetail.getJobDataMap().put(ColorJob.FAVORITE_COLOR,"green");
        jobDetail.getJobDataMap().put(ColorJob.EXECUTION_COUNT,1);

        jobDetail.getJobDataMap().put(ColorJob.FAVORITE_COLOR,"green");

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger2","group2")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);

        scheduler.start();

        Thread.sleep(300L * 1000L);

        scheduler.shutdown(true);
    }

}
