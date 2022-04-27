package com.example.businessapp;

import com.example.businessapp.entity.User;
import com.example.businessapp.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class BusinessAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessAppApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
           userService.saveUser(new User(1L, "admin", "admin", "admin", "admin@gmail.com", "SUPERADMIN", new ArrayList<>(), true));
        };
    }
}
