package com.ParamShavak.ParamShavak.Model;



import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.UUID;

@Document
@Getter
@Setter
public class Enquiry {
    @Id
    private String id;
    private String enquiryId; // MongoDB default ID
    private String name;
    private String lastName;
    private String email;
    private String instituteEmail;
    private String mobileNumber;
    private String instituteMobileNumber;
    private String instituteLocation;
    private String designation;
    private String domain;
    private String purpose;
//    private String projectDescription;
    private String variant;
    private String pdfFileId;

    public Enquiry() {
        this.enquiryId = UUID.randomUUID().toString(); // Generate unique ID on creation
    }



    // Other fields' getters and setters...
}