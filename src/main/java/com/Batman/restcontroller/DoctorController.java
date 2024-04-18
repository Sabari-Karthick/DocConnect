package com.Batman.restcontroller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Batman.enums.AppointmentStatus;
import com.Batman.enums.MedicalSpecialty;
import com.Batman.service.IAppointmentService;
import com.Batman.service.IDoctorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('DOCTOR','ADMIN')")
public class DoctorController {

	private final IDoctorService doctorService;

	private final IAppointmentService appointmentService;

	@GetMapping("/details/{id}")
	public ResponseEntity<?> getDoctorDetails(@PathVariable Integer id) {
		return ResponseEntity.ok(doctorService.getDoctorInfo(id));
	}

	@GetMapping("/all/location/{location}")
	public ResponseEntity<?> getDoctorDetailsByLoc(@PathVariable String location) {
		return ResponseEntity.ok(doctorService.getAllDoctorsByLocation(location));
	}

	@GetMapping("/all/speciality/{speciality}")
	public ResponseEntity<?> getDoctorDetailsBySpec(@PathVariable MedicalSpecialty speciality) {
		return ResponseEntity.ok(doctorService.getAllDoctorsBySpeciality(speciality));
	}

	@GetMapping("/my/appointments/{docID}/{status}")
	@PreAuthorize("hasAuthority('DOCTOR')")
	public ResponseEntity<?> getAllAppointmentsOfDoctor(@PathVariable Integer docID,
			@PathVariable AppointmentStatus status) {
		return ResponseEntity.ok(appointmentService.searchAllAppointmentsByDocandStatus(docID, status));
	}

	
}
