package com.kiit_sap_app.api.service;

import com.kiit_sap_app.api.entity.UserEntity;
import com.kiit_sap_app.api.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Save a new user entry with encrypted password
    public void saveNewUser(UserEntity userEntry){
        // Encrypt the password before saving
        userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
        userRepository.save(userEntry);
    }
}
