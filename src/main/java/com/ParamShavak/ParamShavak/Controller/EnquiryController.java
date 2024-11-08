//package com.ParamShavak.ParamShavak.Controller;
//
//
//import com.ParamShavak.ParamShavak.Model.Enquiry;
//import com.ParamShavak.ParamShavak.Model.User;
//import com.ParamShavak.ParamShavak.Services.EnquiryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/v1/enquiries")
//public class EnquiryController {
//
//    @Autowired
//    private EnquiryService enquiryService;
//
//    @GetMapping
//    public List<Map<String, Object>> getAllEnquiries() {
//        return enquiryService.getAllEnquiries();
//    }
//
//
//    @GetMapping("/{enquiryId}")
//    public ResponseEntity<Enquiry> getEnquiryById(@PathVariable String enquiryId) {
//        // Print the received enquiry ID
//        System.out.println("Received Enquiry ID: " + enquiryId);
//
//        // Find the enquiry by ID
//        Optional<Enquiry> optionalEnquiry = enquiryService.findByEnquiryId(enquiryId);
//
//        // Check if enquiry is present
//        if (optionalEnquiry.isEmpty()) {
//            // Print the error message if not found
//            System.out.println("Enquiry not found with id: " + enquiryId);
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Enquiry not found with id: " + enquiryId);
//        }
//
//        // Print the found enquiry details
//        System.out.println("Found Enquiry: " + optionalEnquiry.get());
//
//        return ResponseEntity.ok(optionalEnquiry.get());
//    }
//
//    @DeleteMapping("/{enquiryId}")
//    public ResponseEntity<String> deleteEnquiry(@PathVariable String enquiryId) {
//        boolean deleted = enquiryService.deleteEnquiry(enquiryId);
//
//        if (deleted) {
//            return new ResponseEntity<>("Enquiry deleted successfully", HttpStatus.OK);
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Enquiry not found with id: " + enquiryId);
//        }
//    }
//
//    @GetMapping("/count")
//    public ResponseEntity<Map<String, Integer>> getEnquiriesCount() {
//        int count = enquiryService.getEnquiriesCount();
//        Map<String, Integer> response = Collections.singletonMap("count", count);
//        return ResponseEntity.ok(response);
//    }
//
//    @DeleteMapping("/{enquiryId}/pdf")
//    public ResponseEntity<String> deletePdfFile(@PathVariable String enquiryId) {
//        enquiryService.deletePdfFromEnquiry(enquiryId);
//        return ResponseEntity.ok("PDF deleted successfully");
//    }
//
//    // New endpoint to get enquiries with PDFs count
//    @GetMapping("/upload-count")
//    public ResponseEntity<Map<String, Integer>> getEnquiriesWithPdfCount() {
//        int count = enquiryService.getEnquiriesWithPdfCount();
//        Map<String, Integer> response = Collections.singletonMap("count", count);
//        return ResponseEntity.ok(response);
//    }
//
//
//}


package com.ParamShavak.ParamShavak.Controller;

import com.ParamShavak.ParamShavak.Model.Enquiry;
import com.ParamShavak.ParamShavak.Services.EnquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController  // This annotation indicates that this class serves RESTful web services
@RequestMapping("/api/v1/enquiries")  // Base URL for all enquiry-related endpoints
public class EnquiryController {

    @Autowired  // Automatically injects the EnquiryService bean
    private EnquiryService enquiryService;  // Service to handle enquiry-related operations

    // Endpoint to get all enquiries
    @GetMapping
    public List<Map<String, Object>> getAllEnquiries() {
        return enquiryService.getAllEnquiries();  // Retrieve and return all enquiries as a list
    }

    // Endpoint to get a specific enquiry by its ID
    @GetMapping("/{enquiryId}")
    public ResponseEntity<Enquiry> getEnquiryById(@PathVariable String enquiryId) {
        // Print the received enquiry ID for debugging purposes
        System.out.println("Received Enquiry ID: " + enquiryId);

        // Find the enquiry by ID using the service
        Optional<Enquiry> optionalEnquiry = enquiryService.findByEnquiryId(enquiryId);

        // Check if the enquiry is present
        if (optionalEnquiry.isEmpty()) {
            // Print the error message if the enquiry is not found
            System.out.println("Enquiry not found with id: " + enquiryId);
            // Throw a 404 NOT FOUND exception with a descriptive message
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Enquiry not found with id: " + enquiryId);
        }

        // Print the found enquiry details for debugging purposes
        System.out.println("Found Enquiry: " + optionalEnquiry.get());

        // Return the found enquiry in the response
        return ResponseEntity.ok(optionalEnquiry.get());
    }

    // Endpoint to delete an enquiry by its ID
    @DeleteMapping("/{enquiryId}")
    public ResponseEntity<String> deleteEnquiry(@PathVariable String enquiryId) {
        // Attempt to delete the enquiry using the service
        boolean deleted = enquiryService.deleteEnquiry(enquiryId);

        // Check if the deletion was successful
        if (deleted) {
            // Return a success message with HTTP status 200 OK
            return new ResponseEntity<>("Enquiry deleted successfully", HttpStatus.OK);
        } else {
            // Throw a 404 NOT FOUND exception if the enquiry was not found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Enquiry not found with id: " + enquiryId);
        }
    }

    // Endpoint to get the count of all enquiries
    @GetMapping("/count")
    public ResponseEntity<Map<String, Integer>> getEnquiriesCount() {
        // Get the count of enquiries from the service
        int count = enquiryService.getEnquiriesCount();
        // Prepare a response map containing the count
        Map<String, Integer> response = Collections.singletonMap("count", count);
        // Return the count in the response
        return ResponseEntity.ok(response);
    }

    // Endpoint to delete the PDF file associated with an enquiry by its ID
    @DeleteMapping("/{enquiryId}/pdf")
    public ResponseEntity<String> deletePdfFile(@PathVariable String enquiryId) {
        // Delete the PDF file from the specified enquiry
        enquiryService.deletePdfFromEnquiry(enquiryId);
        // Return a success message indicating the PDF has been deleted
        return ResponseEntity.ok("PDF deleted successfully");
    }

    // New endpoint to get the count of enquiries that have PDFs associated with them
    @GetMapping("/upload-count")
    public ResponseEntity<Map<String, Integer>> getEnquiriesWithPdfCount() {
        // Get the count of enquiries with PDFs from the service
        int count = enquiryService.getEnquiriesWithPdfCount();
        // Prepare a response map containing the count
        Map<String, Integer> response = Collections.singletonMap("count", count);
        // Return the count in the response
        return ResponseEntity.ok(response);
    }
}
