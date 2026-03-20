package com.smartvillage.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.smartvillage.dto.user.UserResponseDto;
import com.smartvillage.dto.user.UserUpdateDto;
import com.smartvillage.dto.user.UserUpdateProfileDto;
import com.smartvillage.entity.User;
import com.smartvillage.enums.UserRole;
import com.smartvillage.mapper.UserMapper;
import com.smartvillage.service.UserService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // Constructor injection instead of field injection
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Assign role to a user (Admin only)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/assign-role/{userId}")
    public ResponseEntity<UserResponseDto> assignRole(@PathVariable Long userId,
                                                      @RequestParam UserRole role) {
        User user = userService.assignRole(userId, role);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    // Admin updates user details
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/update/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable long id,
            @Valid @RequestBody UserUpdateDto dto) {

        User user = userService.getAnyUserById(id);
        UserMapper.updateEntity(user, dto);
        User updatedUser = userService.updateUser(id, user);

        return ResponseEntity.ok(UserMapper.toDto(updatedUser));
    }

    // Logged-in user updates own profile
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/update-profile")
    public ResponseEntity<UserResponseDto> updateProfile(
            Principal principal,
            @Valid @RequestBody UserUpdateProfileDto dto) {

        String email = principal.getName();
        User user = userService.getUserByEmail(email);

        UserMapper.updateUserProfile(user, dto);
        User updatedUser = userService.updateProfile(user);

        return ResponseEntity.ok(UserMapper.toDto(updatedUser));
    }

    // Delete user by ID (Admin only)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Get user by ID (accessible to authenticated users)
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    // Admin can fetch any user by ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/{id}")
    public ResponseEntity<UserResponseDto> getAnyUserById(@PathVariable long id) {
        User user = userService.getAnyUserById(id);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    // Get all users with pagination
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<UserResponseDto>> getAllUsers(Pageable pageable) {
        Page<User> users = userService.getAllUsers(pageable);
        Page<UserResponseDto> dtoPage = users.map(UserMapper::toDto);
        return ResponseEntity.ok(dtoPage);
    }

    // Get users by role
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/by-role")
    public ResponseEntity<List<UserResponseDto>> getUsersByRole(@RequestParam UserRole role) {
        List<UserResponseDto> users = userService.getUserByRole(role)
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    // Activate a user account
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/activate/{id}")
    public ResponseEntity<UserResponseDto> activateUser(@PathVariable long id) {
        User user = userService.activeUser(id);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    // Block a user account
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/block/{id}")
    public ResponseEntity<UserResponseDto> blockUser(@PathVariable long id) {
        User user = userService.blockUser(id);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    // Get all active users
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/active")
    public ResponseEntity<List<UserResponseDto>> getActiveUsers() {
        List<UserResponseDto> users = userService.getActiveUsers()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }
}