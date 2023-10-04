package com.Batman.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.Batman.services.IDocconnectAppointmentService;

@Configuration
@EnableScheduling
public class SchedulerConfig {
	
	@Autowired
	private IDocconnectAppointmentService appointmentService;
	
	
	
	@Scheduled(cron = "0 0 */12 * * *")
	public void closeAppointments() {
		appointmentService.closeAllAppointments();
	}
	

}
