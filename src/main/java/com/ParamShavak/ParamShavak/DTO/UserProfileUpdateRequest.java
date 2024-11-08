//package com.ParamShavak.ParamShavak.DTO;
//
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.Size;
//
//public class UserProfileUpdateRequest {
//
//    @Email(message = "Invalid email format")
//    private String email;
//
//    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
//    private String phone;
//
//    @NotEmpty(message = "Address cannot be empty")
//    private String address;
//
//    // Add other fields as needed
//
//    // Getters and setters
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//}


package com.ParamShavak.ParamShavak.DTO;

import jakarta.validation.constraints.Email; // Annotation for email format validation
import jakarta.validation.constraints.NotEmpty; // Annotation to ensure a field is not empty
import jakarta.validation.constraints.Size; // Annotation to specify size constraints

public class UserProfileUpdateRequest {

    @Email(message = "Invalid email format") // Validates that the email field has a valid email format
    private String email;

    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters") // Validates phone number length
    private String phone;

    @NotEmpty(message = "Address cannot be empty") // Validates that the address field is not empty
    private String address;

    // Add other fields as needed (e.g., name, additional contact info, etc.)

    // Getters and setters for each field

    public String getEmail() {
        return email; // Returns the email
    }

    public void setEmail(String email) {
        this.email = email; // Sets the email
    }

    public String getPhone() {
        return phone; // Returns the phone number
    }

    public void setPhone(String phone) {
        this.phone = phone; // Sets the phone number
    }

    public String getAddress() {
        return address; // Returns the address
    }

    public void setAddress(String address) {
        this.address = address; // Sets the address
    }
}
