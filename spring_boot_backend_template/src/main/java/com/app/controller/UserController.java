package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.entities.User;
import com.app.repository.UserRepository;
import com.app.services.UserService;
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("insert")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        User u = userService.addNew(user);
        return ResponseEntity.ok("User added: " + u);
    }

    @PutMapping("update")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        User u = userService.updateUser(user);
        return ResponseEntity.ok("User updated: " + u);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUserDetails(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
    
    @GetMapping("userGetById/{userId}")
    public User getById(@PathVariable Long userId) {
return userService.getByUserId(userId);    
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            User newUser = userService.addNew(user);
            return ResponseEntity.ok("User registered successfully: " + newUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed: " + e.getMessage());
        }
    }
    
}
