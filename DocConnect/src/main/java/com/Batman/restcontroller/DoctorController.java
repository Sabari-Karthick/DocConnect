package com.Batman.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Batman.dto.Doctor;
import com.Batman.services.IDocconnectDoctorService;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

	@Autowired
	private IDocconnectDoctorService doctorService;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerDoctor(@RequestBody Doctor doctor){
		Doctor doctor2 = doctorService.registerDoctor(doctor);
		return new ResponseEntity<Doctor>(doctor2,HttpStatus.OK);
	}
	
	@GetMapping("/fetch/all")
	public ResponseEntity<?> getAllDoctors(){
		List<Doctor> doctors = doctorService.searchAllDcotors();
		return new ResponseEntity<List<Doctor>>(doctors,HttpStatus.OK);
	}
	
	@GetMapping("/fetch/location/{location}")
	public ResponseEntity<?> getDotorsByLocation(@PathVariable String location){
		List<Doctor> doctorByLocation = doctorService.searchDoctorByLocation(location);
		return new ResponseEntity<List<Doctor>>(doctorByLocation,HttpStatus.OK);
	}
	
	@GetMapping("/fetch/city/{city}")
	public ResponseEntity<?> getDotorsByCity(@PathVariable String city){
		List<Doctor> doctorByCity = doctorService.searchDoctorByCity(city);
		return new ResponseEntity<List<Doctor>>(doctorByCity,HttpStatus.OK);
	}
	

	@GetMapping("/fetch/id/{id}")
	public ResponseEntity<?> getDotorsByCity(@PathVariable Integer id){
		Doctor doctor = doctorService.searchDoctorByID(id);
		return new ResponseEntity<Doctor>(doctor,HttpStatus.OK);
	}
	@GetMapping("/fetch/spec/{spec}")
	public ResponseEntity<?> getDotorsBySpecialization(@PathVariable String spec){
	    List<Doctor> doctors = doctorService.searchDoctorBySpecialization(spec);
		return new ResponseEntity<List<Doctor>>(doctors,HttpStatus.OK);
	}
	
	
	@PutMapping("/modify")
	public ResponseEntity<?> updateDoctor(@RequestBody Doctor doctor){
		Doctor doctor2 = doctorService.updateDoctor(doctor);
		return new ResponseEntity<Doctor>(doctor2,HttpStatus.OK);
	}
	@DeleteMapping("/remove")
	public ResponseEntity<?> deleteDoctor(@RequestBody Doctor doctor){
		String deleteDoctor = doctorService.deleteDoctor(doctor);
		return new ResponseEntity<String>(deleteDoctor,HttpStatus.OK);
	}
	
}
