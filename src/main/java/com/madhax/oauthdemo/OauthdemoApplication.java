package com.madhax.oauthdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class OauthdemoApplication {

    private final static Logger log = LoggerFactory.getLogger(OauthdemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(OauthdemoApplication.class, args);
    }
}
