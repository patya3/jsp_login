package com.library.services;

import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.library.entities.Role;
import com.library.entities.User;
import com.library.repository.RoleRepository;
import com.library.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	PasswordEncoder encoder =
		     PasswordEncoderFactories.createDelegatingPasswordEncoder();

	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private EmailService emailService;
	
	private final String USER_ROLE = "USER";
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, EmailService emailService) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.emailService = emailService;
	}
	
	@Override
	public String registerUser(User userToRegister) {
		User userCheck = userRepository.findByUsername(userToRegister.getUsername());
		
		if(userCheck != null)
			return "alreadyExists";
		
		userToRegister.setRoleId(roleRepository.findByRole("user"));
				
		//userToRegister.setEnabled(false);
		userToRegister.setEnabled(true);
		//userToRegister.setActivation(generateKey());
		userToRegister.setPassword(encoder.encode(userToRegister.getPassword()));
		userRepository.save(userToRegister);
		//emailService.sendAuthMessage(userToRegister);
		
		return "ok";
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public String generateKey() {
		String key = "";
		Random random = new Random();
		char[] word = new char[16];
		for (int i = 0; i < word.length; i++) {
			word[i] = (char) ('a' + random.nextInt(26));
		}
		String toReturn = new String(word);
		return toReturn;
	}

	@Override
	public String userActivation(String code) {
		User user = userRepository.findByActivation(code);
		if (user == null)
			return "noresult";
		
		user.setEnabled(true);
		user.setActivation("");
		userRepository.save(user);
		return "ok";
	}

	@Override
	public Set<User> findAll() {
		Set<User> users = userRepository.findAll();
		return users;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException(username);
		
		return new UserDetailsImpl(user);
	}

}
