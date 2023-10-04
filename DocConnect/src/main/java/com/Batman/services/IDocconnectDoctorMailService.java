package com.Batman.services;

import com.Batman.dto.Doctor;

public interface IDocconnectDoctorMailService {
    void sendRegisterMail(Doctor  doctor) throws Exception;
    void accountDeletedMail(Doctor doctor)throws Exception;
    
}
