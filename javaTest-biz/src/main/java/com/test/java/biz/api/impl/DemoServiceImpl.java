package com.test.java.biz.api.impl;

import com.test.java.common.constants.AppConstants;
import com.test.java.facade.api.demoservice.DemoService;
import com.test.java.facade.api.demoservice.DemoServiceOrder;
import com.test.java.facade.api.demoservice.DemoServiceResult;
import com.test.java.facade.api.demoservice.exceptions.DemoServiceException;
import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zyinyan on 2015/6/26.
 */
public class DemoServiceImpl implements DemoService {

    Logger logger = LoggerFactory.getLogger(getClass());

    Logger performanceLogger = LoggerFactory.getLogger(AppConstants.PERFORMANCE_LOGGER);

    @Override
    public DemoServiceResult demo(DemoServiceOrder order) throws DemoServiceException {

        StopWatch stopWatch = new Slf4JStopWatch("execute DemoServiceImpl.deme()",performanceLogger);
        logger.info("order:{}",order);

        DemoServiceResult result = new DemoServiceResult();
        result.setOrderNo(order.getOrderNo());

        logger.info("result:{}", result);
        stopWatch.stop();
        return result;
    }
}
