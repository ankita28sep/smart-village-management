package com.smartvillage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartvillage.dto.user.LoginRequestDto;
import com.smartvillage.dto.user.UserRequestDto;
import com.smartvillage.dto.user.UserResponseDto;
import com.smartvillage.entity.User;
import com.smartvillage.mapper.UserMapper;
import com.smartvillage.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthService authService;

	// REGISTER
	@PostMapping("/register")
	public UserResponseDto registerUser(@Valid @RequestBody UserRequestDto dto) {
		User user = UserMapper.toEntity(dto);
		User saved = authService.register(user);
		return UserMapper.toDto(saved);
	}

	@PostMapping("/login")
	public String login(@RequestBody LoginRequestDto dto) {

		return authService.login(dto);
	}

}
