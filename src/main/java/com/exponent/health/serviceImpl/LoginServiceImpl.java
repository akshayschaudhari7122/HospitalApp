package com.exponent.health.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exponent.health.dto.Response;
import com.exponent.health.dto.UserResponseDto;
import com.exponent.health.entity.Login;
import com.exponent.health.entity.UserRequest;
import com.exponent.health.repo.LoginRepository;
import com.exponent.health.service.LoginServiceI;

@Service
public class LoginServiceImpl implements LoginServiceI {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public UserResponseDto getLoginData(String uname, String pass) {

		UserResponseDto userResponseDto = new UserResponseDto();

		Login login = loginRepository.findByEmailAndPassword(uname, pass);
		if (login != null && login.getUserRequest() != null) {

			UserRequest userRequest = login.getUserRequest();
			if (userRequest.isStatus() && userRequest.getRole() != null) {
				userResponseDto.setUserNumber(userRequest.getUserNumber());
				userResponseDto.setFirstName(userRequest.getFirstName());
				userResponseDto.setLastName(userRequest.getLastName());
				userResponseDto.setMobNumber(userRequest.getMobNumber());
				userResponseDto.setEmail(login.getEmail());
				return userResponseDto;
			} else {

				userResponseDto.setErrorMsg("Access Denied.Please Contact Administrator");
				return userResponseDto;
			}

		} else {

			userResponseDto.setErrorMsg("User Does Not Exists");
			return userResponseDto;
		}

	}

	@Override
	public Response forgotPassword(String email, String pass) {

		Response response = new Response();

		Login login = loginRepository.findByEmail(email);
		login.setPassword(pass);
		Login login2 = loginRepository.save(login);

		if (login2 != null) {

			response.setMsg("Password Updated Successfully");
		} else {

			response.setMsg("Password Not Updated Successfully");
		}

		return response;

	}

}
