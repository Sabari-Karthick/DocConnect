package com.Batman.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.Batman.dto.Doctor;

@Service
public class DocconnectDoctorMailServiceImpl implements IDocconnectDoctorMailService {

	@Autowired
	private JavaMailSender sender;

	@Value("${spring.mail.username}")
	private String fromEmail;

	@Override
	public void sendRegisterMail(Doctor doctor) throws Exception{
			String message = "Thank You "+ doctor.getDocName()+",SuccessFully Registered With ID:: "+doctor.getDocID();
			sendMail(message, doctor.getEmail(),"Registration Success Mail");
	}

	
	@Override
	public void accountDeletedMail(Doctor doctor) throws Exception {
		String message = "Thank You "+ doctor.getDocName()+",Your Account  Registered With ID:: "+doctor.getDocID()+" is deleted Successfully";
		sendMail(message, doctor.getEmail(),"Account Removed Mail");
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
