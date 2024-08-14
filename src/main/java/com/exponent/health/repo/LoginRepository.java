package com.exponent.health.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exponent.health.entity.Login;
import com.exponent.health.entity.UserRequest;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {
	
	Login findByEmail(String email);
	
	Login findByEmailAndPassword(String email, String pass);
	
	@Query(nativeQuery = true, value = "select email from login;")
	List<String> findAllEmails();
	
	

}
