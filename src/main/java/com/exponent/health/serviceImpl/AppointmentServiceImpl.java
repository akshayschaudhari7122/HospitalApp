package com.exponent.health.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.exponent.health.dto.Response;
import com.exponent.health.entity.Appointment;
import com.exponent.health.entity.UserRequest;
import com.exponent.health.repo.AppointmentRepository;
import com.exponent.health.repo.UserRepository;
import com.exponent.health.service.AppointmentServiceInterface;
import com.exponent.health.util.EmailSender;
import com.exponent.health.util.UserRequestIDGenerator;

@Service
public class AppointmentServiceImpl implements AppointmentServiceInterface{
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public Response bookAppointment(Appointment appointment,String userNumber) {
		
		Response response = new Response();

		UserRequest user = userRepository.findByUserNumber(userNumber);

		if (user != null) {
			System.out.println("User Found: " + user.getUserNumber());

			appointment.setUserRequest(user);

			try {
				String apponum = UserRequestIDGenerator.generateUserID();
				appointment.setAppointmentNumber(apponum);

				Appointment savedAppointment = appointmentRepository.save(appointment);

				if (savedAppointment != null) {
				//	EmailSender.sendAppointmentConfirmationEmail(javaMailSender, savedAppointment);
					response.setMsg("Appointment Data Inserted.");
				} else {
					response.setMsg("Appointment Data Not Inserted.");
				}
			} catch (Exception e) {
				response.setMsg("Error Occurred While Inserting Appointment Data: " + e.getMessage());
			}
		} else {
			response.setMsg("User Not Found.");
		}

		System.out.println("Response Message: " + response.getMsg());
		return response;
	}

}
