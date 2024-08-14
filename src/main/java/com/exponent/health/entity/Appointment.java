package com.exponent.health.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String appointmentNumber;
	
	private String pname;
	
	private String date;
	
	private String time;
	
	private int age;
	
	private String gender;
	
	private String category;
	
	private String appointedDoctor;
	
	private String refferedDoctor;
	
	private String location;
	
	private String mobileNumber;
	
	private String email;
	
	private String boodGroup;
	
	private String visitType;
	
	private String problemHistory;
	
	private String zipCode;
	
	private String doctorNumebr;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private UserRequest userRequest;
	
	
	
	

}
