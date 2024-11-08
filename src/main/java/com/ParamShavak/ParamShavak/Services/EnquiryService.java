//    package com.ParamShavak.ParamShavak.Services;
//
//    import com.ParamShavak.ParamShavak.Model.Enquiry;
//    import com.ParamShavak.ParamShavak.Model.User;
//    import com.ParamShavak.ParamShavak.Repository.EnquiryRepository;
//    import com.ParamShavak.ParamShavak.Repository.UserRepo;
//    import com.mongodb.client.gridfs.GridFSBucket;
//    import org.bson.types.ObjectId;
//    import org.springframework.beans.factory.annotation.Autowired;
//    import org.springframework.http.HttpStatus;
//    import org.springframework.stereotype.Service;
//    import org.springframework.web.server.ResponseStatusException;
//
//    import java.util.*;
//
//    @Service
//    public class EnquiryService {
//
//        @Autowired
//        private UserRepo userRepository;
//
//        @Autowired
//        private EnquiryRepository enquiryRepository;
//
//
//        @Autowired
//        private GridFSBucket gridFSBucket;
//
//
//        public Optional<Enquiry> findByEnquiryId(String enquiryId) {
//            return enquiryRepository.findByEnquiriesEnquiryId(enquiryId)
//                    .map(user -> user.getEnquiries().stream()
//                            .filter(enquiry -> enquiry.getEnquiryId().equals(enquiryId))
//                            .findFirst()
//                            .orElse(null));
//        }
//
//
//
//
//        public List<Map<String, Object>> getAllEnquiries() {
//            List<User> usersWithEnquiries = userRepository.findByEnquiriesNotNull();
//            List<Map<String, Object>> result = new ArrayList<>();
//
//            for (User user : usersWithEnquiries) {
//                Map<String, Object> userEnquiries = new HashMap<>();
//                userEnquiries.put("username", user.getFirstname() + " " + user.getLastname());
//                userEnquiries.put("enquiries", user.getEnquiries());
//                result.add(userEnquiries);
//            }
//
//            return result;
//        }
//
//        public boolean deleteEnquiry(String enquiryId) {
//            Optional<User> userOptional = enquiryRepository.findByEnquiriesEnquiryId(enquiryId);
//
//            if (userOptional.isPresent()) {
//                User user = userOptional.get();
//                List<Enquiry> updatedEnquiries = new ArrayList<>(user.getEnquiries());
//
//                boolean removed = updatedEnquiries.removeIf(enquiry -> enquiry.getEnquiryId().equals(enquiryId));
//
//                if (removed) {
//                    user.setEnquiries(updatedEnquiries);
//                    userRepository.save(user);
//                    return true;
//                }
//            }
//
//            return false; // Enquiry not found
//        }
//
//
//        public int getEnquiriesCount() {
//            List<User> allUsers = userRepository.findAll(); // Fetch all users from the repository
//            int totalEnquiries = 0;
//            for (User user : allUsers) {
//                totalEnquiries += user.getEnquiries().size(); // Only count actual enquiries
//            }
//            return totalEnquiries;
//        }
//
//
//        public boolean deletePdfFromEnquiry(String enquiryId) {
//            // Find the user who has this enquiry
//            Optional<User> userOptional = enquiryRepository.findByEnquiriesEnquiryId(enquiryId);
//
//            if (userOptional.isPresent()) {
//                User user = userOptional.get();
//                List<Enquiry> enquiries = user.getEnquiries();
//
//                // Find the specific enquiry with the PDF
//                for (Enquiry enquiry : enquiries) {
//                    if (enquiry.getEnquiryId().equals(enquiryId)) {
//                        String pdfFileId = enquiry.getPdfFileId();
//
//                        if (pdfFileId != null) {
//                            // Delete the file from GridFS
//                            ObjectId gridFsId = new ObjectId(pdfFileId);
//                            gridFSBucket.delete(gridFsId);
//
//                            // Remove PDF reference from the enquiry
//                            enquiry.setPdfFileId(null);
//                            userRepository.save(user);  // Save the updated user
//
//                            return true;  // PDF deleted successfully
//                        } else {
//                            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No PDF associated with this enquiry");
//                        }
//                    }
//                }
//            }
//
//            return false; // Enquiry or PDF not found
//        }
//
//        // Method to count enquiries with an associated PDF
//        public int getEnquiriesWithPdfCount() {
//            List<User> allUsers = userRepository.findAll(); // Fetch all users from the repository
//            int totalEnquiriesWithPdf = 0;
//
//            System.out.println("Total users found: " + allUsers.size()); // Log the number of users
//
//            for (User user : allUsers) {
//                if (user.getEnquiries() != null) {
//                    System.out.println("User: " + user.getFirstname() + " " + user.getLastname() + " has enquiries: " + user.getEnquiries().size());
//                    for (Enquiry enquiry : user.getEnquiries()) {
//                        if (enquiry.getPdfFileId() != null && !enquiry.getPdfFileId().isEmpty()) {
//                            totalEnquiriesWithPdf++;
//                        }
//                    }
//                }
//            }
//
//            System.out.println("Total enquiries with PDF: " + totalEnquiriesWithPdf); // Log the count
//            return totalEnquiriesWithPdf;
//        }
//
//
//
//
//    }

