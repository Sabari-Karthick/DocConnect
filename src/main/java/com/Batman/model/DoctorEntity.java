package com.Batman.model;

import java.io.Serializable;

import com.Batman.enums.MedicalSpecialty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "doctor_details")
public class DoctorEntity  implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer docID;
	
	@OneToOne
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	private UserEntity user;
	
	private String location;
	
	@Column(nullable = false,unique = true)
	private String medicalID;
	
	@Enumerated(EnumType.STRING)
	private MedicalSpecialty specialty;
	
	private Double fee;
	
	
}
