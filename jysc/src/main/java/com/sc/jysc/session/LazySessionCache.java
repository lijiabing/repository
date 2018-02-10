package com.sc.jysc.session;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.orm.hibernate5.SpringSessionContext;

import java.io.ByteArrayInputStream;
import java.util.HashMap;

public class LazySessionCache {
    private static Logger logger = Logger.getLogger(LazySessionCache.class);
    private HashMap<String, SessionFactory> sessionFactoryCache = new HashMap();
    private String dataSourceName;
    private DataSourceMeta meta;

    public LazySessionCache(String dataSourceName,DataSourceMeta meta) {
        this.dataSourceName = dataSourceName;
        this.meta=meta;
    }


    private synchronized SessionFactory loadSessionFactory(String dataSourceName,DataManage dm,DataSourceMeta meta) {

//        ServiceRegistry standardRegistry = (new StandardServiceRegistryBuilder()).applySetting("hibernate.connection.url", meta.getAddress()).applySetting("hibernate.connection.username", meta.getUser()).applySetting("hibernate.connection.password", meta.getPassword()).applySetting("hibernate.connection.driver_class", meta.getDriverClass()).applySetting("hibernate.dialect", meta.getStrDialect()).applySetting("hibernate.c3p0.max_size", meta.getPoolSizeMax()).applySetting("hibernate.c3p0.min_size", meta.getPoolSizeMin()).applySetting("hibernate.c3p0.timeout", meta.getTimeout()).applySetting("hibernate.c3p0.max_statements", meta.getMaxStatements()).applySetting("hibernate.show_sql", meta.getSqlDebugEnabled()).applySetting("hibernate.format_sql", meta.getSqlDebugEnabled()).applySetting("hibernate.default_entity_mode",  "pojo").applySetting("hibernate.current_session_context_class", SpringSessionContext.class.getName()).build();
//        MetadataSources sources = new MetadataSources(standardRegistry);
        //Metadata metadata = sources.addInputStream(new ByteArrayInputStream(mapXml.getBytes())).getMetadataBuilder().build();
        //Metadata metadata=sources.addPackage("com.sc.jysc.entity").getMetadataBuilder().build();
        //Metadata metadata =sources.getMetadataBuilder().build();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();

        Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();



        //SessionFactory sessionFactory= new Configuration().configure().buildSessionFactory();


        this.sessionFactoryCache.put(dataSourceName, sessionFactory);
        return sessionFactory;
    }

    public void reloadSession(DataManage dm){
        SessionFactory sf=null;
        sf=sessionFactoryCache.get(dataSourceName);
        if(sf==null){
            sf=this.loadSessionFactory(dataSourceName,dm,meta);
        }
        if(sf!=null){
            dm.setSessionFactory(sf);
        }else{
            throw new RuntimeException("Unable to load dataset: " + dataSourceName);
        }

    }

}
