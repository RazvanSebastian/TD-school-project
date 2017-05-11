package com.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 
 * @author Parautiu
 * 
 * @NOTE: Must extends SpringBootServletInitializer and override configure method for deploying 
 */

@SpringBootApplication
@ComponentScan(basePackages={"com.application.restController","com.application.service"})
@EntityScan(basePackages={"com.application.model"})
@EnableJpaRepositories(basePackages={"com.application.model.repository"})
public class BackEndServerApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(BackEndServerApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return super.configure(builder);
	}
	
}
