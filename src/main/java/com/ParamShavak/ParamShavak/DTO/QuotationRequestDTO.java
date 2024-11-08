//// src/main/java/com/ParamShavak/ParamShavak/DTO/QuotationRequestDTO.java
//package com.ParamShavak.ParamShavak.DTO;
//
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//
//@Data
//@Getter
//@Setter
//public class QuotationRequestDTO {
//    private String orgLocation;
//    private String orgIdCard; // you might want to handle file upload differently
//    private String designation;
//    private String orgEmail;
//    private String orgContact;
//    private String workDomain;
//    private String workPurpose;
//    private String workProject;
//    private String variant;
//}



// src/main/java/com/ParamShavak/ParamShavak/DTO/QuotationRequestDTO.java
package com.ParamShavak.ParamShavak.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// Data Transfer Object for quotation requests
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@Getter // Lombok annotation to generate getters for all fields
@Setter // Lombok annotation to generate setters for all fields
public class QuotationRequestDTO {

    private String orgLocation; // Location of the organization
    private String orgIdCard; // Identifier for the organization, potentially a file upload (consider handling file uploads differently)
    private String designation; // User's designation in the organization
    private String orgEmail; // Organization's email address
    private String orgContact; // Contact number for the organization
    private String workDomain; // Domain of work for the quotation
    private String workPurpose; // Purpose of the work for which the quotation is requested
    private String workProject; // Specific project details for the quotation
    private String variant; // Variant of the service or product requested
}
