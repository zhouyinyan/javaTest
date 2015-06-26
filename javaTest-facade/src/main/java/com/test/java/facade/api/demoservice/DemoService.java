package com.test.java.facade.api.demoservice;

import com.test.java.facade.api.demoservice.exceptions.DemoServiceException;

/**
 * Created by zyinyan on 2015/6/26.
 */
public interface DemoService {

    public DemoServiceResult demo(DemoServiceOrder order) throws DemoServiceException;

}
