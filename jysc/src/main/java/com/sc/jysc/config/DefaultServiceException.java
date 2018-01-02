package com.sc.jysc.config;

/**
 * 自定义的异常
 */
public class DefaultServiceException extends Exception{
    public DefaultServiceException() {
    }

    public DefaultServiceException(String message) {
        super(message);
    }

    public DefaultServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DefaultServiceException(Throwable cause) {
        super(cause);
    }

    public DefaultServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
