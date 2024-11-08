////package com.ParamShavak.ParamShavak;
////
////import org.springframework.boot.SpringApplication;
////import org.springframework.boot.autoconfigure.SpringBootApplication;
////import org.springframework.context.annotation.ComponentScan;
////
////@SpringBootApplication
////public class ParamShavakApplication {
////
////	public static void main(String[] args) {
////		SpringApplication.run(ParamShavakApplication.class, args);
////	}
////
////}
//package com.ParamShavak.ParamShavak;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class ParamShavakApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(ParamShavakApplication.class, args);
//	}
//}


package com.ParamShavak.ParamShavak; // Package declaration for the application

import org.springframework.boot.SpringApplication; // Importing SpringApplication class to bootstrap the application
import org.springframework.boot.autoconfigure.SpringBootApplication; // Importing SpringBootApplication for auto-configuration

// The @SpringBootApplication annotation is a convenience annotation that combines:
// @Configuration, @EnableAutoConfiguration, and @ComponentScan
// It indicates that this class is the primary Spring configuration class.
@SpringBootApplication
public class ParamShavakApplication {

	// The main method serves as the entry point for the Spring Boot application.
	public static void main(String[] args) {
		// SpringApplication.run() method launches the application.
		// It takes the primary Spring component class (ParamShavakApplication.class) and command-line arguments as parameters.
		SpringApplication.run(ParamShavakApplication.class, args);
	}
}


