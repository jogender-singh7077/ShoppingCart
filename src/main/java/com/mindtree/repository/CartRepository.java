package com.mindtree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.mindtree.entities.Cart;
import com.mindtree.entities.User;

public interface CartRepository extends JpaRepository<Cart, Integer>{

	Cart findByUser(@Param("user") User user);
}
