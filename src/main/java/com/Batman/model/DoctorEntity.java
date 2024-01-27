package com.Batman.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.Batman.enums.MedicalSpecialty;

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
