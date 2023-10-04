package com.Batman.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.Batman.dto.Appoinment;
import com.Batman.exception.ProcessingException;

@Service
public class DocconnectAppoinmentMailServiceImpl implements IDocconnectAppointmentMailService{

	
	
	@Autowired
	private JavaMailSender sender;

	@Value("${spring.mail.username}")
	private String fromEmail;
	
	
	private void sendMail(String msg, String toMails,String subject) {
		MimeMessage mimeMessage = sender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
			mimeMessageHelper.setCc(toMails);
			mimeMessageHelper.setFrom(fromEmail);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(msg);
		} catch (MessagingException e) {
			throw new ProcessingException("Error While Generating Mail");
		}
		sender.send(mimeMessage);
		
		
		

	}


	@Override
	public void sendNotificationMail(Appoinment appoinment){
		String notificationMessage="Your Appointment ::"+appoinment.getAppointmnetID()+" Has Been Initiated On "+ appoinment.getAppointmentDate()+"at "+appoinment.getAppointmentTime() +" With Doctor "+appoinment.getDoctor().getDocName()+" By Patient "+appoinment.getPatient().getPatientName()+"  Will Be Confirmed Soon";
		sendMail(notificationMessage, appoinment.getPatient().getPatientMail(), "Appointment Request SuccessFully Sent");
		String doctorMessage = "New Appointment Request ::"+appoinment.getAppointmnetID()+" has requested on"+ appoinment.getAppointmentDate()+"at "+appoinment.getAppointmentTime() +" By a patient " +appoinment.getPatient().getPatientName()+ "\nPlease click the  below link to view and confirm the appointment "+"http://localhost:9999/DocConnect/api/appointment/fetch/doctor/"+appoinment.getDoctor().getDocId();
		sendMail(doctorMessage, appoinment.getDoctor().getEmail(), "Appointment Request");
	}


	@Override
	public void sendConfirmationMail(Appoinment appoinment) {
		String confirmationMessage="Your Appointment ::"+appoinment.getAppointmnetID()+" Has Been Confirmed On "+ appoinment.getAppointmentDate()+"at "+appoinment.getAppointmentTime() +" With Doctor "+appoinment.getDoctor().getDocName()+" By Patient "+appoinment.getPatient().getPatientName()+"  is Confirmed";
		sendMail(confirmationMessage, appoinment.getPatient().getPatientMail(), "Appointment Confirmd");
		
	}


	@Override
	public void sendStatusMail(Appoinment appoinment) {
		
	}

}
