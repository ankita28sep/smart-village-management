package com.smartvillage.service.impl;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Assign role to user
    @Override
    public User assignRole(Long userId, UserRole role) {
        User user = getAnyUserById(userId);
        user.setRole(role);
        return userRepository.save(user);
    }

    // Admin update user (merge fields)
    @Override
    public User updateUser(long id, User user) {
        User existing = getAnyUserById(id);

        if (user.getName() != null) {
            existing.setName(user.getName());
        }

        if (user.getEmail() != null && !user.getEmail().equals(existing.getEmail())) {
            userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
                if (u.getId() != id) {
                    throw new InvalidDataException("Email already in use");
                }
            });
            existing.setEmail(user.getEmail());
        }

        if (user.getPassword() != null) {
            existing.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        if (user.getAddress() != null) {
            existing.setAddress(user.getAddress());
        }

        if (user.getRole() != null) {
            existing.setRole(user.getRole());
        }

        existing.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(existing);
    }

    // User updates own profile
    @Override
    public User updateProfile(User user) {
        if (user == null || user.getId() == 0) {
            throw new InvalidDataException("User id is required to update profile");
        }

        User existing = getAnyUserById(user.getId());

        if (user.getName() != null) {
            existing.setName(user.getName());
        }

        if (user.getPassword() != null) {
            existing.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        if (user.getAddress() != null) {
            existing.setAddress(user.getAddress());
        }

        existing.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(existing);
    }

    // Delete user
    @Override
    public void deleteUser(long id) {
        User user = getAnyUserById(id);
        userRepository.delete(user);
    }

    // Get active user by id
    @Override
    public User getUserById(long id) {
        User user = getAnyUserById(id);

        if (!user.isActive()) {
            throw new InvalidDataException("User is blocked by admin");
        }

        return user;
    }

    // Get all users (pagination)
    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    // Get users by role (pagination)
    @Override
    public Page<User> getUserByRole(UserRole role, Pageable pageable) {
        return userRepository.findByRole(role, pageable);
    }

    // Get active users (pagination)
    @Override
    public Page<User> getActiveUsers(Pageable pageable) {
        return userRepository.findByActiveTrue(pageable);
    }

    // Get user by email
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }

    // Activate user
    @Override
    public User activeUser(long id) {
        User user = getAnyUserById(id);
        user.setActive(true);
        return userRepository.save(user);
    }

    // Block user
    @Override
    public User blockUser(long id) {
        User user = getAnyUserById(id);
        user.setActive(false);
        return userRepository.save(user);
    }

    // Get any user by id (no status check)
    @Override
    public User getAnyUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }
}