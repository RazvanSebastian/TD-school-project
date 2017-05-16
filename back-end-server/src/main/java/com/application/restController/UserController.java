package com.application.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.User;
import com.application.service.UserDetailsService;

/**
 * 
 * @author Parautiu
 *
 */

@RestController
public class UserController {
	
	@Autowired
	private UserDetailsService userService;
	
	@RequestMapping(value="/api/register",method=RequestMethod.POST)
	public ResponseEntity<String> onSendRegister(@RequestBody User newUser){
		try {
			this.userService.userRegister(newUser.getUserEmail(), newUser.getPassword());
			return new ResponseEntity<>("OK",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
}
