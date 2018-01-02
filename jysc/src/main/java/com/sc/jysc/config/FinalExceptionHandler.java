package com.sc.jysc.config;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Jackbing on 2017/12/20.
 * 页面请求异常处理
 */
@RestController
public class FinalExceptionHandler implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = "/error")
    public <T> Result<T> error(HttpServletResponse response, HttpServletRequest request){
        return ResultGenerator.genFail("请求地址不存在");
    }
}
