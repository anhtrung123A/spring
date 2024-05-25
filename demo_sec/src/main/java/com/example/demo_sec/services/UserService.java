package com.example.demo_sec.services;

import com.example.demo_sec.models.ApplicationUser;
import com.example.demo_sec.models.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Log
public class UserService implements UserDetailsService {

    private PasswordEncoder encoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("In the user details service");
        log.info(username);
        if (username.equals("Ethan")) throw new UsernameNotFoundException("Not ethan");
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1, "USER"));
        return new ApplicationUser(1, "Ethan", encoder.encode("password"), roles);
    }
}
