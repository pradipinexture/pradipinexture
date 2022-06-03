package com.pradip.customjwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pradip.customjwt.service.CustomUserDetailService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled =  true)  // this annotation for active annotation base role access
public class MySecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailService userService;//=new CustomUserDetailService();
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable() //Cross-Site Request Forgery
//		.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and() // access push, put by only tocken
			.authorizeRequests().antMatchers("/authenticate").permitAll()
//			.antMatchers("/public/login","/public/","/public/register").permitAll()
//			.antMatchers("/*.js").permitAll()
			
//			.antMatchers("/user/**").hasRole("ADMIN")
//			.antMatchers("/user/**","/public/**").hasRole("MANAGER")
//			.antMatchers("/public/**").hasRole("NORMAL")
//			.antMatchers("/resources/**").permitAll()
			.antMatchers("/loginpage").permitAll()
			.antMatchers("/**").hasAnyRole("ADMIN","MANAGER","NORMAL")
			.anyRequest().authenticated()
			.and()
//			.httpBasic()
//			.and()	
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // it apply basic authentication
//			.formLogin()
//			.loginPage("/loginpage")
//			.loginProcessingUrl("/loginprocess")
//			.defaultSuccessUrl("/user/users/");
//			.permitAll();
		
		// Add a filter to validate the tokens with every request
				http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
		
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
//		auth.inMemoryAuthent ication().withUser("pradip").password("123").roles("ADMIN");
//		auth.inMemoryAuthentication().withUser("sandip").password("123").roles("MANAGER");
		
//		auth.inMemoryAuthentication().withUser("pradip").password(this.passwordEncoder().encode("pradip123")).roles("ADMIN");
//		auth.inMemoryAuthentication().withUser("sandip").password(this.passwordEncoder().encode("sandip123")).roles("MANAGER");	
//		auth.inMemoryAuthentication().withUser("bhavin").password(this.passwordEncoder().encode("bhavin123")).roles("NORMAL");	
		
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
		
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder(10);
////		return NoOpPasswordEncoder.getInstance();
//	}
	
}
