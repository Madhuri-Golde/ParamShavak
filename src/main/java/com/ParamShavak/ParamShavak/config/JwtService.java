//package com.ParamShavak.ParamShavak.config;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//import java.security.Key;
//
//@Service
//public class JwtService {
//
//    private static final String SECRET_KEY = "43315AF07B0D5BBBD7CC34C6AFF1994FA6510DEA6C968805DB64FA6A3160D87F6C398E755E3AF1DBE0840ED9EB1878AFC3B91D82FF0DF066755040ECA201316C02B979C740A9EAD0DFFC713A317708E055F0150F1F35D3F81CD3D872E95695969483346C2094EC089DC9B896714FBB493DC0C9B8CD034CE87A23C4F756DE13FD";
//    private static final long EXPIRATION_TIME = 1_800_000;
//
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
////    public String generateToken(UserDetails userDetails) {
////
////        return generateToken(new HashMap<>(), userDetails);
////    }
//
//
//    public String generateToken(UserDetails userDetails, String role) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("role", role); // Add role to the claims
//        return generateToken(claims, userDetails);
//    }
//
//
//    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
//        String token =  Jwts.builder()
//                .setClaims(extraClaims)
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // 30 minutes
//                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // Use HS256 for HMAC
//                .compact();
//        System.out.println("Generated Token: " + token);
//        return token;
//    }
//
//    public String generateToken(String email) {
//        return Jwts.builder()
//                .setSubject(email)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                .compact();
//    }
//
//    public boolean isTokenValid(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    private Claims extractAllClaims(String token) {
//        System.out.println("JWT Token: " + token);
//
//        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
//    }
//
//    private Key getSignInKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//}








