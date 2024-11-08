//package com.ParamShavak.ParamShavak.DTO;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class LoginRequest {
//
//    private String email;
//    private String password;
//}



package com.ParamShavak.ParamShavak.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Data Transfer Object for login requests
@Data
@Builder // Lombok annotation to implement the Builder pattern
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
public class LoginRequest {

    private String email; // User's email for login
    private String password; // User's password for login
}
