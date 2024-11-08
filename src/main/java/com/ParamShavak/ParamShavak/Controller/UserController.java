//package com.ParamShavak.ParamShavak.Controller;
//import com.ParamShavak.ParamShavak.DTO.QuotationRequestDTO;
//import com.ParamShavak.ParamShavak.Model.Enquiry;
//import com.ParamShavak.ParamShavak.Model.Role;
//import com.ParamShavak.ParamShavak.Model.User;
//import com.ParamShavak.ParamShavak.Services.EmailService;
//import com.ParamShavak.ParamShavak.Services.UserService;
//import com.ParamShavak.ParamShavak.config.JwtService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//import java.security.Principal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("api/v1/users")
//public class UserController {
//
//        @Autowired
//        private UserService userService;
//
//        @Autowired
//        private JwtService jwtService;
//
//        @Autowired
//        private EmailService emailService;
//
//        @PreAuthorize("hasRole('ADMIN')")
//        @PostMapping
//        public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
//                if (user.getRole() == null) {
//                        user.setRole(Role.USER); // Ensure default role
//                }
//                User createdUser = userService.createUser(user);
//                return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
//        }
//
//
//        @GetMapping("/{id}")
//        public ResponseEntity<User> getUser(@PathVariable String id) {
//                User user = userService.getUserById(id);
//                if (user != null) {
//                        return ResponseEntity.ok(user);
//                } else {
//                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//                }
//        }
//
//        @GetMapping
//        public ResponseEntity<List<User>> getAllUsers() {
//                return ResponseEntity.ok(userService.getAllUsers());
//        }
//
//        @PutMapping("/{id}")
//        public ResponseEntity<User> updateUserProfile(@PathVariable String id, @RequestBody User userDetails) {
//                if (id == null || userDetails == null) {
//                        return ResponseEntity.badRequest().body(null);
//                }
//                User updatedUser = userService.updateUser(id, userDetails); // Updated to match the service method
//                if (updatedUser != null) {
//                        return ResponseEntity.ok(updatedUser);
//                } else {
//                        return ResponseEntity.notFound().build();
//                }
//        }
//
//        @DeleteMapping("/{id}")
//        public ResponseEntity<Void> deleteUser(@PathVariable String id) {
//                userService.deleteUser(id);
//                return ResponseEntity.noContent().build();
//        }
//
//        @GetMapping("/profile")
//        public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String token) {
//                try {
//                        // Log the received token
//                        System.out.println("Received token: " + token);
//
//                        // Remove "Bearer " from the token if it's included
//                        String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;
//                        System.out.println("JWT token: " + jwtToken);
//
//                        // Extract email from token
//                        String email = jwtService.extractUsername(jwtToken);
//                        System.out.println("Extracted email: " + email);
//
//                        // Check if email is null or empty
//                        if (email == null || email.isEmpty()) {
//                                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token");
//                        }
//
//                        Optional<User> userOptional = userService.getUserByEmail(email);
//                        if (userOptional.isPresent()) {
//                                return ResponseEntity.ok(userOptional.get());
//                        } else {
//                                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//                        }
//                } catch (Exception e) {
//                        // Log the exception for debugging purposes
//                        e.printStackTrace();
//                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
//                }
//        }
//
//
//
//
//
//        @PostMapping("/submit-quotation")
//        public ResponseEntity<?> submitQuotation(@RequestBody Enquiry enquiry, @RequestHeader("Authorization") String token) {
//                System.out.println("Received request to submit quotation with token: " + token);
//
//                try {
//                    // Extract user email from JWT token
//                    String userEmail;
//                    if (token != null && token.startsWith("Bearer ")) {
//                        userEmail = jwtService.extractUsername(token.substring(7));
//                        System.out.println("Extracted email from token: " + userEmail);
//                    } else {
//                        System.out.println("Invalid token format: " + token);
//                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token format");
//                    }
//
//                    // Ensure the enquiry has a unique ID
//                    if (enquiry.getEnquiryId() == null || enquiry.getEnquiryId().isEmpty()) {
//                        enquiry.setEnquiryId(UUID.randomUUID().toString());
//                    }
//                    System.out.println("Generated enquiry ID: " + enquiry.getEnquiryId());
//
//                    // Save the enquiry to the user's record
//                    System.out.println("Adding enquiry to user: " + enquiry);
//                    userService.addEnquiryToUser(userEmail, enquiry);
//                    System.out.println("Successfully added enquiry to user");
//
//                    // Notify admin with the enquiry details
//                    notifyAdmin(enquiry, userEmail);
//                    System.out.println("Notification sent to admin for enquiry ID: " + enquiry.getEnquiryId());
//
//                    return ResponseEntity.ok("Enquiry submitted successfully with ID: " + enquiry.getEnquiryId());
//                } catch (Exception e) {
//                        System.out.println("Error occurred while submitting enquiry: " + e.getMessage());
//                        e.printStackTrace();  // This will print the stack trace in the console, useful for debugging
//                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to submit enquiry");
//                }
//        }
//
//
//
//        private void notifyAdmin(Enquiry enquiry, String userEmail) {
//                String adminEmail = "gmadhuri@cdac.in";
//                String subject = "New Quotation Request from " + userEmail;
//
//                // Create a detailed email body with all enquiry information
//                StringBuilder body = new StringBuilder();
//                body.append("New quotation request submitted:\n")
//                        .append("User Email: ").append(userEmail).append("\n")
//                        .append("Name: ").append(enquiry.getName()).append("\n")
//                        .append("Last Name: ").append(enquiry.getLastName()).append("\n")
//                        .append("Email: ").append(enquiry.getEmail()).append("\n")
//                        .append("Institute Email: ").append(enquiry.getInstituteEmail()).append("\n")
//                        .append("Mobile Number: ").append(enquiry.getMobileNumber()).append("\n")
//                        .append("Institute Mobile Number: ").append(enquiry.getInstituteMobileNumber()).append("\n")
//                        .append("Institute Location: ").append(enquiry.getInstituteLocation()).append("\n")
//                        .append("Designation: ").append(enquiry.getDesignation()).append("\n")
//                        .append("Domain: ").append(enquiry.getDomain()).append("\n")
//                        .append("Purpose: ").append(enquiry.getPurpose()).append("\n")
////                        .append("Project Description: ").append(enquiry.getProjectDescription())
//                       .append("\n")
//                        .append("Variant: ").append(enquiry.getVariant()).append("\n");
//
//                // Send email to admin
//                emailService.sendEmail(adminEmail, subject, body.toString());
//        }
//
//        @GetMapping("/my-enquiries")
//        public ResponseEntity<?> getUserEnquiries(Principal principal) {
//                // Fetch the logged-in user's email from the Principal object
//                String email = principal.getName();
//
//                // Fetch the user's enquiries from the service layer
//                List<Enquiry> enquiries = userService.getUserEnquiriesByEmail(email);
//
//                if (enquiries == null) {
//                        return new ResponseEntity<>("No enquiries found for the user.", HttpStatus.NOT_FOUND);
//                }
//
//                // Return the enquiries with serial numbers
//                return new ResponseEntity<>(enquiries, HttpStatus.OK);
//        }
//
//}

