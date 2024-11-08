
package com.ParamShavak.ParamShavak.config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfiguration {
//
//    private final JwtAuthFilter jwtAuthFilter;
//    private final AuthenticationProvider authenticationProvider;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/api/v1/auth/**" ,"/api/v1/verify-otp", "/api/v1/send-otp", "/api/v1/enquiries/users")
//                        .permitAll()
//                        .requestMatchers("/api/v1/upload") // Require authentication for upload
//                        .authenticated()
//                        .requestMatchers("/api/v1/user/profile", "/api/v1/view/**", "/api/v1/users/my-enquiries","api/v1/enquiries/upload-count","/api/v1/enquiries/count","api/v1/users", "/api/v1/enquiries", "/api/v1/users/submit-quotation").authenticated() // Authenticated users can access /download
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//}





@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthFilter jwtAuthFilter; // Custom JWT filter to handle token-based authentication
    private final AuthenticationProvider authenticationProvider; // Custom authentication provider for user authentication

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF since we're using token-based authentication, not session-based
                .csrf(AbstractHttpConfigurer::disable)

                // Configure authorization rules for different request endpoints
                .authorizeHttpRequests(authorize -> authorize
                        // Publicly accessible endpoints that don't require authentication
                        .requestMatchers("/api/v1/auth/**" ,"/api/v1/verify-otp", "/api/v1/send-otp", "/api/v1/enquiries/users")
                        .permitAll()

                        // Require authentication for file uploads
                        .requestMatchers("/api/v1/upload")
                        .authenticated()

                        // Authenticated users can access the profile, view data, their enquiries, and other related endpoints
                        .requestMatchers("/api/v1/user/profile", "/api/v1/view/**", "/api/v1/users/my-enquiries","api/v1/enquiries/upload-count","/api/v1/enquiries/count","api/v1/users", "/api/v1/enquiries", "/api/v1/users/submit-quotation")
                        .authenticated()

                        // All other requests require authentication by default
                        .anyRequest().authenticated()
                )

                // Configure session management to be stateless (no session is created; each request is authenticated individually using JWT)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Set the custom authentication provider to handle user authentication
                .authenticationProvider(authenticationProvider)

                // Add the JWT authentication filter before the UsernamePasswordAuthenticationFilter to validate the JWT token with each request
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build(); // Build the security configuration and return the SecurityFilterChain
    }
}
