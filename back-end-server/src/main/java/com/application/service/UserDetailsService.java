package com.application.service;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.application.model.User;
import com.application.model.repository.UserRepository;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		final User user = this.userRepository.findByUserEmail(userEmail);
		if (!EmailValidator.getInstance().isValid(userEmail))
			throw new UsernameNotFoundException("Email patern is not valid!");
		if (user == null)
			throw new UsernameNotFoundException("User not found!");
		return user;
	}

	public void userRegister(String userEmail, String password) throws Exception {
		if (!EmailValidator.getInstance().isValid(userEmail))
			throw new Exception("Email patern is not valid!");
		if(password.length()<6)
			throw new Exception("Password must contain at least 6 characters!");
		final User user = this.userRepository.findByUserEmail(userEmail);
		if (user != null)
			throw new Exception("This email is used!");
		this.userRepository.save(new User(userEmail, password));
	}
}
