package com.nirsb.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nirsb.account.model.User;
import com.nirsb.account.service.UserService;

@RestController
@RequestMapping()
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(path = "/users" , method= RequestMethod.POST)
	public ResponseEntity<Long> createUser(@RequestBody User user){
		com.nirsb.account.model.dto.User user1 =  userService.createUser(user);
		return new ResponseEntity<Long>(user1.getUserId(),HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "/users/{userId}" , method= RequestMethod.GET)
	public ResponseEntity<com.nirsb.account.model.dto.User> getUser(@PathVariable long userId){
		com.nirsb.account.model.dto.User user1 =  userService.getUser(userId);
		return new ResponseEntity<com.nirsb.account.model.dto.User>(user1,HttpStatus.OK);
		
	}
	
}
