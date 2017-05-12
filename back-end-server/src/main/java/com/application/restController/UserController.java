package com.application.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.User;
import com.application.model.repository.UserRepository;
import com.application.service.UserService;

/**
 * 
 * @author Parautiu
 *
 */

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/")
	public String getMessage(){
		return "String~";
	}
	
	@RequestMapping(value="/get-all-users",method=RequestMethod.GET)
	public List<User> onGetUserList(){
		return userService.getAllUserRecords();
	}
}
