package com.example.businessapp.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "user")
public class User implements Serializable {
    @Id
    private String id;

    private String username;

    private String password;

    private String name;

    private String email;

    private String role;

    private boolean isActiveMail;

    private String avatar;

    private String eWallet;
}
