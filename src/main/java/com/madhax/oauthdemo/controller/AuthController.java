package com.madhax.oauthdemo.controller;

import com.madhax.oauthdemo.constants.AuthConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    // TODO make test class

    private final Logger log = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping(value = {AuthConstants.USER_ENDPOINT}, produces = "application/json")
    public Map<String, Object> user(OAuth2Authentication user) {

        log.debug("/user endpoint called.");
        Map<String, Object> userInfo = new HashMap<>();

        userInfo.put(
                "user",
                user.getUserAuthentication().getPrincipal()
        );

        userInfo.put(
                "authorities",
                AuthorityUtils.authorityListToSet(
                        user.getUserAuthentication().getAuthorities()
                )
        );

        return userInfo;
    }
}
