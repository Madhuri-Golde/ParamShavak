package com.ParamShavak.ParamShavak.Services;

import com.ParamShavak.ParamShavak.Model.Otp;
import com.ParamShavak.ParamShavak.Repository.OtpRepository;
import com.ParamShavak.ParamShavak.config.SmsConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.lookups.v1.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SmsConfig smsConfig;

    private static final int OTP_LENGTH = 6;
    private static final long OTP_VALIDITY_DURATION_MINUTES = 5;

    public String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // 6-digit OTP
        return String.valueOf(otp);
    }

    public void sendOtp(String type, String value) {
        String otp = generateOtp();
        Otp otpEntity = new Otp();
        otpEntity.setType(type);
        otpEntity.setValue(value);
        otpEntity.setOtp(otp);
        otpEntity.setCreationTime(Instant.now());
        otpRepository.save(otpEntity);

        try {
            if (type.equals("email")) {
                sendEmailOtp(value, otp);
            } else if (type.equals("mobile")) {
                sendSmsOtp(value, otp);
            } else {
                throw new IllegalArgumentException("Unsupported OTP type: " + type);
            }
        } catch (Exception e) {
            // Handle or log the exception (e.g., send alert or log to file)
            e.printStackTrace();
        }
    }

    private void sendEmailOtp(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP code is: " + otp);
        mailSender.send(message);
    }

    private void sendSmsOtp(String mobile, String otp) {
//        Twilio.init(smsConfig.getAccountSid(), smsConfig.getAuthToken());
//        Message message = Message.creator(
//                new PhoneNumber(mobile), // To number
//                new PhoneNumber(smsConfig.getPhoneNumber()), // From number
//                "Your OTP code is: " + otp
//        ).create();
//        System.out.println("SMS sent: " + message.getSid());
    }

    public boolean verifyOtp(String type, String value, String otp) {
        Optional<Otp> otpEntityOpt = otpRepository.findFirstByTypeAndValue(type, value);
        if (otpEntityOpt.isPresent()) {
            Otp otpEntity = otpEntityOpt.get();
            Instant otpCreationTime = otpEntity.getCreationTime();
            Instant expirationTime = otpCreationTime.plus(Duration.ofMinutes(OTP_VALIDITY_DURATION_MINUTES));

            return otpEntity.getOtp().equals(otp) && Instant.now().isBefore(expirationTime);
        }
        return false;
    }
}
