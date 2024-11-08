//package com.ParamShavak.ParamShavak.Repository;
//
//import com.ParamShavak.ParamShavak.Model.Enquiry;
//import com.ParamShavak.ParamShavak.Model.User;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
//import org.springframework.stereotype.Repository;
//import java.util.Optional;
//
//@Repository
//public interface EnquiryRepository extends MongoRepository<User, String> {
////    Optional<User> findUser(String enquiryId);  // Optional method for finding enquiry by ID
//
//    @Query("{ 'enquiries.enquiryId': ?0 }")
//    Optional<User> findByEnquiriesEnquiryId(String enquiryId);
//
//    // New method to count enquiries with a non-null PDF file ID
//    long countByEnquiries_PdfFileIdIsNotNull();
//}





package com.ParamShavak.ParamShavak.Repository;

import com.ParamShavak.ParamShavak.Model.Enquiry; // Importing Enquiry model (not used directly here)
import com.ParamShavak.ParamShavak.Model.User; // Importing User model
import org.springframework.data.mongodb.repository.MongoRepository; // Spring Data interface for MongoDB repositories
import org.springframework.data.mongodb.repository.Query; // Annotation for custom query methods
import org.springframework.stereotype.Repository; // Annotation to indicate that this is a repository
import java.util.Optional; // Import for Optional type

@Repository // Indicates that this interface is a repository
public interface EnquiryRepository extends MongoRepository<User, String> { // Extends MongoRepository for User entity

    // Optional method for finding a user by their enquiry ID
    @Query("{ 'enquiries.enquiryId': ?0 }") // Custom MongoDB query to find a user based on an enquiry ID
    Optional<User> findByEnquiriesEnquiryId(String enquiryId);

    // New method to count enquiries with a non-null PDF file ID
    long countByEnquiries_PdfFileIdIsNotNull(); // Counts the number of enquiries that have a PDF file ID
}
