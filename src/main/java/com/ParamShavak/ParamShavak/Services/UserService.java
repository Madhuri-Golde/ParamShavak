package com.ParamShavak.ParamShavak.Services;

import com.ParamShavak.ParamShavak.Model.User;
import com.ParamShavak.ParamShavak.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(String id) {
        return userRepo.findById(id).orElse(null);
    }

    public User createUser(User user) {
        System.out.println("Creating user: " + user);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash the password
        return userRepo.save(user);
    }


    public User updateUser(String id, User user) {
        if (userRepo.existsById(id)) {
            user.setId(id);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepo.save(user);
        } else {
            return null;
        }
    }

    public boolean authenticate(String email, String password) {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    public void deleteUser(String id) {
        userRepo.deleteById(id);
    }

    public long getUserCount() {
        return userRepo.count();
    }
}
