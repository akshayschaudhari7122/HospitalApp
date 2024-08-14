package com.exponent.health.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exponent.health.dto.Response;
import com.exponent.health.dto.UserResponseDto;
import com.exponent.health.entity.Login;
import com.exponent.health.service.LoginServiceI;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/login/user")
public class LoginController {

	@Autowired
	private LoginServiceI loginServiceI;

	@PostMapping("/login")
	public ResponseEntity<UserResponseDto> getLoginData(@RequestBody Login login) {

		//System.out.println("Check Login Details " + login);
		log.info("Check Login Details "+login);
		log.debug("Check Login Details "+login);
		log.trace("Check Login Details "+login);
		log.error("Check Login Details "+login);
		log.warn("Check Login Details "+login);
		
		UserResponseDto userResponseDto = loginServiceI.getLoginData(login.getEmail(), login.getPassword());

		return new ResponseEntity<UserResponseDto>(userResponseDto,HttpStatus.OK);
	}
	
	@GetMapping(value = "/forgotPassword")
	public ResponseEntity<Response> forgotPassword(@RequestParam String email, @RequestParam String pass){
		
		System.out.println("Check Data "+email+" "+pass);
		Response response = loginServiceI.forgotPassword(email, pass);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

}
