package com.Batman.services;

import java.util.List;

import com.Batman.dto.Appoinment;

public interface IDocconnectAppointmentService {
	 Appoinment registerAppointment(Appoinment appoinment) ;
	 List<Appoinment> fetchAllAppoinmentsOfDoctorByStatus(Integer docID,Integer statusCode);
	 List<Appoinment> fetchAllAppoinmentsOfPatientByStatus(Integer patientID,Integer statusCode);
	 Appoinment updateAppointmentStatus(Integer id,Integer code);
	 void closeAllAppointments();
}
