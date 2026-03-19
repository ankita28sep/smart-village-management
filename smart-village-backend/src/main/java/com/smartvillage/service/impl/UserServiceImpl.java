package com.smartvillage.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smartvillage.entity.User;
import com.smartvillage.enums.UserRole;
import com.smartvillage.exceptions.InvalidDataException;
import com.smartvillage.exceptions.ResourceNotFoundException;
import com.smartvillage.repository.UserRepository;
import com.smartvillage.service.UserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User assignRole(Long userId, UserRole role) {
		User user= userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		user.setRole(role);
		return userRepository.save(user);
	}

//UPDATE USER
	@Override
	public User updateUser(long id, User user) {

		if (user.getPassword() != null) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}

		user.setUpdatedAt(LocalDateTime.now());

		return userRepository.save(user);

	}

//UPDATE PROFILE
	@Override
	public User updateProfile(User user) {

		if (user.getPassword() != null) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}

		user.setUpdatedAt(LocalDateTime.now());

		return userRepository.save(user);
	}

// DELETE USER
	@Override
	public void deleteUser(long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		userRepository.delete(user);

	}

// GET USER BY ID
	@Override
	public User getUserById(long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

		// CHECK STATUS
		if (!user.isActive()) {
			throw new InvalidDataException("User is Blocked by admin");
		}
		return user;
	}

//GET ALL USERS
	@Override
	public List<User> getAllUsers() {

		return userRepository.findAll();
	}

// GET USER BY ROLE
	@Override
	public List<User> getUserByRole(UserRole role) {

		return userRepository.findByRole(role);
	}

//GET USER BY EMAIL
	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
	}

//ACTIVE USER 
	@Override
	public User activeUser(long id) {
		User user = getAnyUserById(id);
		user.setActive(true);
		return userRepository.save(user);
	}

//BLOCK USER 
	@Override
	public User blockUser(long id) {
		User user = getAnyUserById(id);
		user.setActive(false);
		return userRepository.save(user);
	}

// GET USER BY ROLE
	@Override
	public List<User> getActiveUsers() {

		return userRepository.findByActiveTrue();
	}

	@Override
	public User getAnyUserById(long id) {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
	}


}
