package com.example.spring_security.controller;

import com.example.spring_security.domain.entities.UserEntity;
import com.example.spring_security.repositories.ProductRepository;
import com.example.spring_security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Security;

@RestController
public class Controller {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/user/save")
    public ResponseEntity<Object> saveUser(@RequestBody UserEntity user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity result = userRepository.save(user);
        if (result.getId()>0){
            return ResponseEntity.ok("User was saved");
        }
        else {
            return ResponseEntity.status(404).body("Error, user not saved");
        }
    }
    @GetMapping("/product/all")
    public ResponseEntity<Object> getAllProducts(){
        return ResponseEntity.ok(productRepository.findAll());
    }
    @GetMapping("/user/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getAllUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }
    @GetMapping("/user/single")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Object> getOneUser(){
        return ResponseEntity.ok(userRepository.findByusername(getLoggedInUserDetails().getUsername()));
    }
    public UserDetails getLoggedInUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }
}
