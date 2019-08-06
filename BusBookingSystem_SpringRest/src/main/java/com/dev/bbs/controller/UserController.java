package com.dev.bbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.bss.beans.User;
import com.dev.bss.sevice.ServiceUser;

@RestController
public class UserController {
	
	@Autowired
	ServiceUser services;
	
	@RequestMapping(value = "create",method = RequestMethod.POST)
	public User createUser(@RequestBody User user)
	{
		 return user = services.createUser(user);
	}
	
	@RequestMapping(value = "get/{id}",method = RequestMethod.GET)
	public User getUser(@PathVariable("id") int userId)
	{
		User user = services.searchUser(userId);
		return user;
	}
	@RequestMapping(value = "update/{password}/{newpassword}",method = RequestMethod.POST)
	public User updateUser(@RequestBody User user,@PathVariable("password") String password,@PathVariable("newpassword") String newPassword)
	{
		Boolean state = services.updateUser(user, password, newPassword);
		if(state)
		{
		 user = services.searchUser(user.getUserId());
		 return user;
		}
		return null;
		
	}
	@RequestMapping(value = "/user/delete/{userId}/{password}", method = RequestMethod.DELETE)
	public User deleteUser(@PathVariable("userId") int userId,@PathVariable("password") String password) {
		User user = services.searchUser(userId);
		Boolean state = services.deleteUser(userId, password);
		return user;
	}

}
