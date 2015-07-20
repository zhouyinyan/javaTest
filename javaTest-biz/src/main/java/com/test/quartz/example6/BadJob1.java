package com.test.quartz.example6;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zyinyan on 2015/6/30.
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class BadJob1 implements Job {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            int zero = 0;
            int calculation = 4815 / zero;
        }catch (Exception e) {

            logger.info("--- Error in job!");
            JobExecutionException je = new JobExecutionException(e);
            //je.refireImmediately();
            throw je ;
        }
    }
}
