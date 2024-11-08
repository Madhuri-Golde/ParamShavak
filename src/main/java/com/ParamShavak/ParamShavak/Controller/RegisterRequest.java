//package com.ParamShavak.ParamShavak.Controller;
//
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class RegisterRequest {
//
//
//    private String firstname;
//    private String lastname;
//    private String email;
//    private String password;
//
//}



package com.ParamShavak.ParamShavak.Controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Annotation to automatically generate getters, setters, and other utility methods
@Data
// Annotation to generate a constructor with all fields
@AllArgsConstructor
// Annotation to generate a default constructor
@NoArgsConstructor
// Annotation to enable the builder pattern for creating instances of this class
@Builder
public class RegisterRequest {

    private String firstname;  // User's first name
    private String lastname;   // User's last name
    private String email;      // User's email address
    private String password;   // User's password for authentication
}
