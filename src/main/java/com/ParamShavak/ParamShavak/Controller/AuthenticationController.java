//
//package com.ParamShavak.ParamShavak.Controller;
//
//import com.ParamShavak.ParamShavak.DTO.LoginRequest;
//
//import com.ParamShavak.ParamShavak.Services.UserService;
//
//import com.ParamShavak.ParamShavak.config.JwtService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import com.ParamShavak.ParamShavak.Model.User;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/v1/auth")
//@RequiredArgsConstructor
//public class AuthenticationController {
//
//    private final AuthenticationService service;
//    private final UserService userService;
//    private final JwtService jwtService;
//
//    @PostMapping("/register")
//    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
//        return ResponseEntity.ok(service.register(request));
//    }
//
//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
//        return ResponseEntity.ok(service.authenticate(request));
//    }
//
//    @GetMapping("/sampleGetMapping")
//    public String sampleGetMapping() {
//        return "sampleGetMapping";
//    }
////    @PostMapping("/login")
////    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
////        boolean isAuthenticated = userService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
////        if (isAuthenticated) {
////            // Generate JWT token
////            String token = jwtService.generateToken(loginRequest.getEmail());
////
////            // Create response map
////            Map<String, String> response = new HashMap<>();
////            response.put("token", token);
////
////            // Return the response with the token
////            return ResponseEntity.ok(response);
////        } else {
////            // Return unauthorized response
////            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // Changed to null to match ResponseEntity<Map<String, String>>
////        }
////    }
//
//    @PostMapping("/login")
//    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
//        // Authenticate the user
//        User user = userService.authenticateAndGetUser(loginRequest.getEmail(), loginRequest.getPassword());
//
//        if (user != null) {
//            // Generate JWT token
//            String token = jwtService.generateToken(user.getEmail());
//
//            // Create response map
//            Map<String, String> response = new HashMap<>();
//            response.put("token", token);
//            response.put("role", user.getRole().toString()); // Add role to response
//
//            // Return the response with the token and role
//            return ResponseEntity.ok(response);
//        } else {
//            // Return unauthorized response
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // Changed to null to match ResponseEntity<Map<String, String>>
//        }
//    }
//
//
//}

//
//package com.ParamShavak.ParamShavak.Controller;
//
//import com.ParamShavak.ParamShavak.DTO.LoginRequest;
//import com.ParamShavak.ParamShavak.Services.UserService;
//import com.ParamShavak.ParamShavak.config.JwtService;
//import com.ParamShavak.ParamShavak.Model.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/v1/auth")
//@RequiredArgsConstructor
//public class AuthenticationController {
//
//    private final AuthenticationService service;
//    private final UserService userService;
//    private final JwtService jwtService;
//
//    @PostMapping("/register")
//    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
//        return ResponseEntity.ok(service.register(request));
//    }
//
//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
//        return ResponseEntity.ok(service.authenticate(request));
//    }
//
//    @GetMapping("/sampleGetMapping")
//    public String sampleGetMapping() {
//        return "sampleGetMapping";
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
//        // Authenticate the user and get the User object
//        User user = userService.authenticateAndGetUser(loginRequest.getEmail(), loginRequest.getPassword());
//
//        if (user != null) {
//            // Generate JWT token with role
//            String token = jwtService.generateToken(user, user.getRole().toString());
//
//            // Create response map
//            Map<String, String> response = new HashMap<>();
//            response.put("token", token);
//            response.put("role", user.getRole().toString()); // Add role to response
//
//            // Return the response with the token and role
//            return ResponseEntity.ok(response);
//        } else {
//            // Return unauthorized response with a meaningful message
//            Map<String, String> errorResponse = new HashMap<>();
//            errorResponse.put("error", "Invalid credentials");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
//        }
//    }
//}



package com.ParamShavak.ParamShavak.Controller;

import com.ParamShavak.ParamShavak.DTO.LoginRequest;
import com.ParamShavak.ParamShavak.Services.UserService;
import com.ParamShavak.ParamShavak.config.JwtService;
import com.ParamShavak.ParamShavak.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController  // Marks this class as a REST controller for handling HTTP requests
@RequestMapping("/api/v1/auth")  // Base URL for all authentication-related endpoints
@RequiredArgsConstructor  // Lombok annotation to generate a constructor for final fields
public class AuthenticationController {

    // Injected service dependencies
    private final AuthenticationService service;  // Service to handle registration and authentication logic
    private final UserService userService;  // Service to handle user-related operations like authentication
    private final JwtService jwtService;  // Service to generate and validate JWT tokens

    // Endpoint to handle user registration
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        // Calls the registration logic and returns a response entity with the registration result
        return ResponseEntity.ok(service.register(request));
    }

    // Endpoint to handle user authentication
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        // Calls the authentication logic and returns a response entity with the authentication result
        return ResponseEntity.ok(service.authenticate(request));
    }

    // Sample GET mapping to test or demonstrate the API
    @GetMapping("/sampleGetMapping")
    public String sampleGetMapping() {
        return "sampleGetMapping";  // Returns a simple string as a response for demonstration purposes
    }

    // Endpoint for user login that accepts email and password and returns JWT token with user role
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        // Authenticate the user using the email and password from the login request
        User user = userService.authenticateAndGetUser(loginRequest.getEmail(), loginRequest.getPassword());

        // If the authentication is successful and a valid user is found
        if (user != null) {
            // Generate a JWT token for the authenticated user, including the user's role in the token claims
            String token = jwtService.generateToken(user, user.getRole().toString());

            // Create a map to store the response data (token and role)
            Map<String, String> response = new HashMap<>();
            response.put("token", token);  // Add the generated JWT token to the response
            response.put("role", user.getRole().toString());  // Add the user's role to the response

            // Return the response entity with the JWT token and user role (HTTP status 200 OK)
            return ResponseEntity.ok(response);
        } else {
            // If authentication fails, create an error response with an "Invalid credentials" message
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid credentials");

            // Return the error response with an HTTP status of 401 (Unauthorized)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }
}
