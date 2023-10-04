package com.Batman.services;

import com.Batman.dto.Appoinment;

public interface IDocconnectAppointmentMailService {
       void sendNotificationMail(Appoinment appoinment) ;
       void sendConfirmationMail(Appoinment appoinment) ;
       void sendStatusMail(Appoinment appoinment) ;
}
