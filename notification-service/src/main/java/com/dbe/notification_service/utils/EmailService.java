package com.dbe.notification_service.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${notification.admin.email}")
    private String to;

    @Value("${spring.mail.username}")
    private String from;

    public void send(String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject("New Enquiry Notification");
        msg.setText(body);
        msg.setFrom(from);
        mailSender.send(msg);
    }
}
