package com.example.extend_templates.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @RequestMapping
    public String base(){
        return "base";
    }
    @RequestMapping(path = "/sub")
    public String sub(){
        return "derived";
    }
}
