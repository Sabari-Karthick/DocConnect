package com.Batman.restcontroller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Batman.dto.RegistrationRequest;
import com.Batman.dto.auth.AuthenticationRequest;
import com.Batman.dto.auth.AuthenticationResponse;
import com.Batman.dto.user.UpgradeRequest;
import com.Batman.enums.AppointmentStatus;
import com.Batman.service.IAppointmentService;
import com.Batman.service.IUserService;

import lombok.RequiredArgsConstructor;

/**
 * 
 * 
 * @author karthick
 * @date 16-10-2023
 *
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
public class UserController {
	
	private final IUserService userService;
	
	private final IAppointmentService appointmentService;
	
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest request){
		
		return new ResponseEntity<AuthenticationResponse>(userService.login(request),HttpStatus.OK);
		
	}
	
    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@Valid @RequestBody RegistrationRequest request,BindingResult bindingResult){
		return ResponseEntity.ok(userService.registerUser(request,bindingResult));
    	
    }
    
    @GetMapping("/details/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id){
    	return ResponseEntity.ok(userService.getUserDetails(id));
    }
    
    
    @PostMapping("/upgrade/doctor")
    public ResponseEntity<?> upgradeUserToDoctor(@Valid @RequestBody UpgradeRequest request,BindingResult bindingResult){
    	return ResponseEntity.ok(userService.upgradeUserToDoctor(request, bindingResult));
    }
    
    @GetMapping("/appointment/{userID}/appointments/{status}")
	public ResponseEntity<?> getAllUserAppointmentsByStatus(@PathVariable("userID") Integer userID,
			@PathVariable AppointmentStatus status) {
		return ResponseEntity.ok(appointmentService.searchAllAppointmentsByUserandStatus(userID, status));
	}

}
