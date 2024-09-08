package com.app.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ResourceNotFoundException.ResourceNotFoundException;
import com.app.entities.User;
import com.app.repository.UserRepository;
import com.app.services.UserService;
@Service
@Transactional
public class UserImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public User addNew(User user) {
		
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(Long userId) {
	userRepository.deleteById(userId);
	}

	@Override
	public User getByUserId(Long userId) {
	    return userRepository.findById(userId)
	                         .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
	}
}
