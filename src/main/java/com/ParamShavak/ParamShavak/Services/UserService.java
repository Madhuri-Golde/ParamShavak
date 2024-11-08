//package com.ParamShavak.ParamShavak.Services;
//
//import com.ParamShavak.ParamShavak.Model.Enquiry;
//import com.ParamShavak.ParamShavak.Model.User;
//import com.ParamShavak.ParamShavak.Repository.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepo userRepo;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public List<User> getAllUsers() {
//        return userRepo.findAll();
//    }
//
//    public User getUserById(String id) {
//        if (id == null) {
//            throw new IllegalArgumentException("User ID must not be null");
//        }
//        return userRepo.findById(id).orElse(null);
//    }
//
//    public User createUser(User user) {
//        if (user == null) {
//            throw new IllegalArgumentException("User must not be null");
//        }
//
//        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash the password
//        return userRepo.save(user);
//    }
//
//    public User updateUser(String id, User userDetails) {
//        if (id == null || userDetails == null) {
//            throw new IllegalArgumentException("ID and user details must not be null");
//        }
//        if (userRepo.existsById(id)) {
//            User existingUser = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
//
//            // Update fields
//            existingUser.setFirstname(userDetails.getFirstname());
//            existingUser.setLastname(userDetails.getLastname());
//            existingUser.setEmail(userDetails.getEmail());
//            existingUser.setGender(userDetails.getGender());
//            existingUser.setDateOfBirth(userDetails.getDateOfBirth());
//            existingUser.setInstituteName(userDetails.getInstituteName());
//            existingUser.setContactNumber(userDetails.getContactNumber());
//            existingUser.setCountry(userDetails.getCountry());
//            existingUser.setState(userDetails.getState());
//            existingUser.setCity(userDetails.getCity());
//            existingUser.setPincode(userDetails.getPincode());
//
//            // Update password if provided
//            if (userDetails.getPassword() != null) {
//                existingUser.setPassword(passwordEncoder.encode(userDetails.getPassword())); // Hash the new password
//            }
//
//            return userRepo.save(existingUser);
//        } else {
//            return null;
//        }
//    }
//
//    public boolean authenticate(String email, String password) {
//        if (email == null || password == null) {
//            throw new IllegalArgumentException("Email and password must not be null");
//        }
//        Optional<User> userOptional = userRepo.findByEmail(email);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            return passwordEncoder.matches(password, user.getPassword());
//        }
//        return false;
//    }
//
//    public User authenticateAndGetUser(String email, String password) {
//        Optional<User> userOptional = userRepo.findByEmail(email);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            if (passwordEncoder.matches(password, user.getPassword())) {
//                return user;
//            }
//        }
//        return null;
//    }
//
//
//    public void deleteUser(String id) {
//        if (id == null) {
//            throw new IllegalArgumentException("ID must not be null");
//        }
//        userRepo.deleteById(id);
//    }
//
//    public long getUserCount() {
//        return userRepo.count();
//    }
//
//    public Optional<User> getUserByEmail(String email) {
//        if (email == null) {
//            throw new IllegalArgumentException("Email must not be null");
//        }
//        return userRepo.findByEmail(email);
//    }
//
//    public void updateUserForQuotation(String id, User user) {
//        userRepo.save(user);
//    }
//
//
//
//
//    public void addEnquiryToUser(String email, Enquiry enquiry) {
//        Optional<User> userOptional = userRepo.findByEmail(email);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//
//            if (user.getEnquiries() == null) {
//                user.setEnquiries(new ArrayList<>());
//            }
//
//            // Ensure the enquiry has a unique ID
//            if (enquiry.getEnquiryId() == null || enquiry.getEnquiryId().isEmpty()) {
//                enquiry.setEnquiryId(UUID.randomUUID().toString());
//            }
//
//            user.getEnquiries().add(enquiry);
//            userRepo.save(user);  // Save the user with the new enquiry
//        }
//    }
//
//
//
//    public List<Enquiry> getUserEnquiriesByEmail(String email) {
//        // Fetch the user by email using Optional
//        Optional<User> userOptional = userRepo.findByEmail(email);
//
//        // Check if the user is present and has enquiries
//        if (userOptional.isPresent() && userOptional.get().getEnquiries() != null) {
//            return userOptional.get().getEnquiries();
//        }
//
//        return null;  // No enquiries found
//    }
//}


package com.ParamShavak.ParamShavak.Services;

