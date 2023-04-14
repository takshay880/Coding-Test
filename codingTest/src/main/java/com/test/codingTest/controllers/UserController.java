package com.test.codingTest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.codingTest.model.User;
import com.test.codingTest.service.UserService;

@RestController
@RequestMapping("")
public class UserController {

	@Autowired
	private UserService userService;

	// api to check for user credentials
	@PostMapping("/authenticate")
	public ResponseEntity<Void> authenticateUser(@RequestBody User user) {
		boolean authenticated = userService.authenticate(user.getUsername(), user.getPassword());
		if (authenticated) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	// api to create new user
	@PostMapping("/signup")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		if (userService.createUser(user)) {

			return ResponseEntity.status(HttpStatus.CREATED).body(user);
		} else
			return ResponseEntity.status(HttpStatus.CONFLICT).body(user);

	}
}
