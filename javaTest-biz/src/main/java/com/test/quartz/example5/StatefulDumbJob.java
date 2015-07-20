package com.test.quartz.example5;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by zyinyan on 2015/6/30.
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class StatefulDumbJob implements Job {

    public static final String NUM_EXECUTIONS = "NumExecutions";
    public static final String EXECUTION_DELAY = "ExecutionDelay";

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        logger.info("---" + context.getJobDetail().getKey() + " executing.[" + new Date() + "]");

        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        int exeCount = dataMap.containsKey(NUM_EXECUTIONS) ? dataMap.getInt(NUM_EXECUTIONS) :  0;
        exeCount++;
        dataMap.put(NUM_EXECUTIONS, exeCount);

        long delay = dataMap.containsKey(EXECUTION_DELAY) ? dataMap.getLong(EXECUTION_DELAY) : 5000l ;

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            logger.error("{}", e);
        }

        logger.info("  -" + context.getJobDetail().getKey() + " complete (" + exeCount + ").");
    }
}
