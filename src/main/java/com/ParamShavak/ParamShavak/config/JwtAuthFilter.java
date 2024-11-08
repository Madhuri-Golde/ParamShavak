//package com.ParamShavak.ParamShavak.config;
//
//import com.ParamShavak.ParamShavak.Services.UserDetailService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.lang.NonNull;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//@Configuration
//@EnableWebSecurity
//@Component
//@RequiredArgsConstructor
//public class JwtAuthFilter extends OncePerRequestFilter {
//
//    private final JwtService jwtService;
//    private final UserDetailService userDetailsService;
//
//    @Override
//    protected void doFilterInternal(
//           @NonNull HttpServletRequest request,
//                @NonNull    HttpServletResponse response,
//                    @NonNull   FilterChain filterChain)
//                            throws ServletException, IOException {
//                                final String authHeader = request.getHeader("Authorization");
//                                final String jwt;
//                                final String userEmail;
//                                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//                                    filterChain.doFilter(request, response);
//                                    return;
//                                }
//                                jwt = authHeader.substring(7);
//                                userEmail = jwtService.extractUsername(jwt);
//                                if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
//                                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
//                                    if(jwtService.isTokenValid(jwt,userDetails)){
//                                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                                                userDetails, null, userDetails.getAuthorities()
//                                        );
//                                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                                        SecurityContextHolder.getContext().setAuthentication(authToken);
//                                    }
//
//                                }
//                                filterChain.doFilter(request, response);
//    }
//}


package com.ParamShavak.ParamShavak.config;

import com.ParamShavak.ParamShavak.Services.UserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    // Inject necessary services for JWT validation and user details retrieval
    private final JwtService jwtService;
    private final UserDetailService userDetailsService;

    // Method that filters every incoming request to handle JWT-based authentication
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        // Retrieve the Authorization header from the incoming request
        final String authHeader = request.getHeader("Authorization");
        final String jwt;  // Variable to store the JWT token
        final String userEmail;  // Variable to store the username (email) extracted from the JWT token

        // Check if the Authorization header is missing or does not start with "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // If no valid Authorization header is present, continue the filter chain (i.e., don't perform any authentication)
            filterChain.doFilter(request, response);
            return;
        }

        // Extract the JWT token from the Authorization header by removing the "Bearer " prefix
        jwt = authHeader.substring(7);

        // Use the jwtService to extract the username (email) from the JWT token
        userEmail = jwtService.extractUsername(jwt);

        // Check if the username (email) is not null and if there is no existing authentication in the security context
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Load user details from the database using the UserDetailsService based on the extracted email
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            // Validate the JWT token against the user details (to ensure it has not been tampered with or expired)
            if (jwtService.isTokenValid(jwt, userDetails)) {

                // Create an authentication token with the user details and their authorities (roles/permissions)
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                // Set additional details about the current web request (e.g., client IP, session ID)
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set the authentication in the SecurityContext, indicating that the user is authenticated
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continue with the rest of the filter chain (passing the request to the next filter in the chain)
        filterChain.doFilter(request, response);
    }
}
