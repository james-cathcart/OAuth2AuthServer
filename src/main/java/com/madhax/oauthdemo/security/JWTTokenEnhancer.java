package com.madhax.oauthdemo.security;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JWTTokenEnhancer implements TokenEnhancer {

    // This class is used to "enhance" your JWT token by adding claims essentially.
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        // TODO: figure out why this map is not being added to the token
        Map<String, Object> additionalInfo = new HashMap<>();

        Long currentTimeSeconds = System.currentTimeMillis() / 1000;
        Long fiveHoursSeconds = 18_000L;
        additionalInfo.put("refresh_drop_dead", currentTimeSeconds + fiveHoursSeconds);

        DefaultOAuth2AccessToken defaultOAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;

        defaultOAuth2AccessToken.setAdditionalInformation(additionalInfo);

        return defaultOAuth2AccessToken;
    }
}
