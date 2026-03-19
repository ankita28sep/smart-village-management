package com.smartvillage.dto.user;

import com.smartvillage.enums.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoginRequestDto {
	@Email(message = "Invalid email format")
	@NotBlank(message = "Email is required")
	private String email;
	@NotBlank(message = "Password is required")
	private String password;
	@NotNull(message = "User role is required")
	private UserRole role;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}

}
