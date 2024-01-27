package com.Batman.dto.appointment;

import java.time.LocalDate;
import java.time.LocalTime;

import com.Batman.enums.AppointmentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import lombok.Data;


@Data
public class AppointmentResponse {
	private Integer id;
	private String doc_name;
	private String location;
	private String patientName;
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
	@JsonSerialize(using = LocalTimeSerializer.class)
	@JsonFormat(pattern = "HH:mm:ss")
	private LocalTime startTime;
	private AppointmentStatus status;
	private Long slotToken;

}
