package com.sc.jysc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Jackbing on 2017/12/20.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.sc.jysc"})
@EnableTransactionManagement
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
//    /**
//     * 注入sessionfatory
//     * @return
//     */
//    @Bean
//    public HibernateJpaSessionFactoryBean sessionFactory() {
//        return new HibernateJpaSessionFactoryBean();
//    }
}
