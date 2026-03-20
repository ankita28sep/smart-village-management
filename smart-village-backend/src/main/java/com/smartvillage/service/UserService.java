package com.smartvillage.service;

import java.util.List;

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

    List<User> getUserByRole(UserRole role);

    User getUserByEmail(String email);

    List<User> getActiveUsers();

    User assignRole(Long userId, UserRole role);
}