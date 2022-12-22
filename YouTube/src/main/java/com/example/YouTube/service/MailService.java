package com.example.YouTube.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;//buni propertiga yozganimizdan springni o`zi create qiladi

    void sendEmail(String toAccount, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toAccount);
        msg.setFrom("aismatulleyev@mail.ru");
        msg.setSubject(subject);
        msg.setText(text);
        javaMailSender.send(msg);
    }
}
