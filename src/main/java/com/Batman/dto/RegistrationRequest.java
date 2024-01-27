package com.Batman.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class RegistrationRequest {

	
	@Email(message = "Incorrect email")
	@NotBlank(message = "Email cannot be empty")
	private String email;
	
	@NotBlank(message = "name cannot be empty")
	private String name;
	
	@NotBlank(message = "password connot be empty")
	private String password;
	

}
