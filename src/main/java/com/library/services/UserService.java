package com.library.services;

import java.util.Set;

import com.library.entities.User;

public interface UserService {
	
	public String registerUser(User user);
	
	public User findByEmail(String email);
	
	public User findByUsername(String fullName);
	
	public String userActivation(String code);
	
	public Set<User> findAll();
}
