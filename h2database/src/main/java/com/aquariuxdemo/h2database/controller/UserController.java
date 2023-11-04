package com.aquariuxdemo.h2database.controller;

import com.aquariuxdemo.h2database.entity.User;
import com.aquariuxdemo.h2database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User newUser) {
        // No Validation needed for my demo as per question
        userRepository.save(newUser);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }


}