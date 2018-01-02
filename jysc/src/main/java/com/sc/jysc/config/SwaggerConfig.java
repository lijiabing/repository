package com.sc.jysc.config;

import io.swagger.annotations.Api;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
@ConditionalOnExpression("${swagger.enabled: true}")
public class SwaggerConfig {
    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(getApiInfo())
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .select() // 选择那些路径和api会生成document
//                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class)) // 对所有api进行监控
               .apis(RequestHandlerSelectors.basePackage("com.sc.jysc"))//对"com.cgs.dcp"包下全部进行扫描
                .paths(PathSelectors.regex("/.*")) // 对所有路径进行监控
//                .paths(PathSelectors.regex("/demo.*"))
                .build();
        return docket;
    }

    @Deprecated
    private ApiInfo getApiInfo(){

        ApiInfo apiInfo = new ApiInfo("Swagger  APi", // 大标题
                initContextInfo(), // 简单的描述
                "1.0.0", // 版本
                "服务条款", "后台开发团队", // 作者
                "The Apache License, Version 2.0", // 链接显示文字
                "http://www.baidu.com"// 网站链接
        );
        return apiInfo;
    }

    private String initContextInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("以下是本项目的API文档");
        return sb.toString();
    }

}
