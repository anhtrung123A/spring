package com.example.demo_sec.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@CrossOrigin
public class UserController {
    @GetMapping(path = "/hello")
    public String hello(){
        return "hello";
    }
}
