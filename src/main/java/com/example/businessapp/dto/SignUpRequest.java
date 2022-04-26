package com.example.businessapp.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class SignUpRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;


    @NotBlank
    private String name;


    @Email
    private String email;
}
