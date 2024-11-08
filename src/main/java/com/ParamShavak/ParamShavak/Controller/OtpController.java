package com.ParamShavak.ParamShavak.Controller;

import com.ParamShavak.ParamShavak.DTO.OtpRequest;
import com.ParamShavak.ParamShavak.Services.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OtpController {

    @Autowired
    private OtpService otpService;



    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody OtpRequest otpRequest) {
        System.out.println("Sending otp request: " + otpRequest);
        otpService.sendOtp(otpRequest.getType(), otpRequest.getValue());
        return ResponseEntity.ok("OTP sent");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpRequest otpRequest) {
        System.out.println("Received request: " + otpRequest);

        boolean isValid = otpService.verifyOtp(otpRequest.getType(), otpRequest.getValue(), otpRequest.getOtp());

        if (isValid) {
            return ResponseEntity.ok("OTP verified successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired OTP");
        }
    }


}
