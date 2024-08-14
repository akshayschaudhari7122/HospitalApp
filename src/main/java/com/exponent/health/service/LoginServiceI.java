package com.exponent.health.service;

import com.exponent.health.dto.Response;
import com.exponent.health.dto.UserResponseDto;

public interface LoginServiceI {
	
	UserResponseDto getLoginData(String uname, String pass);
	
	Response forgotPassword(String email, String pass);

}
