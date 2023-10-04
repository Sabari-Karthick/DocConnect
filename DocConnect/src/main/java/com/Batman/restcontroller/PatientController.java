package com.Batman.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Batman.dto.Patient;
import com.Batman.services.IDocconnectPatientService;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
	
	
	@Autowired
	private IDocconnectPatientService patientService;
	

	
	@PostMapping("/register")
	public ResponseEntity<?> registerPatient(@RequestBody Patient patient) {
		return ResponseEntity.ok(patientService.registerPatient(patient));
	}

	
	
}
