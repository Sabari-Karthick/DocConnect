package com.Batman.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.Batman.dto.Patient;

@Service
public class DocconnectPatientMailServiceImpl implements IDocconnectPatientMailService{

	
	
	@Autowired
	private JavaMailSender sender;

	@Value("${spring.mail.username}")
	private String fromEmail;
	
	
	@Override
	public void sendRegisterMail(Patient patient) throws Exception {
		
		String message = "Thank You "+ patient.getPatientName()+",SuccessFully Registered With ID:: "+patient.getPatientID();
		sendMail(message, patient.getPatientMail(),"Registration Success Mail");
		
	}
	private void sendMail(String msg, String toMail,String subject) throws MessagingException {
		MimeMessage mimeMessage = sender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
		mimeMessageHelper.setCc(toMail);
		mimeMessageHelper.setFrom(fromEmail);
		mimeMessageHelper.setSubject(subject);
		mimeMessageHelper.setText(msg);
		sender.send(mimeMessage);
		
		
		

	}

}
