package com.mindtree.dao;

import com.mindtree.entities.User;

public interface UserDao {

	User findByNameAndPassword(final String name, final String password);
}
