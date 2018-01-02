package com.sc.jysc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Jackbing on 2017/12/20.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.sc.jysc"})
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
