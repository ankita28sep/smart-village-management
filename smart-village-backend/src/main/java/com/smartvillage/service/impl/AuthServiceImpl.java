package com.smartvillage.service.impl;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smartvillage.dto.user.LoginRequestDto;
import com.smartvillage.entity.User;
import com.smartvillage.enums.UserRole;
import com.smartvillage.exceptions.DuplicateResourceException;
import com.smartvillage.exceptions.InvalidDataException;
import com.smartvillage.repository.UserRepository;
import com.smartvillage.security.JwtUtil;
import com.smartvillage.service.AuthService;
import com.smartvillage.service.UserService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authManager,
                           JwtUtil jwtUtil,
                           UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    // LOGIN
    @Override
    public String login(LoginRequestDto dto) {

        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getEmail(),
                            dto.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new InvalidDataException("Invalid email or password");
        }

        User user = userService.getUserByEmail(dto.getEmail());

        if (!user.isActive()) {
            throw new InvalidDataException("User is blocked by admin");
        }

        return jwtUtil.generateToken(user.getEmail());
    }

    // REGISTER
    @Override
    public User register(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setActive(true);

        // Default role
       user.setRole(UserRole.CITIZEN);
       

        return userRepository.save(user);
    }
}