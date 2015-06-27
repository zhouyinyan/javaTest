package com.test.java.common.dubbo.rpc.filter;

import com.alibaba.dubbo.rpc.*;
import com.test.java.common.constants.AppConstants;
import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * dubbo性能日志
 * <p/>
 * <h3>Usage Examples</h3>
 * 1.配置所有consumer,请在dubbo:consumer节点上添加filter
 *
 * <pre class="code">
 * {@code
 * <dubbo:consumer filter="performanceFilter"/>
 * }
 *
 * Created by zyinyan on 2015/6/27.
 */
public class PerformanceFilter implements Filter {

    private static final Logger perlogger = LoggerFactory.getLogger(AppConstants.PERFORMANCE_LOGGER);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String dubboServiceName = invoker.getInterface().getSimpleName();
        String dubboMethodName = invocation.getMethodName();
        StopWatch stopWatch = new Slf4JStopWatch(dubboServiceName + "." + dubboMethodName, perlogger);
        try {
            return invoker.invoke(invocation);
        } finally {
            stopWatch.stop();
        }
    }
}
