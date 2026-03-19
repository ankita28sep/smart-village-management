package com.smartvillage.service;

import com.smartvillage.dto.user.LoginRequestDto;
import com.smartvillage.entity.User;


public interface AuthService {

	String login(LoginRequestDto loginRequest);
	User  register(User user);
}
