package com.Batman.services;



import java.util.List;

import com.Batman.dto.Doctor;

public interface IDocconnectDoctorService {
       Doctor registerDoctor(Doctor doctor);
       Doctor searchDoctorByID(Integer ID);
       List<Doctor> searchAllDcotors();
       List<Doctor> searchDoctorByLocation(String location);
       List<Doctor> searchDoctorByCity(String city);
       List<Doctor> searchDoctorBySpecialization(String specialization);
       Doctor updateDoctor(Doctor doctor);
       String deleteDoctor(Doctor doctor);
       
}
