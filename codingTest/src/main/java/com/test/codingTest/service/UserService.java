package com.test.codingTest.service;

import org.springframework.stereotype.Service;

import com.test.codingTest.model.User;

@Service
public interface UserService {
	boolean authenticate(String username, String password);

	boolean createUser(User user);

	boolean validateToken(String token);
}
