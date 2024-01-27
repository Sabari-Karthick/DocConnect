package com.Batman.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.Batman.enums.AppointmentStatus;

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
