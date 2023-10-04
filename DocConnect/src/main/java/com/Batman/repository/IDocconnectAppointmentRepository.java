package com.Batman.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.Batman.dto.Appoinment;

public interface IDocconnectAppointmentRepository extends JpaRepository<Appoinment, Integer>,JpaSpecificationExecutor<Appoinment> {
    
	
	
	List<Appoinment> findAllAppoinmentsByDoctorDocIdAndStatus (Integer docId,String status);
	List<Appoinment> findAllAppoinmentsByPatientPatientIdAndStatus (Integer docId,String status);
//	@Query("from com.Batman.dto.Appoinment where appointmentDateTime ")
//	List<Appoinment> findAllExpiredAppointments(LocalDateTime dt);
	Appoinment findByPatientPatientMail(String mail);
	
	boolean existsByAppointmentDateAndAppointmentTime(LocalDate date,LocalTime timeslot);
}
