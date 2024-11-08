package com.ParamShavak.ParamShavak.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmsConfig {

    @Value("${sms.twilio.accountSid}")
    private String accountSid;

    @Value("${sms.twilio.authToken}")
    private String authToken;

    @Value("${sms.twilio.phoneNumber}")
    private String phoneNumber;

    public String getAccountSid() {
        return accountSid;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
