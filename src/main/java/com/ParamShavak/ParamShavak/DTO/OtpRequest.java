package com.ParamShavak.ParamShavak.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OtpRequest {
    private String type; // email or mobile
    private String value; // email or mobile number
    private String otp; // OTP code

    // Getters and Setters
}
