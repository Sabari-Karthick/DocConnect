package com.Batman.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.Batman.dto.Doctor;
import com.Batman.exception.DoctorNotFoundException;
import com.Batman.exception.ProcessingException;
import com.Batman.repository.IDocConnectDoctorRepository;

@Service

public class DocconnectDoctorServiceImpl  implements IDocconnectDoctorService{
	
	
	
	@Autowired
	private IDocConnectDoctorRepository doctorRepository;
	
	@Autowired
	private IDocconnectDoctorMailService mailService;
	

	@Override
	@Caching(evict = {
			@CacheEvict(value ="Doctors",allEntries = true),
			@CacheEvict(value = "DocByLoc",key = "#doctor.docLocation"),
			@CacheEvict(value = "DocBySpec",key = "#doctor.docSpecialization"),
			@CacheEvict(value = "DocByCity",key = "#doctor.docCity")
	})
	@Transactional
	public Doctor registerDoctor(Doctor doctor){
		 Doctor doctor2 = doctorRepository.save(doctor);
		 
		 try {
		    mailService.sendRegisterMail(doctor2);
		} catch (Exception e) {
			throw new ProcessingException("Error While sending mail");
		}
		 return doctor2;
		 
	}

	@Override
	@Cacheable(value = "Doctor" , key = "#ID")
	public Doctor searchDoctorByID(Integer ID) {
		return doctorRepository.findById(ID).orElseThrow(()-> new DoctorNotFoundException("No doctor found with this ID"));
	}

	@Override
	@Cacheable(value = "DocByLoc",key = "#location")
	public List<Doctor> searchDoctorByLocation(String location) {
		 List<Doctor> doctors = doctorRepository.findByDocLocation(location);
		 if(doctors.isEmpty()) {
		    throw new DoctorNotFoundException("No doction found in this location");
		 }
		 return doctors;
	}

	@Override
	@Cacheable(value = "DocBySpec",key = "#specialization")
	public List<Doctor> searchDoctorBySpecialization(String specialization) {
		List<Doctor> doctors = doctorRepository.findByDocSpecialization(specialization);
		if(doctors.isEmpty()) {
		    throw new DoctorNotFoundException("No Specialized Doctor Available");
		 }
		 return doctors;
	}

	@Override
	@CachePut(value = "Doctor",key = "#doctor.docID")
	@Transactional
	public Doctor updateDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	@Override
	@Caching(evict = {
			@CacheEvict(value ="Doctors",allEntries = true),
			@CacheEvict(value = "Doctor",key = "#doctor.docID"),
			@CacheEvict(value = "DocByLoc",key = "#doctor.docLocation"),
			@CacheEvict(value = "DocBySpec",key = "#doctor.docSpecialization"),
			@CacheEvict(value = "DocByCity",key = "#doctor.docCity")
	})
	@Transactional
	public String deleteDoctor(Doctor doctor){
		doctorRepository.delete(doctor);
		try {
		mailService.accountDeletedMail(doctor);
		}catch (Exception e) {
			throw new ProcessingException("Mail Processing Exception");
		}
		return "Deleted and Mail Sent";
	}

	@Override
	@Cacheable(value = "DocByCity",key = "#city")
	public List<Doctor> searchDoctorByCity(String city) {
		 List<Doctor> doctors = doctorRepository.findByDocCity(city); 
		 if(doctors.isEmpty()) {
			 throw new DoctorNotFoundException("No doctors in the city");
		 }
		 return doctors;
	}

	@Override
	@Cacheable(value = "Doctors")
	public List<Doctor> searchAllDcotors() {
		List<Doctor> doctors = doctorRepository.findAll();
	    if(doctors.isEmpty()) {
	    	throw new DoctorNotFoundException("No Doctors Registered Yet");
	    }
		return doctors;
	}
	
	
	

}
