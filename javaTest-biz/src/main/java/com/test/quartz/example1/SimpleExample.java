package com.test.quartz.example1;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by zyinyan on 2015/6/29.
 */
public class SimpleExample {

    public void initAndStart() throws SchedulerException, InterruptedException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                                .withIdentity("job1", "group1")
                                .build();
        Date runTime = evenMinuteDate(new Date());

        Trigger trigger = newTrigger()
                            .withIdentity("trigger1", "group1")
                            .startAt(runTime)
                            .build();
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();

        Thread.sleep(90L * 1000L);

        scheduler.shutdown(true);
    }
}
