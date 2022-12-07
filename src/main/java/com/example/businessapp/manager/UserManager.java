package com.example.businessapp.manager;

import com.example.businessapp.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserManager extends BaseService {

    public User signIn(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null)
            return user;
        return null;
    }
}
