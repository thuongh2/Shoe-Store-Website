package com.example.businessapp.repository;

import com.example.businessapp.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByName(String name);

    User findByUsernameAndPassword(String username, String password);
}
