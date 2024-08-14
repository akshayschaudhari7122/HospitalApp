package com.exponent.health.service;

import com.exponent.health.dto.Response;
import com.exponent.health.entity.Appointment;

public interface AppointmentServiceInterface {
	
	Response bookAppointment(Appointment appointment,String userNumber);

}
