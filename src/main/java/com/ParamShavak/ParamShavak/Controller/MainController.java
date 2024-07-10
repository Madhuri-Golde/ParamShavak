package com.ParamShavak.ParamShavak.Controller;

import com.ParamShavak.ParamShavak.Model.User;
import com.ParamShavak.ParamShavak.Repository.UserRepo;
import com.ParamShavak.ParamShavak.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/users")
public class MainController {

        @Autowired
        UserService userService;

        @PostMapping
        public User addUser(@Valid @RequestBody User user) {
              return userService.createUser(user);
        }

        @GetMapping("/{id}")
        public User getUser(@PathVariable String id){
              return userService.getUserById(id);
        }

        @GetMapping
        public List<User>getAllUsers(){
                return userService.getAllUsers();
        }

        @PutMapping("/{id}")
        public User updateUser(@PathVariable String id, @Valid @RequestBody User user){
                return userService.updateUser(id, user);
        }

        @DeleteMapping("/{id}")
        public void deleteUser(@PathVariable String id){
                userService.deleteUser(id);
        }
}
