package com.madhax.oauthdemo.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
@EnableResourceServer
public class JWTOAuth2Config extends AuthorizationServerConfigurerAdapter {

    public static final String APP_USERNAME = "defaultApp";
    public static final String APP_PASSWORD = "defaultAppPassword";

    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private TokenStore tokenStore;
    private DefaultTokenServices tokenServices; // TODO: not used?
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    private JWTTokenEnhancer jwtTokenEnhancer;

    public JWTOAuth2Config(
            PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
            @Qualifier("myUserDetailsService") UserDetailsService userDetailsService,
            TokenStore tokenStore,
            DefaultTokenServices tokenServices,
            JwtAccessTokenConverter jwtAccessTokenConverter,
            JWTTokenEnhancer jwtTokenEnhancer
    ) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.tokenStore = tokenStore;
        this.tokenServices = tokenServices;
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
        this.jwtTokenEnhancer = jwtTokenEnhancer;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();

        tokenEnhancerChain
                .setTokenEnhancers(
                        Arrays.asList(
                                jwtTokenEnhancer,
                                jwtAccessTokenConverter
                        )
                );

        endpoints
                .tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient(APP_USERNAME)
                .secret(passwordEncoder.encode(APP_PASSWORD))
                .authorizedGrantTypes("refresh_token", "password", "client_credentials")
                .scopes("webclient", "mobileclient");
    }
}
