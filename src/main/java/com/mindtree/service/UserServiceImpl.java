package com.mindtree.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.dao.UserDao;
import com.mindtree.entities.User;

@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger logger = 
            LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;
	
	@Override
	public User findByNameAndPassword(String name, String password) {
		logger.debug("fetching user by name - {} and password ", name);
		return userDao.findByNameAndPassword(name, password);
	}

}
