package com.application.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/**
 * 
 * @author Razvan
 * 
 *         <h3>About</h3>
 *         <p>
 *         Using the getAuthentication method from
 *         <b>TokenAuthenticationService</b> and handle the request If the
 *         request contain token add the authentication object into the security
 *         context and add to chain of filters the request and response;
 *         </p>
 *         <h3>About</h3>
 *         <p>
 *         This filter will filter every request and only the request with
 *         <b>Bearer</b> token will be add to security context
 *         </p>
 * 
 */
public class JWTAuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		Authentication authentication = TokenAuthenticationService.getAuthentication((HttpServletRequest) req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);

	}

}
