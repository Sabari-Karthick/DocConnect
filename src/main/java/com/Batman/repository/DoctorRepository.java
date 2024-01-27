package com.Batman.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Batman.enums.MedicalSpecialty;
import com.Batman.model.DoctorEntity;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Integer> {
	Optional<DoctorEntity> findByUserEmail(String mail);
	List<DoctorEntity> findAllByLocation(String location);
	List<DoctorEntity> findAllBySpecialty(MedicalSpecialty specialty);
}
