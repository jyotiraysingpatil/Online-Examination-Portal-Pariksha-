package com.app.serviceImpl;

import com.app.entities.Admin;
import com.app.entities.LoginRequest;
import com.app.entities.User;
import com.app.repository.AdminRepository;
import com.app.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;
    public boolean authenticateUser(LoginRequest loginRequest, String role) {
        if ("admin".equalsIgnoreCase(role)) {
            Optional<Admin> admin = adminRepository.findByUsername(loginRequest.getUsername());
            return admin.isPresent() && loginRequest.getPassword().equals(admin.get().getPassword());
        } else {
            Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
            return user.isPresent() && loginRequest.getPassword().equals(user.get().getPassword());
        }
    }

}
