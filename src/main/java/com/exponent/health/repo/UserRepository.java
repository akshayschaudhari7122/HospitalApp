package com.exponent.health.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exponent.health.entity.UserRequest;

@Repository
public interface UserRepository extends JpaRepository<UserRequest, Integer> {

	UserRequest findByUserNumber(String userNumber);
 
}
