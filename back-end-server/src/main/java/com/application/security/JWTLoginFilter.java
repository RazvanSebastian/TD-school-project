package com.application.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.application.model.User;
import com.application.service.UserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static java.util.Collections.emptyList;

/**
 * 
 * @author Razvan
 * 
 * Check the doc on method
 *
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	private final UserDetailsService userService;

	public JWTLoginFilter(String url, AuthenticationManager authManager, UserDetailsService userDetailsService) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
		this.userService = userDetailsService;
	}

	/**
	 * Performs actual authentication.
	 * <p>
	 * The implementation should do one of the following:
	 * <ol>
	 * <li>Return a populated authentication token for the authenticated user, indicating
	 * successful authentication</li>
	 * <li>Return null, indicating that the authentication process is still in progress.
	 * Before returning, the implementation should perform any additional work required to
	 * complete the process.</li>
	 * <li>Throw an <tt>AuthenticationException</tt> if the authentication process fails</li>
	 * </ol>
	 *
	 * @param request from which to extract parameters and perform the authentication
	 * @param response the response, which may be needed if the implementation has to do a
	 * redirect as part of a multi-stage authentication process (such as OpenID).
	 *
	 * @return the authenticated user token, or null if authentication is incomplete.
	 *
	 * @throws AuthenticationException if authentication fails.
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
		User user = new ObjectMapper().readValue(req.getInputStream(), User.class);

		return getAuthenticationManager()
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUserEmail(), user.getPassword()));
	}

	/**
	 * Default behaviour for successful authentication.
	 * <ol>
	 * <li>Sets the successful <tt>Authentication</tt> object on the
	 * {@link SecurityContextHolder}</li>
	 * <li>Informs the configured <tt>RememberMeServices</tt> of the successful login</li>
	 * <li>Fires an {@link InteractiveAuthenticationSuccessEvent} via the configured
	 * <tt>ApplicationEventPublisher</tt></li>
	 * <li>Delegates additional behaviour to the {@link AuthenticationSuccessHandler}.</li>
	 * </ol>
	 *
	 * Subclasses can override this method to continue the {@link FilterChain} after
	 * successful authentication.
	 * @param request
	 * @param response
	 * @param chain
	 * @param authResult the object returned from the <tt>attemptAuthentication</tt>
	 * method.
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub

		final User user = (User) this.userService.loadUserByUsername(authResult.getName());
		final UserAuthentication userAuth = new UserAuthentication(user);

		TokenAuthenticationService.addAuthentication(response, userAuth);
		SecurityContextHolder.getContext().setAuthentication(userAuth);
	}

}
