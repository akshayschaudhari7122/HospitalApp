package com.exponent.health.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class UserRequestIDGenerator {
	
	public static String generateUserID() {
		
		LocalDateTime now = LocalDateTime.now();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
		String date = now.format(formatter);
		
		Random random = new Random();
		
		int i = random.nextInt(9999)+100;
		String userId = date+i;
		System.out.println(userId);
		return userId;
	}

}
