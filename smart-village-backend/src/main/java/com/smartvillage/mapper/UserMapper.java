package com.smartvillage.mapper;

import com.smartvillage.dto.user.UserRequestDto;
import com.smartvillage.dto.user.UserResponseDto;
import com.smartvillage.dto.user.UserUpdateDto;
import com.smartvillage.dto.user.UserUpdateProfileDto;
import com.smartvillage.entity.User;

public class UserMapper {

    // Convert Request DTO to Entity
    public static User toEntity(UserRequestDto dto) {
        User user = new User();

        user.setName(trim(dto.getName()));
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setAddress(trim(dto.getAddress()));

        return user;
    }

    // Convert Entity to Response DTO
    public static UserResponseDto toDto(User user) {
        UserResponseDto dto = new UserResponseDto();

        dto.setId(user.getId());
        dto.setName(trim(user.getName()));
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setAddress(trim(user.getAddress()));
        dto.setActive(user.isActive());

        return dto;
    }

    // Update entity using admin DTO
    public static void updateEntity(User user, UserUpdateDto dto) {

        if (dto.getName() != null) {
            user.setName(trim(dto.getName()));
        }

        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }

        if (dto.getRole() != null) {
            user.setRole(dto.getRole());
        }

        if (dto.getAddress() != null) {
            user.setAddress(trim(dto.getAddress()));
        }

        if (dto.getActive() != null) {
            user.setActive(dto.getActive());
        }
    }

    // Update user profile (limited fields)
    public static void updateUserProfile(User user, UserUpdateProfileDto dto) {

        if (dto.getName() != null) {
            user.setName(trim(dto.getName()));
        }

        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }

        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            user.setPassword(dto.getPassword());
        }

        if (dto.getAddress() != null) {
            user.setAddress(trim(dto.getAddress()));
        }
    }

    // Utility method to safely trim strings
    private static String trim(String value) {
        return value != null ? value.trim() : null;
    }
}