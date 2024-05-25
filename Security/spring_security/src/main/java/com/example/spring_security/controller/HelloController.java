package com.example.spring_security.controller;

import com.example.spring_security.domain.entities.UserEntity;
import com.example.spring_security.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@Log
public class HelloController {
    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
    @Autowired
    private UserRepository userRepository;
    @RequestMapping(path = "/home")
    public String sayHello(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Optional<UserEntity> user = userRepository.findByusername(username);
        UserEntity userEntity = new UserEntity();
        if(user.isPresent()){
            userEntity = user.get();
        }
        else {
            System.out.println("null");
        }
        int userId = userEntity.getId();
        model.addAttribute("user", userEntity);
        log.info(String.valueOf(userId));
        return "index";
    }
    @GetMapping(path = "/login")
    public String loginPage(Model model){
        return "login";
    }
    @PostMapping(path = "/logout")
    public String logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response, Model model){
        this.logoutHandler.logout(request, response, authentication);
        model.addAttribute("logout", "You have logged out!");
        return loginPage(model);
    }
    @GetMapping(path = "/profile")
    public String profile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Optional<UserEntity> user = userRepository.findByusername(username);
        UserEntity userEntity = new UserEntity();
        if(user.isPresent()){
            userEntity = user.get();
        }
        else {
            System.out.println("null");
        }
        int userId = userEntity.getId();
        model.addAttribute("user", userEntity);
        return "profile";
    }
}
