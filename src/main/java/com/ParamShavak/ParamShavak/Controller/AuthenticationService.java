////
////package com.ParamShavak.ParamShavak.Controller;
////
////import com.ParamShavak.ParamShavak.Model.Role;
////import com.ParamShavak.ParamShavak.Model.User;
////import com.ParamShavak.ParamShavak.Repository.UserRepo;
////import com.ParamShavak.ParamShavak.config.JwtService;
////import lombok.RequiredArgsConstructor;
////import org.springframework.security.authentication.AuthenticationManager;
////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.stereotype.Service;
////
////@Service
////@RequiredArgsConstructor
////public class AuthenticationService {
////
////    private final UserRepo repository;
////    private final JwtService jwtService;
////    private final PasswordEncoder passwordEncoder;
////    private final AuthenticationManager authenticationManager;
////    private final UserDetailsService userDetailsService;
////
////    public AuthenticationResponse register(RegisterRequest request) {
////        var user = new User();
////        user.setFirstname(request.getFirstname());
////        user.setLastname(request.getLastname());
////        user.setEmail(request.getEmail());
////        user.setPassword(passwordEncoder.encode(request.getPassword()));
////        user.setRole(Role.USER);
////        repository.save(user);
////
////        // Use the user object, not the class name
////        var jwtToken = jwtService.generateToken(user);
////        return AuthenticationResponse.builder()
////                .token(jwtToken)
////                .build();
////    }
////
////    public AuthenticationResponse authenticate(AuthenticationRequest request) {
////        authenticationManager.authenticate(
////                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
////        );
////
////        var user = repository.findByEmail(request.getUsername())
////                .orElseThrow(() -> new RuntimeException("User not found"));
////
////        var jwtToken = jwtService.generateToken(user);
////        return AuthenticationResponse.builder()
////                .token(jwtToken)
////                .build();
////    }
////}
//package com.ParamShavak.ParamShavak.Controller;
//
//import com.ParamShavak.ParamShavak.Model.Role;
//import com.ParamShavak.ParamShavak.Model.User;
//import com.ParamShavak.ParamShavak.Repository.UserRepo;
//import com.ParamShavak.ParamShavak.config.JwtService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//public class AuthenticationService {
//
//    private final UserRepo repository;
//    private final JwtService jwtService;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//    private final UserDetailsService userDetailsService;
//
//    public AuthenticationResponse register(RegisterRequest request) {
//        var user = new User();
//        user.setFirstname(request.getFirstname());
//        user.setLastname(request.getLastname());
//        user.setEmail(request.getEmail());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setRole(Role.USER); // Set default role to USER
//        repository.save(user);
//
//        // Add role to the JWT claims
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("role", user.getRole().name());
//
//        var jwtToken = jwtService.generateToken(claims, user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }
//
//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
//        );
//
//        var user = repository.findByEmail(request.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Add role to the JWT claims
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("role", user.getRole().name());
//
//        var jwtToken = jwtService.generateToken(claims, user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }
//}
//
//package com.ParamShavak.ParamShavak.Controller;
//
//import com.ParamShavak.ParamShavak.Model.Role;
//import com.ParamShavak.ParamShavak.Model.User;
//import com.ParamShavak.ParamShavak.Repository.UserRepo;
//import com.ParamShavak.ParamShavak.config.JwtService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//public class AuthenticationService {
//
//    private final UserRepo repository;
//    private final JwtService jwtService;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//    private final UserDetailsService userDetailsService;
//
//    // Register a new user
//    public AuthenticationResponse register(RegisterRequest request) {
//        var user = new User();
//        user.setFirstname(request.getFirstname());
//        user.setLastname(request.getLastname());
//        user.setEmail(request.getEmail());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setRole(Role.USER); // Set default role to USER
//        repository.save(user);
//
//        // Prepare claims including the role
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("role", user.getRole().name());
//
//        // Generate JWT token with claims
//        var jwtToken = jwtService.generateToken(claims, user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }
//
//    // Authenticate a user
//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        // Authenticate user
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
//        );
//
//        // Fetch user details from repository
//        var user = repository.findByEmail(request.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Prepare claims including the role
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("role", user.getRole().name());
//
//        // Generate JWT token with claims
//        var jwtToken = jwtService.generateToken(claims, user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }
//}











package com.ParamShavak.ParamShavak.Controller;

import com.ParamShavak.ParamShavak.Model.Role;
import com.ParamShavak.ParamShavak.Model.User;
import com.ParamShavak.ParamShavak.Repository.UserRepo;
import com.ParamShavak.ParamShavak.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service  // Indicates that this class is a service component in the Spring context
@RequiredArgsConstructor  // Generates a constructor with required arguments (final fields)
public class AuthenticationService {

    private final UserRepo repository;  // Repository for user data access
    private final JwtService jwtService;  // Service for generating and validating JWT tokens
    private final PasswordEncoder passwordEncoder;  // Encoder for password hashing
    private final AuthenticationManager authenticationManager;  // Authentication manager for processing authentication requests
    private final UserDetailsService userDetailsService;  // Service for loading user-specific data

    // Register a new user
    public AuthenticationResponse register(RegisterRequest request) {
        var user = new User();  // Create a new User instance
        user.setFirstname(request.getFirstname());  // Set user's first name
        user.setLastname(request.getLastname());  // Set user's last name
        user.setEmail(request.getEmail());  // Set user's email address
        user.setPassword(passwordEncoder.encode(request.getPassword()));  // Encode and set user's password
        user.setRole(Role.USER);  // Set default role to USER
        repository.save(user);  // Save the user to the database

        // Prepare claims including the role for the JWT token
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());  // Add the user's role as a claim

        // Generate JWT token with claims using the JwtService
        var jwtToken = jwtService.generateToken(claims, user);
        return AuthenticationResponse.builder()  // Build and return an AuthenticationResponse containing the JWT token
                .token(jwtToken)
                .build();
    }

    // Authenticate a user
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // Authenticate user using the AuthenticationManager
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // Fetch user details from the repository using the provided username
        var user = repository.findByEmail(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));  // Throw an exception if the user is not found

        // Prepare claims including the role for the JWT token
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());  // Add the user's role as a claim

        // Generate JWT token with claims using the JwtService
        var jwtToken = jwtService.generateToken(claims, user);
        return AuthenticationResponse.builder()  // Build and return an AuthenticationResponse containing the JWT token
                .token(jwtToken)
                .build();
    }
}
