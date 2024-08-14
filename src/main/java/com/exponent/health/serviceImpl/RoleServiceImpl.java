package com.exponent.health.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exponent.health.dto.Response;
import com.exponent.health.entity.Role;
import com.exponent.health.repo.RoleRepository;
import com.exponent.health.service.RoleServiceI;

@Service
public class RoleServiceImpl implements RoleServiceI{
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Response saveRoleData(Role role) {
		
		Response response = new Response();
		Role role2 = roleRepository.save(role);
		if(role2 != null) {
			
			response.setMsg("Role Data Inserted");
		}else {
			
			response.setMsg("Role Data Not Inserted");
		}
		return response;
	}

}
