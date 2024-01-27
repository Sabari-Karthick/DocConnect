package com.Batman.repository;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Batman.enums.AppointmentStatus;
import com.Batman.model.AppointmentEntity;


public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Integer> {
	Optional<AppointmentEntity> findByDateAndStartTime(LocalDate date,LocalTime time);
	long countByDateAndStartTimeAndDoctorDocID(LocalDate date,LocalTime time,Integer docID);
	List<AppointmentEntity> findByDoctorDocIDAndStatus(Integer doc_id,AppointmentStatus status);
	List<AppointmentEntity> findByBookedByIdAndStatus(Integer booked_user_id,AppointmentStatus status);
}