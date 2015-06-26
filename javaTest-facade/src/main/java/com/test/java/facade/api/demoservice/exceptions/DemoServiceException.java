package com.test.java.facade.api.demoservice.exceptions;

/**
 * Created by zyinyan on 2015/6/26.
 */
public class DemoServiceException extends RuntimeException {
    public DemoServiceException() {
    }

    public DemoServiceException(String message) {
        super(message);
    }

    public DemoServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DemoServiceException(Throwable cause) {
        super(cause);
    }

    public DemoServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
