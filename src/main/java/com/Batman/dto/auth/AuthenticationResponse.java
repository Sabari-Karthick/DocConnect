package com.Batman.dto.auth;

import com.Batman.dto.user.UserResponse;

import lombok.Data;

//authentication response
@Data
public class AuthenticationResponse {

	
	private UserResponse response;
	private String token;
}
