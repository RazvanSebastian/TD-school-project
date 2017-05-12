package com.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.application.service.UserDetailsService;

/**
 * 
 * @author Parautiu
 * @Doc : used to secure all routes and add filter on requests
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers(HttpMethod.POST, "/login").permitAll()
		//all other routes require authentication
		.anyRequest().authenticated()
		.and()
		//Filter api/login requests
		.addFilterBefore(new JWTLoginFilter("/login", authenticationManager(),userDetailsService),UsernamePasswordAuthenticationFilter.class)
		//Filter other requests to check if the JWT is in header
		.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser("admin")
//		.password("password")
//		.roles("ADMIN");
		
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
	
	
	
}
