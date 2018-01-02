package com.sc.jysc.config;

import java.io.Serializable;

/**
 * Controller 返回结果类
 * @param <T>
 */
public class Result<T> implements Serializable{
    private static final long serialVersionUID = 7808525685034448033L;

    private String result;
    private String message;
    private T data;

    public static <T> Result<T> newInstance() {
        return new Result<T>();
    }

    @Override
    public String toString() {
        return "Result{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
