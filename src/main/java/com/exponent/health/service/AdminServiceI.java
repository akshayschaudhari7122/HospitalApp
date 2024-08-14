package com.exponent.health.service;

import java.util.List;

import com.exponent.health.dto.Response;

public interface AdminServiceI {
	
	Response assignRole(String email, String roleName);
	
	List<String> getAllActiveEmailList();
	
	List<String> getRoleNameList();

}
