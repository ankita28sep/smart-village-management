package com.smartvillage.mapper;

import org.springframework.stereotype.Component;

import com.smartvillage.dto.user.UserRequestDto;
import com.smartvillage.dto.user.UserResponseDto;
import com.smartvillage.dto.user.UserUpdateDto;
import com.smartvillage.dto.user.UserUpdateProfileDto;
import com.smartvillage.entity.User;
@Component
public class UserMapper {
	// TO CONVERT THE REQUESTDTO TO ENTITY
	public static User toEntity(UserRequestDto dto) {
			User user = new User();
			user.setName(dto.getName().trim());
			user.setEmail(dto.getEmail());
			user.setPassword(dto.getPassword());
			user.setRole(dto.getRole());
			user.setAddress(dto.getAddress().trim());
		return user;
		
	}
	// TO CONVERT THE ENTITY TO RESPONSEDTO
	public static UserResponseDto toDto(User user) {
		UserResponseDto dto=new UserResponseDto();
		dto.setId(user.getId());
		dto.setName(user.getName().trim());
		dto.setEmail(user.getEmail());
		dto.setRole(user.getRole());
		dto.setAddress(user.getAddress().trim());
		dto.setActive(user.isActive());
		return dto;
	}
	// TO UPDATE THE ENTITY VALUSES BY THE REQUEST DTO VALUES
	public static void updateEntity(User user, UserUpdateDto dto) {
		if (dto.getName() != null) {
			user.setName(dto.getName().trim());
		}
		if (dto.getEmail() != null) {
			user.setEmail(dto.getEmail());
		}
		if (dto.getRole() != null) {
			user.setRole(dto.getRole());
		}
		if (dto.getAddress() != null) {
			user.setAddress(dto.getAddress().trim());
		}
		if (dto.isActive() != null) {
		    user.setActive(dto.isActive());
		}
		
		
	}
	// TO UPDATE THE USER PROFILE 
	public static void updateUserProfile(User user,UserUpdateProfileDto dto ) {
		if (dto.getName() != null) {
			user.setName(dto.getName().trim());
		}
		if (dto.getEmail() != null) {
			user.setEmail(dto.getEmail());
		}
		if (dto.getPassword() != null&&!dto.getPassword().isEmpty()) {
			user.setPassword(dto.getPassword());
		}
		if (dto.getAddress() != null) {
			user.setAddress(dto.getAddress().trim());
		}
		
	}

}
