//package com.ParamShavak.ParamShavak.Services;
//import com.ParamShavak.ParamShavak.Model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailService implements UserDetailsService {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userService.getUserByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
//
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
//    }
//}


package com.ParamShavak.ParamShavak.Services;

import com.ParamShavak.ParamShavak.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    // Autowired UserService to fetch user information
    @Autowired
    private UserService userService;

    /**
     * Loads user details by username (email in this case).
     *
     * @param username the email of the user to be loaded
     * @return UserDetails containing user information for authentication
     * @throws UsernameNotFoundException if no user is found with the given email
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user by email using the userService
        User user = userService.getUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        // Return a Spring Security User object with username, password, and authorities
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),      // The user's username (email in this case)
                user.getPassword(),      // The user's password
                user.getAuthorities()    // The user's granted authorities (roles)
        );
    }
}

