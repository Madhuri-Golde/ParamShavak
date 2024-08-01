package com.ParamShavak.ParamShavak.Repository;

import com.ParamShavak.ParamShavak.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
