package com.smartvillage.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@Autowired
	UserService userService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/assign-role/{userId}")
	public UserResponseDto assignRole(@PathVariable Long userId,
	                                  @RequestParam UserRole role) {

	  
	    return UserMapper.toDto(userService.assignRole(userId, role));
	}

	//ADMIN  UPDATE
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/admin/update/{id}")
	public UserResponseDto updateUser(@PathVariable long id, @Valid @RequestBody UserUpdateDto dto) {
		User user = userService.getAnyUserById(id);
		UserMapper.updateEntity(user, dto);
		User saved = userService.updateUser(id, user);
		return UserMapper.toDto(saved);
	}

	// UPDATE
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/update-profile")
	public UserResponseDto updateProfile(@Valid @RequestBody UserUpdateProfileDto dto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		User user = userService.getUserByEmail(email);
		UserMapper.updateUserProfile(user, dto);
		User saved = userService.updateProfile(user);
		return UserMapper.toDto(saved);
	}

	// DELETE
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable long id) {
		userService.deleteUser(id);
		return "User with ID " + id + " has been deleted.";
	}

	// GET USER BY ID
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/byId/{id}")
	public UserResponseDto getUserById(@PathVariable long id) {
		return UserMapper.toDto(userService.getUserById(id));
	}

	// GET USERS FOR ADMIN
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin/byId/{id}")
	public UserResponseDto getAnyUserById(@PathVariable long id) {
		return UserMapper.toDto(userService.getAnyUserById(id));
	}

	// GET ALL USERS
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getAll")
	public List<UserResponseDto> getAllUsers() {
		return userService.getAllUsers().stream().map(UserMapper::toDto).collect(Collectors.toList());
	}

	// GET USERS BY ROLE
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/byRole")
	public List<UserResponseDto> getUserByRole(@RequestParam UserRole role) {
		return userService.getUserByRole(role).stream().map(UserMapper::toDto).collect(Collectors.toList());
	}

	// ACTIVE USER
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/activate/{id}")
	public UserResponseDto activeUser(@PathVariable long id) {
		return UserMapper.toDto(userService.activeUser(id));
	}

	// BLOCK USER
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/block/{id}")
	public UserResponseDto blockUser(@PathVariable long id) {
		return UserMapper.toDto(userService.blockUser(id));
	}

	// GET ACTIVE USERS
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/active-users")
	public List<UserResponseDto> getActiveUsers() {
		return userService.getActiveUsers().stream().map(UserMapper::toDto).collect(Collectors.toList());
	}

}
