package com.mindtree.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindtree.entities.User;
import com.mindtree.repository.UserRepository;

@Component
public class UserDaoImpl implements UserDao{
	
	private static final Logger logger = 
            LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findByNameAndPassword(final String name, final String password) {
		logger.debug("fetching user by name - {} and password ", name);
		return userRepository.findByNameAndPassword(name, password);
	}

}
