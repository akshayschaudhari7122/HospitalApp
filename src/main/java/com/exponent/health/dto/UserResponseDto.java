package com.exponent.health.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
	
    private String userNumber;
	
	private String firstName;
	
	private String lastName;
	
	private String mobNumber;
	
	private String email;
	
	@JsonIgnore
	private String errorMsg;
	

}
