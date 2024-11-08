//package com.ParamShavak.ParamShavak.Repository;
//
//import com.ParamShavak.ParamShavak.Model.Enquiry;
//import com.ParamShavak.ParamShavak.Model.User;
//import org.springframework.data.mongodb.repository.MongoRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface UserRepo extends MongoRepository<User, String> {
//    Optional<User> findByEmail(String email);
//
//    User findByUsername(String username);
//
//
//    List<User> findByEnquiriesNotNull();
//
//
//    Optional<User> findByEnquiries_EnquiryId(String enquiryId);
//}




package com.ParamShavak.ParamShavak.Repository;

import com.ParamShavak.ParamShavak.Model.User; // Importing the User model
import org.springframework.data.mongodb.repository.MongoRepository; // Spring Data interface for MongoDB repositories

import java.util.List; // Import for List type
import java.util.Optional; // Import for Optional type

// Repository interface for managing User entities
public interface UserRepo extends MongoRepository<User, String> { // Extends MongoRepository for User entity

    // Method to find a User by their email
    Optional<User> findByEmail(String email); // Returns an Optional containing the found User or empty if not found

    // Method to find a User by their username
    User findByUsername(String username); // Returns the User associated with the given username

    // Method to find all Users that have at least one enquiry
    List<User> findByEnquiriesNotNull(); // Returns a list of Users with non-null enquiries

    // Method to find a User by a specific enquiry ID
    Optional<User> findByEnquiries_EnquiryId(String enquiryId); // Returns an Optional containing the User if found
}
