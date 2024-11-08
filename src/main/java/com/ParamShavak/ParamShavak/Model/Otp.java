package com.ParamShavak.ParamShavak.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "otps")
public class Otp {


    @Id
    private String id;
    private String type; // email or mobile
    private String value; // email or mobile number
    private String otp; // OTP code
    private Instant creationTime;


    // Getters and Setters


}
