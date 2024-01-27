package com.Batman.service;


import org.springframework.validation.BindingResult;

import com.Batman.dto.RegistrationRequest;
import com.Batman.dto.auth.AuthenticationRequest;
import com.Batman.dto.auth.AuthenticationResponse;
import com.Batman.dto.user.UpgradeRequest;
import com.Batman.dto.user.UpgradeResponse;
import com.Batman.dto.user.UserResponse;
import com.Batman.model.UserEntity;

public interface IUserService {
	String registerUser(RegistrationRequest request,BindingResult bindingResult);
	UserResponse getUserDetails(Integer userId);
	UpgradeResponse upgradeUserToDoctor(UpgradeRequest request,BindingResult bindingResult);
    UserEntity fetchUserByEmail(String usermail);
    AuthenticationResponse login(AuthenticationRequest request);



}
