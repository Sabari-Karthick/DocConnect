package com.Batman.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Batman.dto.Patient;
import com.Batman.exception.ProcessingException;
import com.Batman.repository.IDocConnectPatientRepository;


@Service
public class DocconnectPatientServiceImpl implements IDocconnectPatientService {

	
	@Autowired
	private IDocConnectPatientRepository patientRepository;
	
	@Autowired
	private IDocconnectPatientMailService mailService;
	
	@Override
	public Patient registerPatient(Patient patient) {
	  Patient patient2 = patientRepository.save(patient);
	   
	  try {
		mailService.sendRegisterMail(patient2);
	} catch (Exception e) {
		throw new ProcessingException("Mail Processing Error");
	}
		
		return patient2;
	}

	

}
