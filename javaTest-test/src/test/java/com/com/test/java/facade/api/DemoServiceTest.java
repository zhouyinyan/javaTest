package com.com.test.java.facade.api;

import com.gzt.test.base.BaseDubboConsumerTest;
import com.test.java.facade.api.demoservice.DemoService;
import com.test.java.facade.api.demoservice.DemoServiceOrder;
import com.test.java.facade.api.demoservice.DemoServiceResult;
import org.junit.Test;

import static org.junit.Assert.*;

import javax.annotation.Resource;

/**
 * Created by zyinyan on 2015/6/26.
 */
public class DemoServiceTest extends BaseDubboConsumerTest {

    @Resource(name = "demoService")
    DemoService demoService;

    @Test
    public void testDemo() throws Exception {
        DemoServiceOrder order =  new DemoServiceOrder();
        order.setOrderNo("orderNo0001");
        DemoServiceResult result = demoService.demo(order);
        assertEquals("orderNo0001", result.getOrderNo());
    }
}
