package com.example.businessapp.securiry;

import com.example.businessapp.entity.User;
import com.example.businessapp.exception.NotFoundException;
import com.example.businessapp.repository.UserRepository;
import com.example.businessapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userService.getUserByUsername(username)
//                .orElseThrow(()->new NotFoundException(String.format("Username %s not found", username)));

        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User " + username + "not found");
        }

        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));

        // return user and list role for spring security
        return mapUserToCustomUserDetails(user, authorities);
    }

    //map user to customUser of spring securiry
    private CustomUserDetails mapUserToCustomUserDetails(User user, List<GrantedAuthority> authorities){
        CustomUserDetails customUserDetails = new CustomUserDetails();

        // set attribute
        customUserDetails.setId(user.getId());
        customUserDetails.setUsername(user.getUsername());
        customUserDetails.setPassword(user.getPassword());
        customUserDetails.setName(user.getName());
        customUserDetails.setEmail(user.getEmail());
        customUserDetails.setAuthorities(authorities);

        return customUserDetails;
    }
}
