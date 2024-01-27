package com.Batman.service;


import java.util.List;

import com.Batman.dto.doctor.DoctorResponse;
import com.Batman.enums.MedicalSpecialty;

public interface IDoctorService {
      
	<T> DoctorResponse getDoctorInfo(T idOrEmail);
	List<DoctorResponse> getAllDoctorsByLocation(String location);
	List<DoctorResponse> getAllDoctorsBySpeciality(MedicalSpecialty specialty);
}
