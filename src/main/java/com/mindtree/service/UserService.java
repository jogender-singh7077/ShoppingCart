package com.mindtree.service;

import com.mindtree.entities.User;

public interface UserService {

	User findByNameAndPassword(final String name, final String password);
}
