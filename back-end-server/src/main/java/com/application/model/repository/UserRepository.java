package com.application.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.model.User;

/**
 * 
 * @author Parautiu
 *
 */

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUserEmail(String userEmail);
	
}
