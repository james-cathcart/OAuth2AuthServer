package com.madhax.oauthdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    public static final String USER_USERNAME = "john.carnell";
    public static final String USER_PASSWORD = "password1";
    public static final String ADMIN_USERNAME = "william.woodward";
    public static final String ADMIN_PASSWORD = "password2";
    public static final String USER_ROLE = "USER";
    public static final String ADMIN_ROLE = "ADMIN";
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public WebSecurityConfigurer(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    @Bean
    @Qualifier("myUserDetailsService")
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser(USER_USERNAME)
                .password(
                        passwordEncoder.encode(USER_PASSWORD)
                )
                .roles(USER_ROLE)
                .and()
                .withUser(ADMIN_USERNAME)
                .password(
                        passwordEncoder.encode(ADMIN_PASSWORD)
                )
                .roles(USER_ROLE, ADMIN_ROLE);
    }
}
