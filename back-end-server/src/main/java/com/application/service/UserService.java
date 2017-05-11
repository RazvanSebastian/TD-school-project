package com.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.model.User;
import com.application.model.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public List<User> getAllUserRecords(){
		return userRepo.findAll();
	}
}
