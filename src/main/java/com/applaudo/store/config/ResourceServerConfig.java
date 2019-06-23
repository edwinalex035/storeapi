package com.applaudo.store.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("api");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/actuator**").permitAll()
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/api/products**").permitAll()
                //.antMatchers("/api/**").authenticated()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
        ;
    }

}
