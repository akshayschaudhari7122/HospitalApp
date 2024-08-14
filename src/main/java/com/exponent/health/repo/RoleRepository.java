package com.exponent.health.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exponent.health.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Role findByRoleName(String roleName);
	
	@Query(nativeQuery = true, value = "select role_name from role;")
	List<String> findRoleName();

}
