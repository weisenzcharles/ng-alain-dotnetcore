package com.weisenz.weiadmin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = "com.weisenz.weiadmin.security")
public class NgAlainSecurityConfig extends JsonWebTokenSecurityConfig {
    @Override
    protected void setupAuthorization(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                // allow anonymous access to /authenticate endpoint
                .antMatchers("/#/passport/login").permitAll()
                .antMatchers("/api/account/login").permitAll()
                .antMatchers("/api/common/appdata").permitAll()
//                .antMatchers("/api/user").permitAll()
//                .antMatchers("/api/user/listMonthUserLoginReport").permitAll()
                .antMatchers("/#").permitAll()
                // allow anonymous to common static resources
                .antMatchers(HttpMethod.GET, "/", "/assets/*.*", "/assets/**/*.*", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js")
                .permitAll()

                // authenticate all other requests
                .anyRequest().authenticated();
    }
}
