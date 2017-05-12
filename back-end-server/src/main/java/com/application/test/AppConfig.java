package com.application.test;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.application.model.User;
import com.application.model.repository.UserRepository;

@Configuration
public class AppConfig {

	@Bean
	public InitializingBean initUser() {
		return new InitializingBean() {

			@Autowired
			private UserRepository userRepo;

			@Override
			public void afterPropertiesSet() throws Exception {
				if (this.userRepo.findAll().isEmpty()) {
					BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
					userRepo.save(new User("rzvs95@gmail.com", encoder.encode("password1")));
					userRepo.save(new User("ambro95@gmail.com", encoder.encode("password2")));
				}

			}
		};

	}
}
