package com.exponent.health.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exponent.health.dto.Response;
import com.exponent.health.service.AdminServiceI;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {
	
	@Autowired
	private AdminServiceI adminServiceI;

	@GetMapping(value = "/assignRole")
	public ResponseEntity<Response> assignRoleToUser(@RequestParam String email, @RequestParam String roleName){
		
	System.out.println("Check Data "+email+" " +roleName);
	Response response = adminServiceI.assignRole(email, roleName);
	return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@GetMapping("/getAllEmail")
	public ResponseEntity<List<String>> getAllActiveEmailList(){
		List<String> emailList = adminServiceI.getAllActiveEmailList();
		System.out.println(emailList);
		return new ResponseEntity<List<String>>(emailList,HttpStatus.OK);
	}
	
	@GetMapping("/getRoleName")
	public ResponseEntity<List<String>> getRoleNameList(){
		List<String> roleNameList = adminServiceI.getRoleNameList();
		System.out.println(roleNameList);
		return new ResponseEntity<List<String>>(roleNameList,HttpStatus.OK);
	}
}
