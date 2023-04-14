package com.test.codingTest.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.codingTest.model.User;
import com.test.codingTest.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	// create a new user
	@Override
	public boolean createUser(User user) {
		if (userRepository.findByUsername(user.getUsername()) == null) {
			userRepository.save(user);
			return true;
		}
		return false;
	}

	// check for user credentials if matches create a token and store it in user
	// object
	@Override
	public boolean authenticate(String username, String password) {
		User storedUser = userRepository.findByUsername(username);
		if (storedUser != null && storedUser.getPassword().equals(password)) {
			String token = UUID.randomUUID().toString();
			storedUser.setToken(token);
			userRepository.save(storedUser);
			return true;
		}
		return false;
	}

	// check for token in user repository
	@Override
	public boolean validateToken(String token) {
		User user = userRepository.findByToken(token);
		return (user != null);
	}

}
