package com.Batman.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Batman.dto.Appoinment;
import com.Batman.services.IDocconnectAppointmentService;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

  
	@Autowired
	private IDocconnectAppointmentService appointmentService;
	
	
	@PostMapping("/bookAppointment")
	public ResponseEntity<?> bookAppoinment(@RequestBody Appoinment appoinment){
		Appoinment appointment2 = appointmentService.registerAppointment(appoinment);
		return ResponseEntity.ok(appointment2);
		
	}
	
	@GetMapping("/fetch/{statusCode}/doctor/{id}/")
	public ResponseEntity<?> searchAllDoctorAppointment(@PathVariable Integer id,@PathVariable Integer statusCode){
		List<Appoinment> allAppoinmentsOfDoctor = appointmentService.fetchAllAppoinmentsOfDoctorByStatus(id,statusCode);
		return  ResponseEntity.ok(allAppoinmentsOfDoctor);
	}
	
	@GetMapping("/fetch/{statusCode}/patient/{id}/")
	public ResponseEntity<?> searchAllPatientAppointment(@PathVariable Integer id,@PathVariable Integer statusCode){
		List<Appoinment> allAppoinmentsOfPatient = appointmentService.fetchAllAppoinmentsOfPatientByStatus(id,statusCode);
		return  ResponseEntity.ok(allAppoinmentsOfPatient);
	}
	
	
	@PatchMapping("/doctor/status/{id}/{code}")
	public ResponseEntity<?> updateAppointmentStatus(@PathVariable Integer id,@PathVariable Integer code){
		Appoinment appoinment = appointmentService.updateAppointmentStatus(id,code);
		return  ResponseEntity.ok(appoinment);
	}
	
}
