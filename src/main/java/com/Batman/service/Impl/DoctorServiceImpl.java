package com.Batman.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author karthick 
 * @date 28/10/2023
 * 
 * 
 */
import org.springframework.stereotype.Service;

import com.Batman.dto.doctor.DoctorResponse;
import com.Batman.enums.MedicalSpecialty;
import com.Batman.exceptions.DetailsNotFoundException;
import com.Batman.exceptions.UserNotFoundException;
import com.Batman.mapper.CommonMapper;
import com.Batman.model.DoctorEntity;
import com.Batman.repository.DoctorRepository;
import com.Batman.service.IDoctorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements IDoctorService {

	private final DoctorRepository doctorRepository;

	private final CommonMapper mapper;

	@Override
	public <T> DoctorResponse getDoctorInfo(T idOrEmail) {
		DoctorEntity doctorEntity;
		if (idOrEmail.getClass().isInstance(String.class)) {
			doctorEntity = doctorRepository.findByUserEmail(idOrEmail.toString())
					.orElseThrow(() -> new UserNotFoundException("No User Available With this mail"));
		} else {
			doctorEntity = doctorRepository.findById((Integer) idOrEmail)
					.orElseThrow(() -> new UserNotFoundException("No User Available With this ID"));
		}
		DoctorResponse doctorResponse = mapper.convertToResponse(doctorEntity, DoctorResponse.class);
		doctorResponse.setName(doctorEntity.getUser().getName());
		doctorResponse.setEmail(doctorEntity.getUser().getEmail());
		return doctorResponse;
	}

	@Override
	public List<DoctorResponse> getAllDoctorsByLocation(String location) {
		List<DoctorEntity> doctorsList = doctorRepository.findAllByLocation(location);
		if (doctorsList.isEmpty())
			throw new DetailsNotFoundException("No doctors found in the location specified");
		return doctorsList.stream().map(doctor -> {
			DoctorResponse docResponse = mapper.convertToResponse(doctor, DoctorResponse.class);
			docResponse.setEmail(doctor.getUser().getEmail());
			docResponse.setName(doctor.getUser().getName());
			return docResponse;
		}).collect(Collectors.toList());
	}

	@Override
	public List<DoctorResponse> getAllDoctorsBySpeciality(MedicalSpecialty specialty) {
		List<DoctorEntity> doctorsList = doctorRepository.findAllBySpecialty(specialty);
		if (doctorsList.isEmpty())
			throw new DetailsNotFoundException("No doctors found by the speciality specified");
		return doctorsList.stream().map(doctor -> {
			DoctorResponse docResponse = mapper.convertToResponse(doctor, DoctorResponse.class);
			docResponse.setEmail(doctor.getUser().getEmail());
			docResponse.setName(doctor.getUser().getName());
			return docResponse;
		}).collect(Collectors.toList());
	}

}
