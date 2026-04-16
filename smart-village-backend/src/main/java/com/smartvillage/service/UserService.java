package com.smartvillage.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.smartvillage.entity.User;
import com.smartvillage.enums.UserRole;

public interface UserService {

    User updateUser(long id, User user);

    User updateProfile(User user);

    void deleteUser(long id);

    User activeUser(long id);

    User blockUser(long id);

    User getUserById(long id);

    User getAnyUserById(long id);

    Page<User> getAllUsers(Pageable pageable);

    Page<User> getUserByRole(UserRole role, Pageable pageable);

    Page<User> getActiveUsers(Pageable pageable);

    User getUserByEmail(String email);

    User assignRole(Long userId, UserRole role);
}