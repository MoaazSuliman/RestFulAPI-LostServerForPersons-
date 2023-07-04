package com.moaaz.lost.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMessage(String text, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("t3195538@gmail.com");
        message.setTo(email);
        message.setText(text);
        message.setSubject("From Lost Server API That Had Created By Engineer Moaaz...");
        javaMailSender.send(message);
    }
}
