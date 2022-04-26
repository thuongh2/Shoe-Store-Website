package com.example.businessapp.service;

import com.example.businessapp.dto.SignUpRequest;
import com.example.businessapp.dto.UserDto;
import com.example.businessapp.entity.User;

import java.util.List;

public interface UserService {

    //get all user (super-admin)
    List<UserDto> getUsers();

    UserDto getUserByUsername(String username);

    boolean hasUserWithUserName(String username);

    boolean hasUserWithEmail(String email);

    void saveUser(User user);

    void deleteUser(Long id);

    void deleteUserByAdmin(Long id);

    UserDto getCurrentUser(String username);

    // update information of product
    UserDto updateUser(SignUpRequest signUpRequest);

    UserDto changeUserRole(String username, String role);
}
