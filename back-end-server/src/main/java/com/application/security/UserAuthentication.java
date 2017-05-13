package com.application.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.application.model.User;

/**
 * 
 * @author Parautiu
 *
 */

public class UserAuthentication implements Authentication{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final User user;
	
	
	public UserAuthentication(User user) {
		super();
		this.user = user;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return user.getAuthorities();
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return user;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setAuthenticated(boolean arg0) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

}
