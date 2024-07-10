package com.ParamShavak.ParamShavak.Repository;

import com.ParamShavak.ParamShavak.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User,String> {

}
