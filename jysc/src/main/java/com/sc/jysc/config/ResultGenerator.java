package com.sc.jysc.config;

/**
 * Controller 返回结果生成类
 */
public class ResultGenerator {

    public static <T> Result<T> genResult(String result, String message, T data){
        Result<T> r = Result.newInstance();
        r.setResult(result);
        r.setMessage(message);
        r.setData(data);
        return r;
    }
    public static <T> Result<T> genSuccess(String message, T data){
        return genResult("success", message, data);
    }
    public static <T> Result<T> genSuccess(T data){
        return genSuccess(null, data);
    }
    public static <T> Result<T> genSuccess(){
        return genSuccess(null);
    }
    public static <T> Result<T> genFail(String message, T data){
        return genResult("fail", message, data);
    }
    public static <T> Result<T> genFail(String message){
        return genFail(message, null);
    }
}
