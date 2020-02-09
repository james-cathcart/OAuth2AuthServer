package com.madhax.oauthdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@SpringBootApplication
public class OauthdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthdemoApplication.class, args);
    }
}
