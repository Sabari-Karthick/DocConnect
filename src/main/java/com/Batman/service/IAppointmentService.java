package com.Batman.service;

import java.util.List;

import com.Batman.dto.appointment.AppointmentRequest;
import com.Batman.dto.appointment.AppointmentResponse;
import com.Batman.enums.AppointmentStatus;

public interface IAppointmentService {
     AppointmentResponse bookAppointment(AppointmentRequest request);
     List<AppointmentResponse> searchAllAppointmentsByDocandStatus(Integer doc_id,AppointmentStatus status);
     List<AppointmentResponse> searchAllAppointmentsByUserandStatus(Integer bookedByUserID,AppointmentStatus status);
     String updateAppointmentStatus(Integer appointmentID,AppointmentStatus status);
}
