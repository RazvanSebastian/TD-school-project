package com.application.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.application.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import static java.util.Collections.emptyList;

/**
 * 
 * @author Parautiu
 * @Doc : Intercept POST request on /login URL and check if the user can
 *      authenticate mapping the InputStream from request
 */

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	public JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
		User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(user.getUserEmail(), user.getPassword(), emptyList()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		TokenAuthenticationService.addAuthentication(response, authResult.getName());
	}
	
	

}
