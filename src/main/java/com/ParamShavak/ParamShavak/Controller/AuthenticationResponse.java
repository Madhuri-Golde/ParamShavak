//package com.ParamShavak.ParamShavak.Controller;
//
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
//public class AuthenticationResponse {
//
//
//    private String token;
//}



package com.ParamShavak.ParamShavak.Controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@Builder  // Lombok annotation to provide a builder pattern for creating instances of this class
@AllArgsConstructor  // Lombok annotation to generate a constructor with all fields as parameters
@NoArgsConstructor  // Lombok annotation to generate a default no-argument constructor
public class AuthenticationResponse {

    // Field to hold the JWT token to be sent as a response after authentication
    private String token;
}
