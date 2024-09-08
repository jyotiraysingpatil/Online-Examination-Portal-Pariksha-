package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.LoginRequest;
import com.app.serviceImpl.AuthService;
@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {
	  @Autowired
	    private AuthService authService;

	    @PostMapping("/login")
	    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
	        boolean isAuthenticated = authService.authenticateUser(loginRequest, loginRequest.getRole());
	        if (isAuthenticated) {
	            return ResponseEntity.ok("Login successful!");
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
	        }
	    }
}
