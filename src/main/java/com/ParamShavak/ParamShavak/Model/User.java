//package com.ParamShavak.ParamShavak.Model;
//
//import com.ParamShavak.ParamShavak.DTO.QuotationRequestDTO;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.Size;
//import lombok.*;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@Document
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
//@Getter
//@Setter
//public class User implements UserDetails {
//
//    private List<Enquiry> enquiries = new ArrayList<>();
//
//    @Id
//    private String id;
//
//    @NotEmpty
//    @Size(min=4, message = "firstname must be of min 4 characters")
//    private String firstname;
//
//    @NotEmpty
//    @Size(min=3, message = "lastname must be of min 3 characters")
//    private String lastname;
//
//    @Email(message = "Email Address is not valid")
//    private String email;
//
//    @NotEmpty(message = "select the gender")
//    private String gender;
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    private LocalDate dateOfBirth;
//
//    @NotEmpty(message = "Institute Name is mandatory")
//    private String instituteName;
//
//    @NotEmpty(message = "ContactNumber is mandatory")
//    private String contactNumber;
//
//    @NotEmpty(message = "Country is mandatory")
//    private String country;
//
//    @NotEmpty(message = "State is mandatory")
//    private String state;
//
//    @NotEmpty(message = "City is mandatory")
//    private String city;
//
//    @NotEmpty(message = "Pincode is mandatory")
//    private String pincode;
//
//    @NotEmpty
//    @Size(min =6, message = "password must be min of 6 characters")
//    private String password;
//
//
//    private Role role = Role.USER;;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(getUsername()));
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
//    }
//
//    // Add to User.java
//
//
//
//}



package com.ParamShavak.ParamShavak.Model;

import com.ParamShavak.ParamShavak.DTO.QuotationRequestDTO; // Importing DTO for quotations (not used in this class)
import com.fasterxml.jackson.annotation.JsonFormat; // For formatting JSON date fields
import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // To ignore unknown properties during deserialization
import jakarta.validation.constraints.Email; // For email validation
import jakarta.validation.constraints.NotEmpty; // For non-empty field validation
import jakarta.validation.constraints.Size; // For size validation
import lombok.*; // For Lombok annotations
import org.springframework.data.annotation.Id; // For MongoDB ID annotation
import org.springframework.data.mongodb.core.mapping.Document; // For MongoDB document mapping
import org.springframework.security.core.GrantedAuthority; // For granting authorities in Spring Security
import org.springframework.security.core.authority.SimpleGrantedAuthority; // For simple granted authority
import org.springframework.security.core.userdetails.UserDetails; // Interface for Spring Security user details

import java.time.LocalDate; // For date representation
import java.util.ArrayList; // For creating a list of enquiries
import java.util.Collection; // For collection of granted authorities
import java.util.List; // For using lists

@Document // Indicates that this class is a MongoDB document
@Data // Lombok annotation to generate getters, setters, toString, etc.
@NoArgsConstructor // Lombok annotation for no-args constructor
@AllArgsConstructor // Lombok annotation for all-args constructor
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unknown properties in JSON
@Getter // Lombok annotation for generating getter methods
@Setter // Lombok annotation for generating setter methods
public class User implements UserDetails { // Implements UserDetails for Spring Security

    private List<Enquiry> enquiries = new ArrayList<>(); // List to hold user's enquiries

    @Id // MongoDB ID annotation
    private String id; // User ID

    @NotEmpty // Validation annotation for non-empty fields
    @Size(min = 4, message = "firstname must be of min 4 characters") // Validation for size
    private String firstname; // User's first name

    @NotEmpty // Validation annotation for non-empty fields
    @Size(min = 3, message = "lastname must be of min 3 characters") // Validation for size
    private String lastname; // User's last name

    @Email(message = "Email Address is not valid") // Email validation
    private String email; // User's email

    @NotEmpty(message = "select the gender") // Validation for non-empty fields
    private String gender; // User's gender

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // Format for date
    private LocalDate dateOfBirth; // User's date of birth

    @NotEmpty(message = "Institute Name is mandatory") // Validation for non-empty fields
    private String instituteName; // User's institute name

    @NotEmpty(message = "ContactNumber is mandatory") // Validation for non-empty fields
    private String contactNumber; // User's contact number

    @NotEmpty(message = "Country is mandatory") // Validation for non-empty fields
    private String country; // User's country

    @NotEmpty(message = "State is mandatory") // Validation for non-empty fields
    private String state; // User's state

    @NotEmpty(message = "City is mandatory") // Validation for non-empty fields
    private String city; // User's city

    @NotEmpty(message = "Pincode is mandatory") // Validation for non-empty fields
    private String pincode; // User's pincode

    @NotEmpty // Validation for non-empty fields
    @Size(min = 6, message = "password must be min of 6 characters") // Validation for size
    private String password; // User's password

    private Role role = Role.USER; // Default role assigned to the user

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(getUsername())); // Granting authorities based on the username
    }

    @Override
    public String getUsername() {
        return email; // Username is the user's email
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired(); // Default implementation for account expiration
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked(); // Default implementation for account lock
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired(); // Default implementation for credential expiration
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled(); // Default implementation for account enabled state
    }

    // Additional methods can be added here if needed
}
