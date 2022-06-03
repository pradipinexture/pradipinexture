package com.pradip.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConf extends ResourceServerConfigurerAdapter{

	private static final String RESOURCE_ID = "myrestservice";
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		System.out.println("res ser secConfi method called");
		resources.resourceId(RESOURCE_ID);
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		System.out.println("res ser http method called");
		http.authorizeRequests().anyRequest().authenticated();
	}
}
