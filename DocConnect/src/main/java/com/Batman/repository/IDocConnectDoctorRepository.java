package com.Batman.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Batman.dto.Doctor;

public interface IDocConnectDoctorRepository extends JpaRepository<Doctor, Integer> {
  List<Doctor> findByDocLocation(String location);
  List<Doctor> findByDocCity(String city);
  List<Doctor> findByDocSpecialization (String specialization);
  Doctor findByEmail(String mail);
}
