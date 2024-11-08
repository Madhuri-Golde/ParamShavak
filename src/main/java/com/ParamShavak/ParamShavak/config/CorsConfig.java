//package com.ParamShavak.ParamShavak.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:3000")
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                .allowedHeaders("*")
//                .allowCredentials(true);
//    }
//}


package com.ParamShavak.ParamShavak.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    // Override the addCorsMappings method to define custom CORS (Cross-Origin Resource Sharing) configuration.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply the CORS configuration to all endpoints in the application ("**" means any path).
                .allowedOrigins("http://localhost:3000") // Allow requests from the specified origin (in this case, the React frontend running on localhost:3000).
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow the specified HTTP methods (GET, POST, PUT, DELETE, OPTIONS) from the allowed origins.
                .allowedHeaders("*") // Allow any headers in the request (e.g., Authorization, Content-Type, etc.).
                .allowCredentials(true); // Allow sending credentials (e.g., cookies, authorization headers) in the request. This is required when working with authenticated requests.
    }
}

