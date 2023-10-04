package com.Batman.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Batman.constants.StatusConstants;
import com.Batman.dto.Appoinment;
import com.Batman.exception.AlreadyBookedException;
import com.Batman.exception.AppointmentNotFoundException;
import com.Batman.exception.ProcessingException;
import com.Batman.repository.IDocconnectAppointmentRepository;

@Service
public class DocconnectAppointmentServiceImpl implements IDocconnectAppointmentService {

	@Autowired
	private IDocconnectAppointmentRepository appointmentRepository;

	@Autowired
	private IDocconnectAppointmentMailService mailService;

	@Override
	@Transactional
	public Appoinment registerAppointment(Appoinment appoinment) {
		
		if (appointmentRepository.existsByAppointmentDateAndAppointmentTime(appoinment.getAppointmentDate(),appoinment.getAppointmentTime())) {
			throw new AlreadyBookedException("Appointment is already Fixed By someone");
		}
		appoinment.setStatus(StatusConstants.STAGE_ZERO);
		Appoinment appoinment2 = appointmentRepository.save(appoinment);

		mailService.sendNotificationMail(appoinment2);
		return appoinment2;
	}

	@Override
	public List<Appoinment> fetchAllAppoinmentsOfDoctorByStatus(Integer docID, Integer statusCode) {
		String status;
		status = getStatus(statusCode);
		List<Appoinment> allAppoinmentsByDocId = appointmentRepository.findAllAppoinmentsByDoctorDocIdAndStatus(docID,
				status);

		return allAppoinmentsByDocId;
	}

	@Override
	public Appoinment updateAppointmentStatus(Integer id, Integer code) {
		Optional<Appoinment> appointmentOptional = appointmentRepository.findById(id);
		Appoinment appoinment = appointmentOptional
				.orElseThrow(() -> new AppointmentNotFoundException("No Appointment Found with this ID :: " + id));

		String status = getStatus(code);
		appoinment.setStatus(status);

		 Appoinment appoinment2 = appointmentRepository.save(appoinment);
		 mailService.sendStatusMail(appoinment2);
		 return appoinment2;
	}
	
	@Override
	public List<Appoinment> fetchAllAppoinmentsOfPatientByStatus(Integer patientID, Integer statusCode) {
		String status;
		status = getStatus(statusCode);
		List<Appoinment> allAppoinmentsOfPatient = appointmentRepository.findAllAppoinmentsByPatientPatientIdAndStatus(patientID, status);

		return allAppoinmentsOfPatient;
	}


	@Override
	public void closeAllAppointments() {
		
	}
	private String getStatus(Integer statusCode) {
		String status;
		switch (statusCode) {
		case 0:
			status = StatusConstants.STAGE_ZERO;
			break;
		case 1:
			status = StatusConstants.STAGE_ONE;
			break;
		case 2:
			status = StatusConstants.STAGE_TWO;
			break;
		case 3:
			status = StatusConstants.STAGE_THREE;
			break;

		default:
			throw new ProcessingException("Invalid Status Code :: " + statusCode);

		}
		return status;
	}

	

	
}