import com.ParamShavak.ParamShavak.Model.Enquiry;
import com.ParamShavak.ParamShavak.Model.User;
import com.ParamShavak.ParamShavak.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    // Injecting the UserRepo to access user-related database operations
    @Autowired
    private UserRepo userRepo;

    // Injecting the PasswordEncoder to hash passwords
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Fetches all users from the database.
     *
     * @return a list of all users
     */
    public List<User> getAllUsers() {
        return userRepo.findAll(); // Returns all users from the repository
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return the user if found, otherwise null
     */
    public User getUserById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("User ID must not be null"); // Validate that ID is not null
        }
        return userRepo.findById(id).orElse(null); // Fetch user by ID, return null if not found
    }

    /**
     * Creates a new user and saves it to the database.
     *
     * @param user the user to be created
     * @return the saved user
     */
    public User createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User must not be null"); // Validate that user object is not null
        }

        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash the password before saving
        return userRepo.save(user); // Save the user and return the saved user object
    }

    /**
     * Updates an existing user with new details.
     *
     * @param id the ID of the user to be updated
     * @param userDetails the new user details to be applied
     * @return the updated user, or null if the user does not exist
     */
    public User updateUser(String id, User userDetails) {
        if (id == null || userDetails == null) {
            throw new IllegalArgumentException("ID and user details must not be null"); // Validate inputs
        }
        if (userRepo.existsById(id)) { // Check if user exists
            User existingUser = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found")); // Fetch existing user

            // Update user fields with the new values from userDetails
            existingUser.setFirstname(userDetails.getFirstname());
            existingUser.setLastname(userDetails.getLastname());
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setGender(userDetails.getGender());
            existingUser.setDateOfBirth(userDetails.getDateOfBirth());
            existingUser.setInstituteName(userDetails.getInstituteName());
            existingUser.setContactNumber(userDetails.getContactNumber());
            existingUser.setCountry(userDetails.getCountry());
            existingUser.setState(userDetails.getState());
            existingUser.setCity(userDetails.getCity());
            existingUser.setPincode(userDetails.getPincode());

            // Update password if provided in userDetails
            if (userDetails.getPassword() != null) {
                existingUser.setPassword(passwordEncoder.encode(userDetails.getPassword())); // Hash the new password
            }

            return userRepo.save(existingUser); // Save the updated user and return it
        } else {
            return null; // Return null if user does not exist
        }
    }

    /**
     * Authenticates a user using their email and password.
     *
     * @param email the email of the user
     * @param password the password provided by the user
     * @return true if authentication is successful, false otherwise
     */
    public boolean authenticate(String email, String password) {
        if (email == null || password == null) {
            throw new IllegalArgumentException("Email and password must not be null"); // Validate inputs
        }
        Optional<User> userOptional = userRepo.findByEmail(email); // Fetch user by email
        if (userOptional.isPresent()) { // If user is found
            User user = userOptional.get();
            return passwordEncoder.matches(password, user.getPassword()); // Check if the password matches
        }
        return false; // Return false if user is not found
    }

    /**
     * Authenticates a user and returns the user object if successful.
     *
     * @param email the email of the user
     * @param password the password provided by the user
     * @return the authenticated user, or null if authentication fails
     */
    public User authenticateAndGetUser(String email, String password) {
        Optional<User> userOptional = userRepo.findByEmail(email); // Fetch user by email
        if (userOptional.isPresent()) { // If user is found
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) { // Check if the password matches
                return user; // Return the authenticated user
            }
        }
        return null; // Return null if authentication fails
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     */
    public void deleteUser(String id) {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null"); // Validate that ID is not null
        }
        userRepo.deleteById(id); // Delete the user by ID
    }

    /**
     * Gets the total count of users.
     *
     * @return the count of users
     */
    public long getUserCount() {
        return userRepo.count(); // Return the total user count
    }

    /**
     * Retrieves a user by their email.
     *
     * @param email the email of the user to retrieve
     * @return an Optional containing the user if found, otherwise empty
     */
    public Optional<User> getUserByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email must not be null"); // Validate that email is not null
        }
        return userRepo.findByEmail(email); // Fetch user by email
    }

    /**
     * Updates a user specifically for quotation purposes.
     *
     * @param id the ID of the user to update
     * @param user the user object containing updated details
     */
    public void updateUserForQuotation(String id, User user) {
        userRepo.save(user); // Save the user with updated details
    }

    /**
     * Adds an enquiry to a user's account by their email.
     *
     * @param email the email of the user
     * @param enquiry the enquiry to be added
     */
    public void addEnquiryToUser(String email, Enquiry enquiry) {
        Optional<User> userOptional = userRepo.findByEmail(email); // Fetch user by email
        if (userOptional.isPresent()) { // If user is found
            User user = userOptional.get();

            // Initialize enquiries list if it's null
            if (user.getEnquiries() == null) {
                user.setEnquiries(new ArrayList<>()); // Create a new list if it doesn't exist
            }

            // Ensure the enquiry has a unique ID
            if (enquiry.getEnquiryId() == null || enquiry.getEnquiryId().isEmpty()) {
                enquiry.setEnquiryId(UUID.randomUUID().toString()); // Generate a new unique ID for the enquiry
            }

            user.getEnquiries().add(enquiry); // Add the enquiry to the user's enquiries
            userRepo.save(user); // Save the updated user
        }
    }

    /**
     * Retrieves all enquiries for a user by their email.
     *
     * @param email the email of the user
     * @return a list of enquiries or null if none are found
     */
    public List<Enquiry> getUserEnquiriesByEmail(String email) {
        // Fetch the user by email using Optional
        Optional<User> userOptional = userRepo.findByEmail(email);

        // Check if the user is present and has enquiries
        if (userOptional.isPresent() && userOptional.get().getEnquiries() != null) {
            return userOptional.get().getEnquiries(); // Return the user's enquiries
        }

        return null; // Return null if no enquiries found
    }
}
