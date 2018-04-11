package com.herokuapp.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.http.HttpMethod.OPTIONS;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> response.sendRedirect("/denied"))
                .and().authorizeRequests()
                .antMatchers("/", "/denied").permitAll()
                .anyRequest().permitAll()
                //TODO change anyRequest to authentificated afte the tests are done
                .antMatchers(OPTIONS).permitAll()
                .and().csrf().disable()


        ;
    }


}