package com.ParamShavak.ParamShavak.Controller;

import com.ParamShavak.ParamShavak.DTO.QuotationRequestDTO;
import com.ParamShavak.ParamShavak.Model.Enquiry;
import com.ParamShavak.ParamShavak.Model.Role;
import com.ParamShavak.ParamShavak.Model.User;
import com.ParamShavak.ParamShavak.Services.EmailService;
import com.ParamShavak.ParamShavak.Services.UserService;
import com.ParamShavak.ParamShavak.config.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users") // Base URL for user-related operations
public class UserController {

        @Autowired
        private UserService userService; // Service to manage user-related operations

        @Autowired
        private JwtService jwtService; // Service to handle JWT token operations

        @Autowired
        private EmailService emailService; // Service to manage email notifications

        @PreAuthorize("hasRole('ADMIN')") // Only allow ADMIN users to add new users
        @PostMapping
        public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
                // Ensure the user has a role; default to USER if not provided
                if (user.getRole() == null) {
                        user.setRole(Role.USER); // Ensure default role
                }
                // Create the user via the user service
                User createdUser = userService.createUser(user);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdUser); // Return created user with HTTP 201 status
        }

        @GetMapping("/{id}") // Retrieve a user by their ID
        public ResponseEntity<User> getUser(@PathVariable String id) {
                User user = userService.getUserById(id); // Fetch user by ID
                if (user != null) {
                        return ResponseEntity.ok(user); // Return user if found
                } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if not found
                }
        }

        @GetMapping // Retrieve all users
        public ResponseEntity<List<User>> getAllUsers() {
                return ResponseEntity.ok(userService.getAllUsers()); // Return list of users
        }

        @PutMapping("/{id}") // Update a user profile by ID
        public ResponseEntity<User> updateUserProfile(@PathVariable String id, @RequestBody User userDetails) {
                // Check for valid ID and user details
                if (id == null || userDetails == null) {
                        return ResponseEntity.badRequest().body(null); // Return 400 for bad request
                }
                User updatedUser = userService.updateUser(id, userDetails); // Update user details
                if (updatedUser != null) {
                        return ResponseEntity.ok(updatedUser); // Return updated user if successful
                } else {
                        return ResponseEntity.notFound().build(); // Return 404 if user not found
                }
        }

        @DeleteMapping("/{id}") // Delete a user by ID
        public ResponseEntity<Void> deleteUser(@PathVariable String id) {
                userService.deleteUser(id); // Call service to delete user
                return ResponseEntity.noContent().build(); // Return 204 No Content status
        }

        @GetMapping("/profile") // Retrieve the profile of the logged-in user
        public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String token) {
                try {
                        // Log the received token
                        System.out.println("Received token: " + token);

                        // Remove "Bearer " from the token if it's included
                        String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;
                        System.out.println("JWT token: " + jwtToken);

                        // Extract email from token
                        String email = jwtService.extractUsername(jwtToken);
                        System.out.println("Extracted email: " + email);

                        // Check if email is null or empty
                        if (email == null || email.isEmpty()) {
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token");
                        }

                        Optional<User> userOptional = userService.getUserByEmail(email); // Fetch user by email
                        if (userOptional.isPresent()) {
                                return ResponseEntity.ok(userOptional.get()); // Return user if found
                        } else {
                                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"); // Return 404 if not found
                        }
                } catch (Exception e) {
                        // Log the exception for debugging purposes
                        e.printStackTrace();
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred"); // Return 500 on error
                }
        }

        @PostMapping("/submit-quotation") // Submit a quotation request
        public ResponseEntity<?> submitQuotation(@RequestBody Enquiry enquiry, @RequestHeader("Authorization") String token) {
                System.out.println("Received request to submit quotation with token: " + token);

                try {
                        // Extract user email from JWT token
                        String userEmail;
                        if (token != null && token.startsWith("Bearer ")) {
                                userEmail = jwtService.extractUsername(token.substring(7)); // Extract email from the token
                                System.out.println("Extracted email from token: " + userEmail);
                        } else {
                                System.out.println("Invalid token format: " + token);
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token format"); // Return 400 for bad token format
                        }

                        // Ensure the enquiry has a unique ID
                        if (enquiry.getEnquiryId() == null || enquiry.getEnquiryId().isEmpty()) {
                                enquiry.setEnquiryId(UUID.randomUUID().toString()); // Generate a new UUID for the enquiry ID
                        }
                        System.out.println("Generated enquiry ID: " + enquiry.getEnquiryId());

                        // Save the enquiry to the user's record
                        System.out.println("Adding enquiry to user: " + enquiry);
                        userService.addEnquiryToUser(userEmail, enquiry); // Save enquiry linked to user
                        System.out.println("Successfully added enquiry to user");

                        // Notify admin with the enquiry details
                        notifyAdmin(enquiry, userEmail);
                        System.out.println("Notification sent to admin for enquiry ID: " + enquiry.getEnquiryId());

                        return ResponseEntity.ok("Enquiry submitted successfully with ID: " + enquiry.getEnquiryId()); // Return success response
                } catch (Exception e) {
                        System.out.println("Error occurred while submitting enquiry: " + e.getMessage());
                        e.printStackTrace();  // This will print the stack trace in the console, useful for debugging
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to submit enquiry"); // Return 500 on error
                }
        }

        // Notify the admin via email about the new enquiry
        private void notifyAdmin(Enquiry enquiry, String userEmail) {
                String adminEmail = "gmadhuri@cdac.in"; // Admin email address
                String subject = "New Quotation Request from " + userEmail; // Email subject

                // Create a detailed email body with all enquiry information
                StringBuilder body = new StringBuilder();
                body.append("New quotation request submitted:\n")
                        .append("User Email: ").append(userEmail).append("\n")
                        .append("Name: ").append(enquiry.getName()).append("\n")
                        .append("Last Name: ").append(enquiry.getLastName()).append("\n")
                        .append("Email: ").append(enquiry.getEmail()).append("\n")
                        .append("Institute Email: ").append(enquiry.getInstituteEmail()).append("\n")
                        .append("Mobile Number: ").append(enquiry.getMobileNumber()).append("\n")
                        .append("Institute Mobile Number: ").append(enquiry.getInstituteMobileNumber()).append("\n")
                        .append("Institute Location: ").append(enquiry.getInstituteLocation()).append("\n")
                        .append("Designation: ").append(enquiry.getDesignation()).append("\n")
                        .append("Domain: ").append(enquiry.getDomain()).append("\n")
                        .append("Purpose: ").append(enquiry.getPurpose()).append("\n")
                        .append("\n")
                        .append("Variant: ").append(enquiry.getVariant()).append("\n");

                // Send email to admin
                emailService.sendEmail(adminEmail, subject, body.toString()); // Send email notification to admin
        }

        @GetMapping("/my-enquiries") // Retrieve all enquiries for the logged-in user
        public ResponseEntity<?> getUserEnquiries(Principal principal) {
                // Fetch the logged-in user's email from the Principal object
                String email = principal.getName();

                // Fetch the user's enquiries from the service layer
                List<Enquiry> enquiries = userService.getUserEnquiriesByEmail(email);

                if (enquiries == null) {
                        return new ResponseEntity<>("No enquiries found for the user.", HttpStatus.NOT_FOUND); // Return 404 if no enquiries found
                }

                // Return the enquiries with serial numbers
                return new ResponseEntity<>(enquiries, HttpStatus.OK); // Return list of enquiries with HTTP 200 status
        }

}
