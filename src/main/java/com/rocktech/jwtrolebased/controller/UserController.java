package com.rocktech.jwtrolebased.controller;

import com.rocktech.jwtrolebased.entity.Role;
import com.rocktech.jwtrolebased.entity.User;
import com.rocktech.jwtrolebased.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRolesAndUser(){
        userService.initRolesAndUser();
    }

    @PostMapping("/register-user")
    public User createUser(@RequestBody User user){
        return  userService.registerNewUser(user);
    }

    @GetMapping("/for-admin")
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to admin";
    }

    @GetMapping("/for-user")
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to user";
    }

    @GetMapping("/for-all")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public String forAll(){
        return "This URL is accessible to both user and admin";
    }
}
