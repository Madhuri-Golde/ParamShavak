//package com.ParamShavak.ParamShavak.Controller;
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
//public class AuthenticationRequest {
//
//    private String username;
//    private String password;
//}



package com.ParamShavak.ParamShavak.Controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // Lombok annotation to generate getter, setter, toString, equals, and hashCode methods
@Builder  // Lombok annotation to provide a builder pattern for creating instances of this class
@AllArgsConstructor  // Lombok annotation to generate a constructor with all fields as parameters
@NoArgsConstructor  // Lombok annotation to generate a default no-argument constructor
public class AuthenticationRequest {

    // Field to hold the username (email or other identifier) for authentication
    private String username;

    // Field to hold the password for authentication
    private String password;
}
