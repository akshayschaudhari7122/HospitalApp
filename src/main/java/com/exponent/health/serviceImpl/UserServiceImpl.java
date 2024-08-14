package com.exponent.health.serviceImpl;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.exponent.health.dto.Response;
import com.exponent.health.dto.ResponseDto;
import com.exponent.health.dto.UserRequestDto;
import com.exponent.health.entity.Appointment;
import com.exponent.health.entity.Login;
import com.exponent.health.entity.Role;
import com.exponent.health.entity.UserRequest;
import com.exponent.health.repo.AppointmentRepository;
import com.exponent.health.repo.LoginRepository;
import com.exponent.health.repo.RoleRepository;
import com.exponent.health.repo.UserRepository;
import com.exponent.health.service.UserServiceInterface;
import com.exponent.health.util.UserRequestIDGenerator;

import lombok.extern.slf4j.Slf4j;

@Service
//comment
@Slf4j
public class UserServiceImpl implements UserServiceInterface {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AppointmentRepository appointmentRepository;

	@Override
	public ResponseDto addUserRequest(Login login) {

		ResponseDto responseDto = new ResponseDto();

		Login login2 = null;

		if (login.getEmail() == null) {

			System.out.println("Please Provide Emial");
			responseDto.setMsg("Please Provide Email");
			return responseDto;
		}
		Login login1 = loginRepository.findByEmail(login.getEmail());

		if (login1 != null) {

			System.out.println("User Emial Null Calling");
			responseDto.setMsg("User Already Exist");
			return responseDto;
		}

		String userId = UserRequestIDGenerator.generateUserID();
		login.getUserRequest().setUserNumber(userId);
		login.getUserRequest().setStatus(true);
		Role role = roleRepository.findById(2).get();
		login.getUserRequest().setRole(role);
		login2 = loginRepository.save(login);

		if (login2 != null && login2.getId() > 0) {

			responseDto.setMsg("User Successfully Registered");

			try {
				MimeMessage message = javaMailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
				helper.setTo(login.getEmail());
				helper.setSubject("Thanks For Creating Account.");
				helper.setText("<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "<head>\r\n"
						+ "    <meta charset=\"UTF-8\">\r\n"
						+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
						+ "    <title>Welcome to Our Service</title>\r\n" + "    <style>\r\n"
						+ "        body { background-color: whitesmoke; font-family: Arial, sans-serif; color: #333; }\r\n"
						+ "        .container { max-width: 600px; margin: 0 auto; padding: 20px; background-color: #f2c99e; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }\r\n"
						+ "        .header { background-color: #3bd5e9; color: #fff; padding: 10px 0; text-align: center; border-radius: 10px 10px 0 0; }\r\n"
						+ "        .header h1 { margin: 0; font-size: 24px; }\r\n"
						+ "        .content { padding: 20px;background-color: aquamarine; }\r\n"
						+ "        .content p { line-height: 1.6; }\r\n"
						+ "        .content strong { color: #007bff; }\r\n"
						+ "        .footer { margin-top: 20px; text-align: center; font-size: 12px; color: #999; }\r\n"
						+ "    </style>\r\n" + "</head>\r\n" + "<body>\r\n" + "    <div class=\"container\">\r\n"
						+ "        <div class=\"header\">\r\n" + "            <h1>Welcome, "
						+ login.getUserRequest().getFirstName() + " " + login.getUserRequest().getLastName()
						+ "!</h1>\r\n" + "        </div>\r\n" + "        <div class=\"content\">\r\n"
						+ "            <p>Thank you for creating an account with us. We are thrilled to have you on board!</p>\r\n"
						+ "            <p>Your account number is: <strong>" + login.getUserRequest().getUserNumber()
						+ "</strong></p>\r\n"
						+ "            <p>We look forward to serving you and providing the best experience possible.</p>\r\n"
						+ "            <p>If you have any questions or need assistance, please feel free to contact our support team.</p>\r\n"
						+ "            <p>Best regards,</p>\r\n" + "            <p>The Team</p>\r\n"
						+ "        </div>\r\n" + "        <div class=\"footer\">\r\n"
						+ "            <p>&copy; 2024 Hospital Mgt Sys. All rights reserved.</p>\r\n"
						+ "        </div>\r\n" + "    </div>\r\n" + "</body>\r\n" + "</html>", true);
				// javaMailSender.send(message);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return responseDto;
		} else {

			responseDto.setMsg("User Not Successfully Registered");
			return responseDto;
		}

	}

	@Override
	public List<UserRequest> getAllData() {

		List<UserRequest> userRequest = userRepository.findAll();
		return userRequest;

	}

	@Override
	public UserRequest getUserRequest(String userNumber) {
		UserRequest userRequest = userRepository.findByUserNumber(userNumber);
		
		return userRequest;
	}

	@Override
	public Response updateUser(UserRequest userRequest) {

		Response response = new Response();

		if (userRequest == null) {
			response.setMsg("User not found.");
			return response;
		}

		userRepository.save(userRequest);
		response.setMsg("User Data Successfully Updated.");
		return response;
	}

	@Override
	public Response deleteUserByEmail(String email) {

		Response response = new Response();
		Login login = loginRepository.findByEmail(email);
		if (login == null) {

			response.setMsg("User Not Found.");
			return response;
		} else {

			loginRepository.delete(login);
			response.setMsg("User Successfully Deleted.");
			return response;
		}
	}

	@Override
	public UserRequestDto getUserAppointsDetails(String userNumber) {
		
		List<Appointment> appointments = appointmentRepository.findByUserRequestUserNumber(userNumber);
		log.debug("Applointment List "+appointments);
		return null;
	}
}
