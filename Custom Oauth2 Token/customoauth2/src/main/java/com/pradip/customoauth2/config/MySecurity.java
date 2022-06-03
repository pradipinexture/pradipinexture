package com.pradip.customoauth2.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled =  true)  // this annotation for active annotation base role access
public class MySecurity extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private CustomUserDetailService customUserDetailService; 

   @Resource(name = "uu")
    private UserDetailsService customUserDetailService;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
    	System.out.println("web sec authmanager method called");
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	System.out.println("web sec http method called");
		http
		.csrf().disable() //Cross-Site Request Forgery
			.authorizeRequests()
			.anyRequest().authenticated();
    }

    @Bean
    public TokenStore tokenStore() {
    	System.out.println("web sec tocken method called");
    	return new InMemoryTokenStore();
    }
    

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("web sec load method called");
		auth.userDetailsService(customUserDetailService).passwordEncoder(encoder());
	}


}
