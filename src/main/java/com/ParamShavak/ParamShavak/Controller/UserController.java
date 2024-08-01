package com.ParamShavak.ParamShavak.Controller;

import Dto.LoginRequest;
import com.ParamShavak.ParamShavak.Model.User;
import com.ParamShavak.ParamShavak.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/users")
public class UserController {

        @Autowired
        UserService userService;

        @PostMapping
        public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
                User createdUser = userService.createUser(user);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        }

        @GetMapping("/{id}")
        public ResponseEntity<User> getUser(@PathVariable String id) {
                User user = userService.getUserById(id);
                if (user != null) {
                        return ResponseEntity.ok(user);
                } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                }
        }

        @GetMapping
        public ResponseEntity<List<User>> getAllUsers() {
                return ResponseEntity.ok(userService.getAllUsers());
        }

        @PutMapping("/{id}")
        public ResponseEntity<User> updateUser(@PathVariable String id, @Valid @RequestBody User user) {
                User updatedUser = userService.updateUser(id, user);
                if (updatedUser != null) {
                        return ResponseEntity.ok(updatedUser);
                } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteUser(@PathVariable String id) {
                userService.deleteUser(id);
                return ResponseEntity.noContent().build();
        }

        @PostMapping("/login")
        public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
                boolean isAuthenticated = userService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
                if (isAuthenticated) {
                        return ResponseEntity.ok("Login successful!");
                } else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
                }
        }

        public long getUserCount() {
                return userService.getUserCount();
        }


}
