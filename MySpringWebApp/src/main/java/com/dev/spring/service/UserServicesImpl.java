package com.dev.spring.service;

import org.springframework.stereotype.Service;

import com.dev.spring.beans.User;
import com.dev.spring.dao.JpaImpl;
import com.dev.spring.dao.UserDAO;
@Service
public class UserServicesImpl implements UserServices {
      	UserDAO db=new JpaImpl();
	@Override
	public Boolean createUser(User user) {
    return db.createUser(user);
	}

	@Override
	public User searchUser(Integer userId) {
		return db.searchUser(userId);
	}

	@Override
	public Boolean updateUser(Integer userId, String password) {
		return db.updateUser(userId, password);
	}

	@Override
	public User deleteUser(Integer userId) {
		return db.deleteUser(userId);
	}

	@Override
	public User login(Integer userId, String password) {
		return db.login(userId, password);
	}

	

}
