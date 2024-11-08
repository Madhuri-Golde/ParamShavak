//package com.ParamShavak.ParamShavak.Controller;
//
//import com.ParamShavak.ParamShavak.Model.Enquiry;
//import com.ParamShavak.ParamShavak.Model.User;
//import com.ParamShavak.ParamShavak.Repository.EnquiryRepository;
//import com.ParamShavak.ParamShavak.Services.FileService;
//import com.mongodb.client.gridfs.GridFSBucket;
//import com.mongodb.client.gridfs.model.GridFSUploadOptions;
//import com.mongodb.client.gridfs.model.GridFSFile;
//import org.bson.types.ObjectId;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/v1/")
//public class FileController {
//
//    @Autowired
//    private GridFSBucket gridFSBucket;
//
//    @Autowired
//    private EnquiryRepository enquiryRepository;
//
//    @Autowired
//    private FileService fileService;
//
//
//    @PostMapping("/upload")
//    public ResponseEntity<ResponseMessage> uploadFile(
//            @RequestParam("file") MultipartFile file,
//            @RequestParam("enquiryId") String enquiryId) {
//        System.out.println("Received Enquiry ID: " + enquiryId); // Debug statement
//
//        Optional<User> optionalUser = enquiryRepository.findByEnquiriesEnquiryId(enquiryId);
//        if (!optionalUser.isPresent()) {
//            System.out.println("Enquiry not found with id: " + enquiryId); // Debug statement
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new ResponseMessage("Enquiry not found with id: " + enquiryId));
//        }
//
//        Enquiry foundEnquiry = optionalUser.get().getEnquiries()
//                .stream()
//                .filter(enquiry -> enquiry.getEnquiryId().equals(enquiryId))
//                .findFirst()
//                .orElse(null);
//
//        if (foundEnquiry == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new ResponseMessage("Enquiry not found with id: " + enquiryId));
//        }
//
//        try {
//            InputStream fileStream = file.getInputStream();
//            GridFSUploadOptions options = new GridFSUploadOptions()
//                    .metadata(new org.bson.Document("type", file.getContentType()));
//            ObjectId fileId = gridFSBucket.uploadFromStream(file.getOriginalFilename(), fileStream, options);
//
//            foundEnquiry.setPdfFileId(fileId.toString());
//            enquiryRepository.save(optionalUser.get()); // Save updated user with enquiry
//
//            return ResponseEntity.ok(new ResponseMessage("File uploaded successfully with id: " + fileId));
//        } catch (IOException e) {
//            e.printStackTrace(); // Log the error
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ResponseMessage("Error occurred while uploading file: " + e.getMessage()));
//        }
//    }
//
//
//
//
//
//
//
//
//    @GetMapping("/download/{fileId}")
//
//
//
//    public ResponseEntity<InputStreamResource> viewQuotation(@PathVariable String fileId) {
//        try {
//            InputStream inputStream = fileService.downloadFile(fileId);
//            HttpHeaders headers = new HttpHeaders();
//            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=quotation.pdf");
//            headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
//
//            return new ResponseEntity<>(new InputStreamResource(inputStream), headers, HttpStatus.OK);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//
//
//
//
//    // Response message class
//    public static class ResponseMessage {
//        private String message;
//
//        public ResponseMessage(String message) {
//            this.message = message;
//        }
//
//        public String getMessage() {
//            return message;
//        }
//
//        public void setMessage(String message) {
//            this.message = message;
//        }
//    }
//}


package com.ParamShavak.ParamShavak.Controller;

import com.ParamShavak.ParamShavak.Model.Enquiry;
import com.ParamShavak.ParamShavak.Model.User;
import com.ParamShavak.ParamShavak.Repository.EnquiryRepository;
import com.ParamShavak.ParamShavak.Services.FileService;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@RestController  // Indicates that this class is a REST controller
@RequestMapping("/api/v1/")  // Base URL for all endpoints in this controller
public class FileController {

    @Autowired
    private GridFSBucket gridFSBucket;  // MongoDB GridFS bucket for storing files

    @Autowired
    private EnquiryRepository enquiryRepository;  // Repository to access enquiry data

    @Autowired
    private FileService fileService;  // Service to handle file-related operations

    // Endpoint for uploading a file associated with a specific enquiry
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(
            @RequestParam("file") MultipartFile file,  // File to upload
            @RequestParam("enquiryId") String enquiryId) {  // ID of the enquiry
        System.out.println("Received Enquiry ID: " + enquiryId); // Debug statement

        // Check if the enquiry exists using the enquiry ID
        Optional<User> optionalUser = enquiryRepository.findByEnquiriesEnquiryId(enquiryId);
        if (!optionalUser.isPresent()) {
            System.out.println("Enquiry not found with id: " + enquiryId); // Debug statement
            // Return a 404 NOT FOUND response if the enquiry is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("Enquiry not found with id: " + enquiryId));
        }

        // Find the specific enquiry associated with the enquiry ID
        Enquiry foundEnquiry = optionalUser.get().getEnquiries()
                .stream()
                .filter(enquiry -> enquiry.getEnquiryId().equals(enquiryId))
                .findFirst()
                .orElse(null);

        // Check if the found enquiry is null
        if (foundEnquiry == null) {
            // Return a 404 NOT FOUND response if the enquiry is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("Enquiry not found with id: " + enquiryId));
        }

        try {
            // Get the input stream of the uploaded file
            InputStream fileStream = file.getInputStream();
            // Create options for uploading the file, including metadata
            GridFSUploadOptions options = new GridFSUploadOptions()
                    .metadata(new org.bson.Document("type", file.getContentType()));
            // Upload the file to GridFS and get the generated file ID
            ObjectId fileId = gridFSBucket.uploadFromStream(file.getOriginalFilename(), fileStream, options);

            // Set the PDF file ID in the found enquiry
            foundEnquiry.setPdfFileId(fileId.toString());
            // Save the updated user with the enquiry to the repository
            enquiryRepository.save(optionalUser.get());

            // Return a success response with the uploaded file ID
            return ResponseEntity.ok(new ResponseMessage("File uploaded successfully with id: " + fileId));
        } catch (IOException e) {
            e.printStackTrace(); // Log the error
            // Return a 500 INTERNAL SERVER ERROR response if an exception occurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage("Error occurred while uploading file: " + e.getMessage()));
        }
    }

    // Endpoint for downloading a file by its ID
    @GetMapping("/download/{fileId}")
    public ResponseEntity<InputStreamResource> viewQuotation(@PathVariable String fileId) {
        try {
            // Get the input stream of the file to be downloaded using the file service
            InputStream inputStream = fileService.downloadFile(fileId);
            // Prepare the HTTP headers for the response
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=quotation.pdf"); // Inline display
            headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf"); // Content type

            // Return the file as a response entity with the input stream and headers
            return new ResponseEntity<>(new InputStreamResource(inputStream), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace(); // Log the error
            // Return a 500 INTERNAL SERVER ERROR response if an exception occurs
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Response message class to encapsulate response messages
    public static class ResponseMessage {
        private String message; // Message field

        // Constructor to initialize the message
        public ResponseMessage(String message) {
            this.message = message;
        }

        // Getter for the message
        public String getMessage() {
            return message;
        }

        // Setter for the message
        public void setMessage(String message) {
            this.message = message;
        }
    }
}
