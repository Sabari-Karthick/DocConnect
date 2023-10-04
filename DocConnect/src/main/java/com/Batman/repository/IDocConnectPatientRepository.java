package com.Batman.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Batman.dto.Patient;

public interface IDocConnectPatientRepository extends JpaRepository<Patient, Integer> {

}
