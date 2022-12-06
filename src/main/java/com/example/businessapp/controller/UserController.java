//package com.example.businessapp.controller;
//
//
//import com.example.businessapp.dto.SignUpRequest;
//import com.example.businessapp.dto.UserDto;
//import com.example.businessapp.securiry.CustomUserDetails;
//import com.example.businessapp.service.UserService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//
//
//@RestController
//@RequestMapping("/api")
//@CrossOrigin(origins = "https://shoes-store-udtt.vercel.app/")
//@Slf4j
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/me")
//    public UserDto getCurrentUser(@AuthenticationPrincipal CustomUserDetails currentUser){
//        log.info(currentUser.getUsername());
//         return userService.getCurrentUser(currentUser.getUsername());
//    }
//
//    // super admin
//    @GetMapping("/s/users")
//    public List<UserDto> getUsers(){
//        return userService.getUsers();
//    }
//
////    //admin and super admin
////    @GetMapping("/s/users/{username}")
////    public UserDto getUserByUsername(@PathVariable(name = "username") String username){
////        return userService.getUserByUsername(username);
////    }
//
//    @GetMapping("/s/user/delete/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void deleteUser(@PathVariable Long id){
//        userService.deleteUser(id);
//    }
//
//    @GetMapping("/s/user/delete-sp/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void deleteUserByAdmin(@PathVariable Long id){
//        userService.deleteUserByAdmin(id);
//    }
//
//    @PostMapping("/user/update")
//    public UserDto updateUser(@Valid @ModelAttribute SignUpRequest signUpRequest){
//        return userService.updateUser(signUpRequest);
//    }
//
//    @PostMapping("/s/user/role")
//    public UserDto changeRole(@ModelAttribute(name = "username") String username,
//                              @ModelAttribute(name = "role") String role){
//        return userService.changeUserRole(username, role);
//    }
//
//
//}
//
//
