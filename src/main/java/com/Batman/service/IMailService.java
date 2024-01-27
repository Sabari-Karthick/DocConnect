package com.Batman.service;
/**
 * 
 * @author _karthick
 * 
 * @date 4/11/2023
 * 
 * 
 *
 */
public interface IMailService {
    void sendMail(String fromMail,String subject,String ...toMail);
}
