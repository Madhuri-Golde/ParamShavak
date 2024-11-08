//package com.ParamShavak.ParamShavak.exceptions;
//
//import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//        @ExceptionHandler(MethodArgumentNotValidException.class)
//        @ResponseStatus(HttpStatus.BAD_REQUEST)
//
//        public ResponseEntity<Map<String, String>>handleValidationExceptions(MethodArgumentNotValidException ex){
//                Map<String, String> errors = new HashMap<>();
//                ex.getBindingResult().getFieldErrors().forEach((FieldError error) ->
//                        errors.put(error.getField(), error.getDefaultMessage())
//                );
//                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//        }
//
//
//}


package com.ParamShavak.ParamShavak.exceptions;

import org.springframework.http.HttpStatus; // For HTTP status codes
import org.springframework.http.ResponseEntity; // To create response entities with status codes
import org.springframework.validation.FieldError; // For capturing validation errors
import org.springframework.web.bind.MethodArgumentNotValidException; // Exception for validation errors
import org.springframework.web.bind.annotation.ControllerAdvice; // Allows handling exceptions globally
import org.springframework.web.bind.annotation.ExceptionHandler; // Annotation for handling specific exceptions
import org.springframework.web.bind.annotation.ResponseStatus; // Annotation for setting response status

import java.util.HashMap; // For storing error messages
import java.util.Map; // For the error response structure

@ControllerAdvice // Indicates that this class will handle exceptions globally across all controllers
public class GlobalExceptionHandler {

        // Method to handle validation exceptions
        @ExceptionHandler(MethodArgumentNotValidException.class) // Specifies the type of exception this method handles
        @ResponseStatus(HttpStatus.BAD_REQUEST) // Sets the response status to 400 Bad Request
        public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
                Map<String, String> errors = new HashMap<>(); // Create a map to hold field errors
                // Iterate over the field errors and populate the map with field names and error messages
                ex.getBindingResult().getFieldErrors().forEach((FieldError error) ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );
                // Return a response entity with the error map and the HTTP status
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
}
