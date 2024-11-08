//package com.ParamShavak.ParamShavak.Repository;
//
//import com.ParamShavak.ParamShavak.Model.Otp;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
//
//import java.util.Optional;
//
//public interface OtpRepository extends MongoRepository<Otp, String> {
//    Optional<Otp> findFirstByTypeAndValue(String type, String value);
//}
//


package com.ParamShavak.ParamShavak.Repository;

import com.ParamShavak.ParamShavak.Model.Otp; // Importing the Otp model
import org.springframework.data.mongodb.repository.MongoRepository; // Spring Data interface for MongoDB repositories
import java.util.Optional; // Import for Optional type

// Repository interface for managing Otp entities
public interface OtpRepository extends MongoRepository<Otp, String> { // Extends MongoRepository for Otp entity

    // Method to find the first Otp entry based on type and value
    Optional<Otp> findFirstByTypeAndValue(String type, String value); // Returns an Optional containing the found Otp or empty if not found
}
