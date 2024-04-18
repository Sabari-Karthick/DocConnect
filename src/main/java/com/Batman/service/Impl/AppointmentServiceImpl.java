package com.Batman.service.Impl;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Batman.dto.appointment.AppointmentRequest;
import com.Batman.dto.appointment.AppointmentResponse;
import com.Batman.enums.AppointmentStatus;
import com.Batman.exceptions.DetailsNotFoundException;
import com.Batman.exceptions.SlotNotAvailableException;
import com.Batman.exceptions.UserNotFoundException;
import com.Batman.mapper.CommonMapper;
import com.Batman.model.AppointmentEntity;
import com.Batman.repository.AppointmentRepository;
import com.Batman.repository.DoctorRepository;
import com.Batman.repository.UserRepository;
import com.Batman.service.IAppointmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements IAppointmentService {

	private final AppointmentRepository appointmentRepository;

	private final DoctorRepository doctorRepository;

	private final UserRepository userRepository;

	private final CommonMapper mapper;

	@Override
	@Transactional
	public synchronized AppointmentResponse bookAppointment(AppointmentRequest request) {
		long count = appointmentRepository.countByDateAndStartTimeAndDoctorDocID(request.getDate(), request.getSlot().getStartTime(),request.getDoc_id());
		if (count == 4)
			throw new SlotNotAvailableException("The Slot requested is not available");

		AppointmentEntity appointmentEntity = mapper.convertToEntity(request, AppointmentEntity.class);
		appointmentEntity.setDoctor(doctorRepository.findById(request.getDoc_id())
				.orElseThrow(() -> new DetailsNotFoundException("No Doctor available with the ID provide")));
		appointmentEntity.setBookedBy(userRepository.findById(request.getBooking_user_id())
				.orElseThrow(() -> new UserNotFoundException("No user available with the ID Provided")));
		appointmentEntity.setStartTime(request.getSlot().getStartTime());
		appointmentEntity.setEndTime(request.getSlot().getEndTime());
		appointmentEntity.setSlotToken(count + 1);

		AppointmentEntity appointment = appointmentRepository.save(appointmentEntity);
		AppointmentResponse appointmentResponse = mapper.convertToResponse(appointment, AppointmentResponse.class);
		appointmentResponse.setDoc_name(appointment.getDoctor().getUser().getName());
		appointmentResponse.setLocation(appointment.getDoctor().getLocation());
		return appointmentResponse;
	}

	@Override
	public List<AppointmentResponse> searchAllAppointmentsByDocandStatus(Integer doc_id,AppointmentStatus status) {
		List<AppointmentEntity> docAppointments = appointmentRepository.findByDoctorDocIDAndStatus(doc_id, status);
		if (docAppointments.isEmpty())
			throw new DetailsNotFoundException("No Appointment is Booked For this Doctor");
		return docAppointments.stream().map(app -> {
			AppointmentResponse appointmentResponse = mapper.convertToResponse(app, AppointmentResponse.class);
			appointmentResponse.setDoc_name(app.getDoctor().getUser().getName());
			appointmentResponse.setLocation(app.getDoctor().getLocation());

			return appointmentResponse;
		}).collect(Collectors.toList());

	}



	@Override
	@Transactional
	public String updateAppointmentStatus(Integer appointmentID, AppointmentStatus status) {
		AppointmentEntity appointmentEntity = appointmentRepository.findById(appointmentID).orElseThrow(()-> new DetailsNotFoundException("No appointment is available with this ID"));
		appointmentEntity.setStatus(status);
		appointmentRepository.save(appointmentEntity);
		return "Successfully Updated";
	}

	@Override
	public List<AppointmentResponse> searchAllAppointmentsByUserandStatus(Integer bookedByUserID,
			AppointmentStatus status) {
		List<AppointmentEntity> appointments = appointmentRepository.findByBookedByIdAndStatus(bookedByUserID, status);
		if (appointments.isEmpty())
			throw new DetailsNotFoundException("No Appointment is Booked by this User");
		
		return appointments.stream().map(appointment ->{
			AppointmentResponse appointmentResponse = mapper.convertToResponse(appointment, AppointmentResponse.class);
			appointmentResponse.setDoc_name(appointment.getDoctor().getUser().getName());
			appointmentResponse.setLocation(appointment.getDoctor().getLocation());
			return appointmentResponse;
		}).collect(Collectors.toList());
	}

}
