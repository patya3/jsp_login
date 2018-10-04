package com.library.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.library.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByEmail(String email);
	
	User findByActivation(String code);
	
	User findByUsername(String username);
	
	Set<User> findAll();
	
	
}
