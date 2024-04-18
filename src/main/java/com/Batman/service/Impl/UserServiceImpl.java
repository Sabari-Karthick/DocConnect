package com.Batman.service.Impl;

import java.util.Collections;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.Batman.dto.RegistrationRequest;
import com.Batman.dto.auth.AuthenticationRequest;
import com.Batman.dto.auth.AuthenticationResponse;
import com.Batman.dto.user.UpgradeRequest;
import com.Batman.dto.user.UpgradeResponse;
import com.Batman.dto.user.UserResponse;
import com.Batman.enums.AuthenticationProiver;
import com.Batman.enums.Role;
import com.Batman.exceptions.AlreadyExistsException;
import com.Batman.exceptions.DetailsNotFoundException;
import com.Batman.exceptions.InputFieldException;
import com.Batman.exceptions.UserNotFoundException;
import com.Batman.mapper.CommonMapper;
import com.Batman.model.DoctorEntity;
import com.Batman.model.UserEntity;
import com.Batman.repository.DoctorRepository;
import com.Batman.repository.UserRepository;
import com.Batman.security.jwt.JwtProvider;
import com.Batman.service.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

	private final CommonMapper mapper;

	private final UserRepository userRepository;

	private final DoctorRepository doctorRepository;

	private final PasswordEncoder encoder;

	private final AuthenticationManager authenticationManager;

	private final JwtProvider provider;

	@Override
	public AuthenticationResponse login(AuthenticationRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		UserEntity userEntity = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new DetailsNotFoundException("EMAIL_NOT_FOUND"));
		String token = provider.createToken(userEntity.getEmail(), userEntity.getRoles().iterator().next().name());
		AuthenticationResponse response = new AuthenticationResponse();

		response.setResponse(mapper.convertToResponse(userEntity, UserResponse.class));
		response.setToken(token);
		return response;
	}

	@Override
	@Transactional
	public String registerUser(RegistrationRequest request, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new InputFieldException(bindingResult.getFieldError().getDefaultMessage());
		}
		if (userRepository.findByEmail(request.getEmail()).isPresent())
			throw new AlreadyExistsException("User already available with the mail");

		UserEntity userEntity = mapper.convertToEntity(request, UserEntity.class);

		userEntity.setRoles(Collections.singleton(Role.USER));
		userEntity.setPassword(encoder.encode(request.getPassword()));
		userEntity.setProvider(AuthenticationProiver.LOCAL);
		userRepository.save(userEntity);

		return "User SuccessFully Registered";
	}

	@Override
	public UserResponse getUserDetails(Integer userId) {
		UserEntity userEntity = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("No user found with the provided ID"));
		UserResponse userResponse = mapper.convertToResponse(userEntity, UserResponse.class);
		return userResponse;
	}

	@Override
	@Transactional
	public UpgradeResponse upgradeUserToDoctor(UpgradeRequest request, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			throw new InputFieldException(bindingResult.getFieldError().getDefaultMessage());

		UserEntity userEntity = userRepository.findById(request.getId())
				.orElseThrow(() -> new UserNotFoundException("No user found with the provided ID"));
		if (userEntity.getRoles().contains(Role.DOCTOR))
			throw new AlreadyExistsException("Already Upgraded....");
		DoctorEntity doctorEntity = mapper.convertToEntity(request, DoctorEntity.class);
		userEntity.setRoles(Collections.singleton(Role.DOCTOR));
		doctorEntity.setUser(userEntity);
		doctorEntity.setSpecialty(request.getSpeciality());
		DoctorEntity doctor = doctorRepository.save(doctorEntity);

		UpgradeResponse response = mapper.convertToResponse(userEntity, UpgradeResponse.class);
		response.setId(doctor.getDocID());
		return response;
	}

	@Override
	public UserEntity fetchUserByEmail(String usermail) {
		return userRepository.findByEmail(usermail)
				.orElseThrow(() -> new UserNotFoundException("No User Available with this mail"));
	}

}