//
//package com.ParamShavak.ParamShavak.config;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//import java.security.Key;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@Service
//public class JwtService {
//
//    private static final String SECRET_KEY = "43315AF07B0D5BBBD7CC34C6AFF1994FA6510DEA6C968805DB64FA6A3160D87F6C398E755E3AF1DBE0840ED9EB1878AFC3B91D82FF0DF066755040ECA201316C02B979C740A9EAD0DFFC713A317708E055F0150F1F35D3F81CD3D872E95695969483346C2094EC089DC9B896714FBB493DC0C9B8CD034CE87A23C4F756DE13FD";
//    private static final long EXPIRATION_TIME = 1_800_000;
//    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);
//
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    public String generateToken(UserDetails userDetails, String role) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("role", role); // Add role to the claims
//        String token = generateToken(claims, userDetails);
//        logger.info("Generated Token: {}", token);
//        return token;
//    }
//
//    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
//        String token = Jwts.builder()
//                .setClaims(extraClaims)
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // 30 minutes
//                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // Use HS256 for HMAC
//                .compact();
//        logger.info("Generated Token with Claims: {}", extraClaims);
//        return token;
//    }
//
//    public String generateToken(String email) {
//        String token = Jwts.builder()
//                .setSubject(email)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                .compact();
//        logger.info("Generated Token for email: {}", email);
//        return token;
//    }
//
//    public boolean isTokenValid(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        boolean isValid = (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
//        logger.info("Token valid: {}", isValid);
//        return isValid;
//    }
//
//    private boolean isTokenExpired(String token) {
//        boolean isExpired = extractExpiration(token).before(new Date());
//        logger.info("Token expired: {}", isExpired);
//        return isExpired;
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    private Claims extractAllClaims(String token) {
//        logger.debug("Extracting claims from token: {}", token);
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(getSignInKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//        logger.debug("Extracted claims: {}", claims);
//        return claims;
//    }
//
//    private Key getSignInKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//}

package com.ParamShavak.ParamShavak.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import java.security.Key;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service  // Marks this class as a Spring service to handle JWT-related operations
public class JwtService {

    // Secret key for signing and verifying JWT tokens. It must be long enough for the chosen algorithm (HS256)
    private static final String SECRET_KEY = "43315AF07B0D5BBBD7CC34C6AFF1994FA6510DEA6C968805DB64FA6A3160D87F6C398E755E3AF1DBE0840ED9EB1878AFC3B91D82FF0DF066755040ECA201316C02B979C740A9EAD0DFFC713A317708E055F0150F1F35D3F81CD3D872E95695969483346C2094EC089DC9B896714FBB493DC0C9B8CD034CE87A23C4F756DE13FD";

    // Token expiration time set to 30 minutes (1,800,000 milliseconds)
    private static final long EXPIRATION_TIME = 1_800_000;

    // Logger for logging important information and debugging messages
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    // Extracts the username (email) from the token by parsing its claims
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);  // The 'subject' claim typically holds the username/email
    }

    // Generic method to extract any specific claim from the token by applying a claims resolver function
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);  // Extracts all claims from the token
        return claimsResolver.apply(claims);  // Applies the resolver function to extract a specific claim
    }

    // Generates a JWT token with custom claims including the user role
    public String generateToken(UserDetails userDetails, String role) {
        Map<String, Object> claims = new HashMap<>();  // Map to hold additional claims (e.g., role)
        claims.put("role", role);  // Adding the user role to the claims
        String token = generateToken(claims, userDetails);  // Generate token with claims and user details
        logger.info("Generated Token: {}", token);  // Log the generated token
        return token;
    }

    // Generates a JWT token with additional claims, such as the user role, and sets the expiration time
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        String token = Jwts.builder()
                .setClaims(extraClaims)  // Set additional claims like user role
                .setSubject(userDetails.getUsername())  // Set the subject, typically the username (email)
                .setIssuedAt(new Date())  // Set the current time as the issue date of the token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))  // Set expiration time (30 minutes)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)  // Sign the token using HS256 algorithm and secret key
                .compact();  // Build and compact the JWT into a string format
        logger.info("Generated Token with Claims: {}", extraClaims);  // Log the generated token with claims
        return token;
    }

    // Generates a simple JWT token using the user's email with default expiration and signing
    public String generateToken(String email) {
        String token = Jwts.builder()
                .setSubject(email)  // Set the subject as the user's email
                .setIssuedAt(new Date())  // Set the token issue date
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // Set token expiration (30 minutes)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  // Sign the token using the secret key and HS256
                .compact();  // Compact the JWT into a string
        logger.info("Generated Token for email: {}", email);  // Log the token generated for the specific email
        return token;
    }

    // Validates the token by checking if it belongs to the correct user and if it has not expired
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);  // Extract the username from the token
        boolean isValid = (username.equals(userDetails.getUsername())) && !isTokenExpired(token);  // Check if username matches and token is not expired
        logger.info("Token valid: {}", isValid);  // Log the token validation result
        return isValid;  // Return the validation result
    }

    // Checks if the token is expired by comparing its expiration date with the current date
    private boolean isTokenExpired(String token) {
        boolean isExpired = extractExpiration(token).before(new Date());  // Compare token expiration date with current date
        logger.info("Token expired: {}", isExpired);  // Log if the token is expired or not
        return isExpired;  // Return whether the token is expired
    }

    // Extracts the expiration date from the token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);  // Extract the expiration claim from the token
    }

    // Extracts all claims from the token by parsing it using the signing key
    private Claims extractAllClaims(String token) {
        logger.debug("Extracting claims from token: {}", token);  // Log the start of claims extraction
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignInKey())  // Set the signing key used to parse the token
                .build()
                .parseClaimsJws(token)  // Parse the token and retrieve the claims
                .getBody();  // Get the body of the parsed claims
        logger.debug("Extracted claims: {}", claims);  // Log the extracted claims
        return claims;  // Return the claims
    }

    // Retrieves the signing key by decoding the base64-encoded secret key and creating an HMAC key
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);  // Decode the base64-encoded secret key
        return Keys.hmacShaKeyFor(keyBytes);  // Create and return the HMAC signing key
    }
}