package com.ParamShavak.ParamShavak.Services;

import com.ParamShavak.ParamShavak.Model.Enquiry;
import com.ParamShavak.ParamShavak.Model.User;
import com.ParamShavak.ParamShavak.Repository.EnquiryRepository;
import com.ParamShavak.ParamShavak.Repository.UserRepo;
import com.mongodb.client.gridfs.GridFSBucket;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class EnquiryService {

    // Autowired repositories for user and enquiry data management
    @Autowired
    private UserRepo userRepository;

    @Autowired
    private EnquiryRepository enquiryRepository;

    @Autowired
    private GridFSBucket gridFSBucket;

    /**
     * Finds an enquiry by its ID.
     *
     * @param enquiryId the ID of the enquiry to find
     * @return an Optional containing the found enquiry or empty if not found
     */
    public Optional<Enquiry> findByEnquiryId(String enquiryId) {
        // Look up a user by the enquiry ID
        return enquiryRepository.findByEnquiriesEnquiryId(enquiryId)
                // If the user is found, filter their enquiries to find the specific enquiry
                .map(user -> user.getEnquiries().stream()
                        .filter(enquiry -> enquiry.getEnquiryId().equals(enquiryId)) // Match the enquiry ID
                        .findFirst() // Get the first matching enquiry
                        .orElse(null)); // Return null if not found
    }

    /**
     * Retrieves all enquiries associated with users.
     *
     * @return a list of maps containing user names and their enquiries
     */
    public List<Map<String, Object>> getAllEnquiries() {
        // Get a list of users who have enquiries
        List<User> usersWithEnquiries = userRepository.findByEnquiriesNotNull();
        // Create a list to store results
        List<Map<String, Object>> result = new ArrayList<>();

        // Loop through each user with enquiries
        for (User user : usersWithEnquiries) {
            // Create a map to hold user and their enquiries
            Map<String, Object> userEnquiries = new HashMap<>();
            // Add the user's full name to the map
            userEnquiries.put("username", user.getFirstname() + " " + user.getLastname());
            // Add the user's enquiries to the map
            userEnquiries.put("enquiries", user.getEnquiries());
            // Add the map to the result list
            result.add(userEnquiries);
        }

        return result; // Return the complete list of user enquiries
    }

    /**
     * Deletes an enquiry by its ID.
     *
     * @param enquiryId the ID of the enquiry to delete
     * @return true if the enquiry was deleted, false otherwise
     */
    public boolean deleteEnquiry(String enquiryId) {
        // Find the user that has the enquiry with the given ID
        Optional<User> userOptional = enquiryRepository.findByEnquiriesEnquiryId(enquiryId);

        // If the user is found
        if (userOptional.isPresent()) {
            User user = userOptional.get(); // Get the user
            // Create a new list from the user's enquiries
            List<Enquiry> updatedEnquiries = new ArrayList<>(user.getEnquiries());

            // Attempt to remove the enquiry with the given ID
            boolean removed = updatedEnquiries.removeIf(enquiry -> enquiry.getEnquiryId().equals(enquiryId));

            // If the enquiry was successfully removed
            if (removed) {
                user.setEnquiries(updatedEnquiries); // Update the user's enquiries
                userRepository.save(user); // Save the updated user back to the repository
                return true; // Indicate that the deletion was successful
            }
        }

        return false; // If the enquiry was not found, return false
    }

    /**
     * Counts the total number of enquiries across all users.
     *
     * @return the total number of enquiries
     */
    public int getEnquiriesCount() {
        // Fetch all users from the repository
        List<User> allUsers = userRepository.findAll();
        int totalEnquiries = 0; // Initialize the count of enquiries

        // Loop through each user
        for (User user : allUsers) {
            // Count the number of enquiries for each user and add to the total
            totalEnquiries += user.getEnquiries().size(); // Only count actual enquiries
        }
        return totalEnquiries; // Return the total count of enquiries
    }

    /**
     * Deletes a PDF file associated with a specific enquiry.
     *
     * @param enquiryId the ID of the enquiry whose PDF should be deleted
     * @return true if the PDF was deleted successfully, false otherwise
     */
    public boolean deletePdfFromEnquiry(String enquiryId) {
        // Find the user who has this enquiry
        Optional<User> userOptional = enquiryRepository.findByEnquiriesEnquiryId(enquiryId);

        // If the user is found
        if (userOptional.isPresent()) {
            User user = userOptional.get(); // Get the user
            List<Enquiry> enquiries = user.getEnquiries(); // Get the user's enquiries

            // Loop through each enquiry
            for (Enquiry enquiry : enquiries) {
                // Check if the enquiry ID matches the specified ID
                if (enquiry.getEnquiryId().equals(enquiryId)) {
                    String pdfFileId = enquiry.getPdfFileId(); // Get the associated PDF file ID

                    // If there is an associated PDF file ID
                    if (pdfFileId != null) {
                        // Convert the PDF file ID to an ObjectId for GridFS deletion
                        ObjectId gridFsId = new ObjectId(pdfFileId);
                        // Delete the file from GridFS storage
                        gridFSBucket.delete(gridFsId);
                        // Remove the PDF reference from the enquiry
                        enquiry.setPdfFileId(null);
                        // Save the updated user
                        userRepository.save(user);
                        return true; // Indicate that the PDF was deleted successfully
                    } else {
                        // Throw an exception if there is no PDF associated with the enquiry
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No PDF associated with this enquiry");
                    }
                }
            }
        }

        return false; // If the enquiry or PDF was not found, return false
    }

    /**
     * Counts the total number of enquiries that have an associated PDF file.
     *
     * @return the count of enquiries with associated PDFs
     */
    public int getEnquiriesWithPdfCount() {
        // Fetch all users from the repository
        List<User> allUsers = userRepository.findAll();
        int totalEnquiriesWithPdf = 0; // Initialize the count for enquiries with PDFs

        // Log the total number of users found
        System.out.println("Total users found: " + allUsers.size());

        // Loop through each user
        for (User user : allUsers) {
            // Check if the user has enquiries
            if (user.getEnquiries() != null) {
                // Log user information and the number of their enquiries
                System.out.println("User: " + user.getFirstname() + " " + user.getLastname() + " has enquiries: " + user.getEnquiries().size());
                // Loop through each enquiry
                for (Enquiry enquiry : user.getEnquiries()) {
                    // Check if the enquiry has an associated PDF file ID
                    if (enquiry.getPdfFileId() != null && !enquiry.getPdfFileId().isEmpty()) {
                        totalEnquiriesWithPdf++; // Increment the count for enquiries with PDFs
                    }
                }
            }
        }

        // Log the total count of enquiries with PDFs
        System.out.println("Total enquiries with PDF: " + totalEnquiriesWithPdf);
        return totalEnquiriesWithPdf; // Return the total count
    }
}
