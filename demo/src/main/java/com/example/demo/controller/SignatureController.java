package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller

public class SignatureController {
	@GetMapping("/")
    public String homepage() {
        return "redirect:/sign-file";
    }

    @GetMapping("/sign-file")
    public String signFile() {
        return "sign-file-form";
    }


    // xac thuc file
    @GetMapping("/verify-file")
    public String verifyFileForm() {
        return "verify-file-form";
    }

    @PostMapping("/verify-file")
    public String verifyFile(
            Model model
            ,@RequestParam("file") MultipartFile file
            ,@RequestParam("publickey") MultipartFile publickey_file
            ) {

        return "verify-file-form";
    }
}
