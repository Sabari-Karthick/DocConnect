package com.Batman.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import com.Batman.Bo.DoctorBO;
import com.Batman.Bo.PatientBO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
public class Appoinment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer appointmnetID;
 
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate appointmentDate;
	
	@JsonFormat(pattern = "HH:mm:ss")
	private LocalTime appointmentTime;

    @Embedded
	private PatientBO patient;

    @Embedded
	private DoctorBO doctor;

	private String status;

}
