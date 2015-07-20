package com.test.quartz.example4;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by zyinyan on 2015/6/30.
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ColorJob implements Job {

    Logger logger = LoggerFactory.getLogger(getClass());

    public static  final  String FAVORITE_COLOR = "favorite_color";
    public static  final  String EXECUTION_COUNT = "execution_count";

    private int _counter = 1;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        String color = dataMap.getString(FAVORITE_COLOR);
        JobKey jobKey = context.getJobDetail().getKey();
        int count  = dataMap.getInt(EXECUTION_COUNT);
        count++;
        dataMap.put(EXECUTION_COUNT, count);

        logger.info("ColorJob: " + jobKey + " executing at " + new Date() + "\n" +
                "  favorite color is " + color + "\n" +
                "  execution count (from job map) is " + count + "\n" +
                "  execution count (from job member variable) is " + _counter);

        _counter++;
    }
}
