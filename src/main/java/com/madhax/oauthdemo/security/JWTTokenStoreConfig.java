package com.madhax.oauthdemo.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
public class JWTTokenStoreConfig {

    private final String ARBITRARY_SIGNING_KEY = "345345fsdfsf5345";

    @Value("${token.access.timeout}")
    private Integer accessTokenTimeout;

    @Value("${token.refresh.timeout}")
    private Integer refreshTokenTimeout;

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    @Qualifier("customTokenServices")
    public DefaultTokenServices tokenServices() {

        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setReuseRefreshToken(true);
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setAccessTokenValiditySeconds(accessTokenTimeout);
        defaultTokenServices.setRefreshTokenValiditySeconds(refreshTokenTimeout);
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setTokenEnhancer(jwtTokenEnhancerChain());

        return defaultTokenServices;
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {

        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(ARBITRARY_SIGNING_KEY);

        return jwtAccessTokenConverter;
    }

    @Bean
    public JWTTokenEnhancer jwtTokenEnhancer() {
        return new JWTTokenEnhancer();
    }

    @Bean
    public TokenEnhancerChain jwtTokenEnhancerChain() {

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();

        tokenEnhancerChain.setTokenEnhancers(
                Arrays.asList(
                        jwtTokenEnhancer(),
                        jwtAccessTokenConverter()
                )
        );

        return tokenEnhancerChain;
    }

}
