package com.Batman.dto.user;

import jakarta.validation.constraints.NotBlank;

import com.Batman.enums.MedicalSpecialty;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import lombok.Data;



@Data
public class UpgradeRequest {
	
     private Integer id;
	 
     private String location;
     
     @NotBlank(message = "Message ID cannot be Empty")
     private String medicalID;
     
     @Type(value = MedicalSpecialty.class)
     private MedicalSpecialty speciality;
     
     private Double fee;
}
