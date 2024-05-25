package com.spring.quickstart;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldApplication {
    @GetMapping(path = "/")
    public String helloWorld()
    {
        return "Hello World!";
    }
}
