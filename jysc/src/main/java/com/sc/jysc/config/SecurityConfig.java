package com.sc.jysc.config;

import com.sc.jysc.authenticate.SCAuthenticationProvider;
import com.sc.jysc.sevice.CustomUserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.DependsOn;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.AuthorizedUrl;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

/**
 * Created by Jackbing on 2017/12/20.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static String[] defaultPermitUrls = new String[]{"/css/**", "/js/**", "/images/**", "/plugin/**", "/scripts/**", "/rest/**"};
    public static String[] defaultAuthenticatedUrls = new String[]{"/**"};
    @Autowired
    private SCAuthenticationProvider authProvider;
//    @Autowired
//    private AuthenticationEntryPoint authenticationEntryPoint;
    @Value("${sc.rememberme.days: 30}")
    private int remembermeDays;
    @Value("${sc.permit-all:}")
    private String[] permitUrls;
    @Value("${sc.authenticated:}")
    private String[] authenticatedUrls;
    @Value("${sc.loginUrl:/login}")
    private String loginUrl;
    @Value("${sc.logoutUrl:/logout}")
    private String logoutUrl;
    @Value("${sc.mainUrl:/index.html}")
    private String mainUrl;
    @Value("${sc.csrfdisable:true}")
    private boolean csrfDisable;
    @Autowired
    private CustomUserSevice customUserSevice;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if(this.permitUrls == null || this.permitUrls.length == 0) {
            this.permitUrls = defaultPermitUrls;
        }

        if(this.authenticatedUrls == null || this.authenticatedUrls.length == 0) {
            this.authenticatedUrls = defaultAuthenticatedUrls;
        }

        (
                (HttpSecurity)
                        (
                                (HttpSecurity)
                                        (
                                                (FormLoginConfigurer)
                                                        (
                                                                (FormLoginConfigurer)
                                                                        (
                                                                                (FormLoginConfigurer)
                                                                                        (
                                                                                                (HttpSecurity)
                                                                                                        (
                                                                                                                (AuthorizedUrl)
                                                                                                                        (
                                                                                                                                (AuthorizedUrl)
                                                                                                                                        (AuthorizedUrl)
                                                                                                                                                http.authorizeRequests()
                                                                                                                                                        .antMatchers(this.permitUrls)
                                                                                                                        ).permitAll()
                                                                                                                                .antMatchers(this.authenticatedUrls)
                                                                                                        ).authenticated()
                                                                                                                .and()
                                                                                        ).formLogin()
                                                                                                .loginPage(this.loginUrl)
                                                                                                .permitAll()
                                                                        ).defaultSuccessUrl(this.mainUrl)
                                                        ).authenticationDetailsSource(this.authenticationDetailsSource)
                                        ).and()
                        ).logout()
                                .logoutSuccessUrl(this.loginUrl)
                                .and()
        ).rememberMe()
                .tokenValiditySeconds(this.remembermeDays * 86400)
                .tokenRepository(this.tokenRepository());
        if(csrfDisable){
            http.csrf().disable();
        }
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(authProvider);
        auth.userDetailsService(this.customUserSevice);
    }

    @Bean
    @ConditionalOnMissingBean({JdbcTokenRepositoryImpl.class})
    @DependsOn("dataSource")
    public JdbcTokenRepositoryImpl tokenRepository() {
        JdbcTokenRepositoryImpl j = new JdbcTokenRepositoryImpl();
        j.setDataSource(this.dataSource);
        return j;
    }

}
