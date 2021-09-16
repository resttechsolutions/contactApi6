package com.resttechsolutions.contactapi6.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender mailSenderService;

    public MailService(JavaMailSender mailSenderService) {
        this.mailSenderService = mailSenderService;
    }

    public void sendMailNotification(String to, String subject, String message){
        SimpleMailMessage smm = new SimpleMailMessage();

        smm.setFrom("rafaelalfonso82@gmail.com");
        smm.setTo(to);
        smm.setSubject(subject);
        smm.setText(message);

        this.mailSenderService.send(smm);
    }
}
