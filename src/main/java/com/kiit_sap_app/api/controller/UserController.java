package com.kiit_sap_app.api.controller;

import com.kiit_sap_app.api.entity.UserEntity;
import com.kiit_sap_app.api.repository.UserRepository;
import com.kiit_sap_app.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private  UserService userService;

    @Autowired
    private UserRepository userRepository;

    // Endpoint to create a new user
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserEntity user) {
        if (userRepository.findByUsername(user.getUsername())!=null) {
            return ResponseEntity.badRequest().body("User with this username already exists.");
        }
        userService.saveNewUser(user);
        return ResponseEntity.ok("User created successfully!");
    }
}

