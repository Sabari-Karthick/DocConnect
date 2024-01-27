package com.Batman.dto.doctor;

import com.Batman.enums.MedicalSpecialty;

import lombok.Data;


@Data
public class DoctorResponse {

	private Integer  docID;
	private String name;
	private String email;
	private MedicalSpecialty specialty;
	private Double fee;
	private String location;
	
	
}
