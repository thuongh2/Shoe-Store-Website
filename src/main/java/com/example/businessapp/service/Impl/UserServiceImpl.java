package com.example.businessapp.service.Impl;

import com.example.businessapp.dto.SignUpRequest;
import com.example.businessapp.dto.UserDto;
import com.example.businessapp.entity.User;
import com.example.businessapp.exception.NotFoundException;
import com.example.businessapp.repository.UserRepository;
import com.example.businessapp.service.UserService;
import com.example.businessapp.utils.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getUsers() {
        return ObjectMapperUtils.mapAll(userRepository.findUserByActiveIsTrue(), UserDto.class);
    }

    @Override
    public UserDto getCurrentUser(String username){
        return ObjectMapperUtils.map(userRepository.findByUsername(username), UserDto.class);
    }

    @Override
    public UserDto updateUser(SignUpRequest signUpRequest) {
        User user = userRepository.findByUsername(signUpRequest.getUsername());

        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }

        // update user
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setEmail(signUpRequest.getEmail());
        user.setName(signUpRequest.getName());

        return ObjectMapperUtils.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public UserDto changeUserRole(String username, String role) {
        User user = userRepository.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }

        role = role.toUpperCase().trim();

        if(role.equals("ADMIN") || role.equals("SUPERADMIN")){
            user.setRole(role);
        }

        return ObjectMapperUtils.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return ObjectMapperUtils.map(userRepository.findByUsername(username), UserDto.class);
    }

    @Override
    public boolean hasUserWithUserName(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean hasUserWithEmail(String email) {
        return userRepository.existsByEmail(email);
    }


    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User userDelete = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with username %s not found", id)));

        userDelete.setActive(!userDelete.isActive());

        userRepository.save(userDelete);
    }

    @Override
    public void deleteUserByAdmin(Long id) {
        User userDelete = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with username %s not found", id)));
        userRepository.delete(userDelete);
    }
}
