package com.mindtree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import com.mindtree.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByNameAndPassword(@Param("name") String name, @Param("password") String password);
}
