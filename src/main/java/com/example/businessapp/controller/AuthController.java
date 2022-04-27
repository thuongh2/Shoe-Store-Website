package com.example.businessapp.controller;

import com.example.businessapp.dto.AuthResponse;
import com.example.businessapp.dto.LoginRequest;
import com.example.businessapp.dto.SignUpRequest;
import com.example.businessapp.entity.User;
import com.example.businessapp.exception.DuplicatedUserInfoException;
import com.example.businessapp.securiry.TokenProvider;
import com.example.businessapp.service.UserService;
import com.example.businessapp.utils.ObjectMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
@Slf4j
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/authenticate")
    public AuthResponse login (@Valid @ModelAttribute LoginRequest loginRequest){
        // get token and return for client
        String token = authenticateAndGetToken(loginRequest.getUsername(), loginRequest.getPassword());

        return new AuthResponse(token);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public void signUp(@Valid @ModelAttribute SignUpRequest signUpRequest){
        if(userService.hasUserWithUserName(signUpRequest.getUsername())){
            throw new DuplicatedUserInfoException(String.format("Username %s already been used", signUpRequest.getUsername()));
        }

        if(userService.hasUserWithEmail(signUpRequest.getEmail())){
            throw new DuplicatedUserInfoException(String.format("Email %s already been used", signUpRequest.getEmail()));
        }

        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        User user = ObjectMapperUtils.map(signUpRequest, User.class);
        user.setRole("USER");
        user.setActive(true);

        userService.saveUser(user);
    }


    private String authenticateAndGetToken(String username, String password){
        // generate token in tokenProvider
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            return tokenProvider.generate(authentication);
        }catch (Exception e){
            log.info("Loi ngay day: "+ e.getMessage());
        }

        return null;
    }
}
