package com.Batman.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Batman.dto.appointment.AppointmentRequest;
import com.Batman.enums.AppointmentStatus;
import com.Batman.service.IAppointmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/appointment")
@RequiredArgsConstructor
public class AppointmentController {

	private final IAppointmentService appointmentService;

	@PostMapping("/service/book")
	public ResponseEntity<?> bookAppointment(@RequestBody AppointmentRequest request) {
		return ResponseEntity.ok(appointmentService.bookAppointment(request));
	}

	@PatchMapping("/update/status/appointment/{appointentID}/{status}")
	public ResponseEntity<?> updateAppointment(@PathVariable("appointentID") Integer appointmentID,
			@PathVariable("status") AppointmentStatus status) {

		return ResponseEntity.ok(appointmentService.updateAppointmentStatus(appointmentID, status));
	}
	
	

	

}
