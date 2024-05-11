package com.Batman.dto.appointment;


import java.time.LocalDate;

import com.Batman.enums.AppointmentStatus;
import com.Batman.enums.TimeSlots;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Data;
//Appointment Request
@Data
public class AppointmentRequest {
	
	private String patientName;
    private String phone;
    private Integer doc_id;
    private Integer booking_user_id;
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private TimeSlots slot;
    private AppointmentStatus status = AppointmentStatus.INITIATED;
	

}
