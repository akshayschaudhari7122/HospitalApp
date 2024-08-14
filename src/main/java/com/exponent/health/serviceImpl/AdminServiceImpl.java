package com.exponent.health.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exponent.health.dto.Response;
import com.exponent.health.entity.Login;
import com.exponent.health.entity.Role;
import com.exponent.health.entity.UserRequest;
import com.exponent.health.repo.LoginRepository;
import com.exponent.health.repo.RoleRepository;
import com.exponent.health.repo.UserRepository;
import com.exponent.health.service.AdminServiceI;

@Service
public class AdminServiceImpl implements AdminServiceI{
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Response assignRole(String email, String roleName) {
		
		Login login = loginRepository.findByEmail(email);
		if(login != null && login.getUserRequest() != null) {
			
			Role role = roleRepository.findByRoleName(roleName);
			if(role != null) {
				
				UserRequest userRequest = login.getUserRequest();
				userRequest.setRole(role);
				userRepository.save(userRequest);
				
			}
			return Response.builder().msg("Role Assigned Successfully").build();
		}
			
		return Response.builder().msg("Role Not Assigned").build();
		}

	@Override
	public List<String> getAllActiveEmailList() {
		
		return loginRepository.findAllEmails();
	}

	@Override
	public List<String> getRoleNameList() {
		
		return roleRepository.findRoleName();
	}
		
		
		
	}


