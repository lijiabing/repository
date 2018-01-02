package com.sc.jysc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationNotSupportedException;

/**
 * Created by Jackbing on 2017/12/20.
 */
@RestControllerAdvice
public class GlobalExceptionHandler{
    private Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public <T> Result<T> handleRuntimeException(RuntimeException e){
        logger.error(e.getMessage(), e);
        return ResultGenerator.genFail("系统异常");
    }


    @ExceptionHandler(DefaultServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public <T> Result<T> handleMyServiceException(DefaultServiceException e){
        logger.error(e.getMessage(), e);
        return ResultGenerator.genFail(e.getMessage());
    }
}
