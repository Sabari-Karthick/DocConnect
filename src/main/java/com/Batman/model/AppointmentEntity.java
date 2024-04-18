package com.Batman.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.Batman.enums.AppointmentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "appointments")
@Data
public class AppointmentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String patientName;

	@Column(name = "patient_phone", nullable = false,length = 20)
	private String phone;

	@ManyToOne
	@JoinColumn(name = "doctor_id", referencedColumnName = "docID")
	private DoctorEntity doctor;

	@ManyToOne
	@JoinColumn(name = "booked_by")
	private UserEntity bookedBy;

	private LocalDate date;

	private LocalTime startTime;

	private LocalTime endTime;

	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;

	private Long slotToken;

}
