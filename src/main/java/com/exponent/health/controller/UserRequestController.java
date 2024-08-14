package com.exponent.health.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exponent.health.dto.Response;
import com.exponent.health.dto.ResponseDto;
import com.exponent.health.dto.UserRequestDto;
import com.exponent.health.entity.Login;
import com.exponent.health.entity.UserRequest;
import com.exponent.health.service.UserServiceInterface;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/api/user")
public class UserRequestController {

	@Autowired
	UserServiceInterface userServiceInterface;

	@PostMapping(value = "/save", consumes = "application/json")
	public ResponseEntity<ResponseDto> registerUser(@RequestBody Login login) {

		System.out.println("Check User Data " + login);
		ResponseDto responseDto = userServiceInterface.addUserRequest(login);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.CREATED);
	}

	@GetMapping(value = "/getAllData")
	public List<UserRequest> getAllData() {

		List<UserRequest> list = userServiceInterface.getAllData();
		return list;
	}

	@GetMapping(value = "/get/{userNumber}")
	public ResponseEntity<UserRequest> getUserRequest(@PathVariable String userNumber) {

		log.info("User Request Data Fetch "+userNumber);
		UserRequest userRequest = userServiceInterface.getUserRequest(userNumber);
		
		return new ResponseEntity<UserRequest>(userRequest, HttpStatus.OK);
	}

	@PutMapping(value = "/update")
	public ResponseEntity<Response> updateUserByUserNumebr(@RequestBody UserRequest userRequest) {

		Response response = userServiceInterface.updateUser(userRequest);

		if (response != null) {

			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteUser/{email}")
	public ResponseEntity<Response> deleteUserByEmail(@PathVariable String email) {

		Response response = userServiceInterface.deleteUserByEmail(email);

		if (response != null) {

			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/userAllAppointment/{userNumber}")
	public ResponseEntity<UserRequestDto> getUserAppointsDetails(@PathVariable String userNumber){
		
		log.info("Check Details "+userNumber);
		userServiceInterface.getUserAppointsDetails(userNumber);
		return null;
		
	}

}
