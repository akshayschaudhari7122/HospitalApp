package com.exponent.health.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exponent.health.dto.Response;
import com.exponent.health.entity.Appointment;
import com.exponent.health.service.AppointmentServiceInterface;

@RestController
@RequestMapping(value = "/api/all")
public class AppointmentController {
	
	@Autowired
	private AppointmentServiceInterface appointmentServiceInterface;
	
	@PostMapping(value = "/book/{userNumber}")
	public ResponseEntity<Response> bookAppointment(@RequestBody Appointment appointment,@PathVariable String userNumber){
		
		System.out.println("Check Data "+appointment+" "+userNumber);
		Response response = appointmentServiceInterface.bookAppointment(appointment,userNumber);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}

}
