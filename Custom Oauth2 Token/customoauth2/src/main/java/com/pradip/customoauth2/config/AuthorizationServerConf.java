package com.pradip.customoauth2.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import ch.qos.logback.core.rolling.helper.TokenConverter;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConf extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
		
		System.out.println("Auth servers client method called");
		configurer
				.inMemory()
				.withClient("pradip")
				.secret(new BCryptPasswordEncoder().encode("123"))
				.authorizedGrantTypes("password","authorization_code" , "refresh_token", "implicit" )
				.scopes("read", "write", "trust")
				.accessTokenValiditySeconds(1*60*60)
				.refreshTokenValiditySeconds(6*60*60);
	}

	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		System.out.println("Auth servers endpoint method called");
		
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(List.of(new CustomTokenEnhancer()));
        
        endpoints.authenticationManager(authenticationManager)
                .tokenEnhancer(tokenEnhancerChain)
                .tokenStore(tokenStore);

	}
}
