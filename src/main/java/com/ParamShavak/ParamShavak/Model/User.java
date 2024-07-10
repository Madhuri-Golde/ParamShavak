package com.ParamShavak.ParamShavak.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDate;
import java.util.Date;


@Document
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @Id
    private String id;

    @NotEmpty
    @Size(min=4, message = "firstname must be of min 4 characters")
    private String firstname;

    @NotEmpty
    @Size(min=3, message = "lastname must be of min 3 characters")
    private String lastname;

    @Email(message = "Email Address is not valid")
    private String mailid;

    @NotEmpty(message = "select the gender")
    private String gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotEmpty(message = "Institute Name is mandatory")
    private String instituteName;

    @NotEmpty(message = "ContactNumber is mandatory")
    private String contactNumber;

    @NotEmpty(message = "Country is mandatory")
    private String country;

    @NotEmpty(message = "State is mandatory")
    private String state;

    @NotEmpty(message = "City is mandatory")
    private String city;

    @NotEmpty(message = "Pincode is mandatory")
    private String pincode;

    @NotEmpty
    @Size(min =6, message = "password must be min of 6 characters")
    private String password;





}
