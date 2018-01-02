package com.sc.jysc.config;


import com.sc.jysc.authenticate.SCAuthenticationProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Jackbing on 2017/12/20.
 */
@Configuration
public class SecurityAutoConfig  {


    @Bean
    @ConditionalOnMissingBean({AuthenticationProvider.class})
    public SCAuthenticationProvider defaultAuthenticationProvider() {
        return new SCAuthenticationProvider();
    }

}
