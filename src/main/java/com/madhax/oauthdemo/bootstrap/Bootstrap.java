package com.madhax.oauthdemo.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Bootstrap.class);

    public Bootstrap() {
        log.debug("I am Bootstrap");
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
