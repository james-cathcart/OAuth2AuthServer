package com.madhax.oauthdemo.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Bootstrap.class);

    @Value("${token.access.timeout}")
    private Integer accessTokenTimeout;

    @Value("${token.refresh.timeout}")
    private Integer refreshTokenTimeout;

    public Bootstrap() {
        log.debug("I am Bootstrap");
    }

    private void listProperties() {
        System.out.println("Access token timeout: " + accessTokenTimeout);
        System.out.println("Refresh token timeout: " + refreshTokenTimeout);
    }

    @Override
    public void run(String... args) throws Exception {
        log.debug("Bootstrap is running!");
        listProperties();
    }
}
