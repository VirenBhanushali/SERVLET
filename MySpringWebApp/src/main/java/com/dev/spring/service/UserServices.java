package com.dev.spring.service;

import com.dev.spring.beans.User;

public interface UserServices {
	public Boolean createUser(User user);
	 public User searchUser(Integer userId);
	 public Boolean updateUser(Integer userId,String password);
	 public User deleteUser(Integer userId);
	 public User login(Integer userId,String password);

}
