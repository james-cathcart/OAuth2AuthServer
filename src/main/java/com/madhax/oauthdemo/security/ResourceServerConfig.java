package com.madhax.oauthdemo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/read-check")
                    .hasAuthority("READ")
                .antMatchers("/write-check")
                    .hasAuthority("WRITE")
                .antMatchers("/delete-check")
                    .hasAuthority("DELETE")
                .antMatchers("/user")
                    .permitAll()
                .antMatchers("/**/*")
                    .denyAll();
    }
}
