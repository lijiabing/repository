package com.sc.jysc.session;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class DataSourceMeta {

    @Value("${spring.datasource.url:jdbc:mysql://localhost:3306/jysc?useSSL=true&characterEncoding=utf-8}")
    private String address;
    @Value("${spring.datasource.username:root}")
    private String user;
    @Value("${spring.datasource.password:root}")
    private String password;
    @Value("${hibernate.c3p0.max_size:30}")
    private Integer poolSizeMax;
    @Value("${hibernate.c3p0.min_size:10}")
    private Integer poolSizeMin;
    @Value("${hibernate.c3p0.timeout:20000}")
    private Integer timeout;
    @Value("${hibernate.c3p0.max_statements:64}")
    private Integer maxStatements;
    @Value("${hibernate.c3p0.timeout:300000}")
    private Integer readTimeOut;
    @Value("${hibernate.connection.driver_class:com.mysql.jdbc.Driver}")
    private String driverClass;
    @Value("${spring.jpa.database-platform:org.hibernate.dialect.MySQL5Dialect}")
    private String strDialect;
    @Value("${hibernate.show_sql:false}")
    private Boolean sqlDebugEnabled;

    public Boolean getSqlDebugEnabled() {
        return sqlDebugEnabled;
    }

    public void setSqlDebugEnabled(Boolean sqlDebugEnabled) {
        this.sqlDebugEnabled = sqlDebugEnabled;
    }

    public String getStrDialect() {
        return strDialect;
    }

    public void setStrDialect(String strDialect) {
        this.strDialect = strDialect;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPoolSizeMax() {
        return poolSizeMax;
    }

    public void setPoolSizeMax(Integer poolSizeMax) {
        this.poolSizeMax = poolSizeMax;
    }

    public Integer getPoolSizeMin() {
        return poolSizeMin;
    }

    public void setPoolSizeMin(Integer poolSizeMin) {
        this.poolSizeMin = poolSizeMin;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getMaxStatements() {
        return maxStatements;
    }

    public void setMaxStatements(Integer maxStatements) {
        this.maxStatements = maxStatements;
    }

    public Integer getReadTimeOut() {
        return readTimeOut;
    }

    public void setReadTimeOut(Integer readTimeOut) {
        this.readTimeOut = readTimeOut;
    }
}
