//package com.ParamShavak.ParamShavak.Services;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class EmailService {
//
//    private final JavaMailSender mailSender;
//
//    @Autowired
//    public EmailService(JavaMailSender mailSender) {
//        this.mailSender = mailSender;
//    }
//
//    public void sendEmail(String to, String subject, String body) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(body);
//        message.setFrom("hvaibhav@cdac.in");
//
//        mailSender.send(message);
//    }
//}
//




package com.ParamShavak.ParamShavak.Services;

import org.springframework.beans.factory.annotation.Autowired; // For dependency injection
import org.springframework.mail.SimpleMailMessage; // For creating simple mail messages
import org.springframework.mail.javamail.JavaMailSender; // Interface for sending emails
import org.springframework.stereotype.Service; // Indicates that this class is a service

@Service // Marks this class as a service component in the Spring context
public class EmailService {

    private final JavaMailSender mailSender; // Mail sender to send emails

    // Constructor injection for the JavaMailSender
    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender; // Assign the mailSender instance
    }

    // Method to send an email
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage(); // Create a new mail message
        message.setTo(to); // Set the recipient's email address
        message.setSubject(subject); // Set the subject of the email
        message.setText(body); // Set the body text of the email
        message.setFrom("hvaibhav@cdac.in"); // Set the sender's email address

        mailSender.send(message); // Send the email
    }
}
