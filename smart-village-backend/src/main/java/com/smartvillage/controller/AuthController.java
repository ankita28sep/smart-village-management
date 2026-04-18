package com.smartvillage.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.smartvillage.dto.user.LoginRequestDto;
import com.smartvillage.dto.user.UserRequestDto;
import com.smartvillage.dto.user.UserResponseDto;
import com.smartvillage.entity.User;
import com.smartvillage.mapper.UserMapper;
import com.smartvillage.service.AuthService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(
            @Valid @RequestBody UserRequestDto dto) {

        User user = UserMapper.toEntity(dto);
        User saved = authService.register(user);

        return ResponseEntity.ok(UserMapper.toDto(saved));
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Valid @RequestBody LoginRequestDto dto) {

        String token = authService.login(dto);
        return ResponseEntity.ok(token);
    }
}