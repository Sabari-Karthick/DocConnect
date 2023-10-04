package com.Batman.services;

import com.Batman.dto.Patient;

public interface IDocconnectPatientMailService {
       void sendRegisterMail(Patient patient) throws Exception;
}
