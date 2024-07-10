package com.ParamShavak.ParamShavak.Services;


import com.ParamShavak.ParamShavak.Model.User;
import com.ParamShavak.ParamShavak.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User>getAllUsers(){
        return userRepo.findAll();
    }

    public User getUserById(String id){
        return userRepo.findById(id).orElse(null);
    }

    public User createUser(User user){
        return userRepo.save(user);
    }

    public User updateUser(String id, User user){
        if(userRepo.existsById(id)){
            user.setId(id);
            return userRepo.save(user);
        }
        else{
            return null;
        }
    }

    public void deleteUser(String id){
        userRepo.deleteById(id);
    }
}
