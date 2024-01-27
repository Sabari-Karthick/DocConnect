package com.Batman.dto.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AuthenticationRequest {
	
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	@Size(min = 6)
	private String password;
}
