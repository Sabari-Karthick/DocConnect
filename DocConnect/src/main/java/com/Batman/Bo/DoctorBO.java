package com.Batman.Bo;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class DoctorBO implements Serializable {

	private static final long serialVersionUID = 1L;
    
	private Integer docId;
	private String email;
	private String docName;
	private String docLocation;
	
}
