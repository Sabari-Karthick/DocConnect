package com.Batman.Bo;

import java.io.Serializable;

import javax.persistence.Embeddable;


import lombok.Data;

@Data
@Embeddable
public class PatientBO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	
	private Integer patientId;
	private String patientName;
	private String patientMail;
	private String patientLocation;
}
