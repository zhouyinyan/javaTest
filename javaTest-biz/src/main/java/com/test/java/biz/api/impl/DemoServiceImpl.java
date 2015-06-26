package com.test.java.biz.api.impl;

import com.test.java.facade.api.demoservice.DemoService;
import com.test.java.facade.api.demoservice.DemoServiceOrder;
import com.test.java.facade.api.demoservice.DemoServiceResult;
import com.test.java.facade.api.demoservice.exceptions.DemoServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zyinyan on 2015/6/26.
 */
public class DemoServiceImpl implements DemoService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public DemoServiceResult demo(DemoServiceOrder order) throws DemoServiceException {
        logger.info("order:{}",order);

        DemoServiceResult result = new DemoServiceResult();
        result.setOrderNo(order.getOrderNo());

        logger.info("result:{}", result);
        return result;
    }
}
