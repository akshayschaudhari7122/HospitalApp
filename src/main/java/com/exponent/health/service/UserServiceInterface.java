package com.exponent.health.service;

import java.util.List;

import com.exponent.health.dto.Response;
import com.exponent.health.dto.ResponseDto;
import com.exponent.health.dto.UserRequestDto;
import com.exponent.health.entity.Login;
import com.exponent.health.entity.UserRequest;

public interface UserServiceInterface {
	
	ResponseDto addUserRequest(Login login);
	
	List<UserRequest> getAllData();
	
	UserRequest getUserRequest(String userNumber);
	
	Response updateUser(UserRequest userRequest);
	
	Response deleteUserByEmail(String email);
	
	UserRequestDto getUserAppointsDetails(String userNumber);

}
