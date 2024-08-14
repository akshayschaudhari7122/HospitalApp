package com.exponent.health.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exponent.health.dto.Response;
import com.exponent.health.entity.Role;
import com.exponent.health.service.RoleServiceI;

@RestController
@RequestMapping(value = "/api/admin")
public class RoleController {
	
	@Autowired
	private RoleServiceI roleServiceI;
	
	@PostMapping(value = "/roleSave")
	public ResponseEntity<Response> insertRoleData(@RequestBody Role role){
		
		System.out.println("Role Data "+role);
		Response response = roleServiceI.saveRoleData(role);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}

}
