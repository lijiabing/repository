package com.sc.jysc.config;

import com.sc.jysc.session.DataManageImpl;
import com.sc.jysc.session.DataSet;
import com.sc.jysc.session.DataSourceMeta;
import com.sc.jysc.session.LazySessionCache;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

import javax.persistence.EntityManagerFactory;

@Configuration
@ComponentScan(basePackages = {
        "com.sc.jysc.config"
})
public class JyscAutoConfig {



    @Value("${sc.datasource.name:local}")
    private String dataSourceName;
    @Autowired
    private DataSourceMeta dataSourceMeta;
    public JyscAutoConfig() {
    }

    @Bean
    public LazySessionCache sessionCache(){
        return new LazySessionCache(dataSourceName,dataSourceMeta);
    }

    @Bean
    public DataSet dataManage(){
        return new DataManageImpl(this.sessionCache());
    }



}
