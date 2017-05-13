package com.application.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.application.model.User;

/**
 * 
 * @author Parautiu
 *
 */

public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("SELECT user FROM User user WHERE user.id=:id")
	User findById(@Param("id") Long id);
	
	User findByUserEmail(String userEmail);
	
}
