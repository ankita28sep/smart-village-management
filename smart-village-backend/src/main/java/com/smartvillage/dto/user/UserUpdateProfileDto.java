package com.smartvillage.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserUpdateProfileDto {
	@NotBlank(message = "name is required")
	private String name;
	@Email(message = "email is not in required format")
	private String email;
	@NotBlank(message = "address is required")
	private String address;
	@Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
