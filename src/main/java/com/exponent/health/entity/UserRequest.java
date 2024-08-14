package com.exponent.health.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class UserRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@JsonIgnore
	private int id;
	
	private String userNumber;
	
	private String firstName;
	
	private String lastName;
	
	private String address;
	
	private int zipcode;
	
	private String country;
	
	private String gender;
	
	private String mobNumber;
	
//	@JsonIgnore
	@OneToOne(cascade = CascadeType.DETACH)
	private Role role;
	
//	@JsonIgnore
	@Type(type = "yes_no")
	private boolean status;
	
	
	

	
}
