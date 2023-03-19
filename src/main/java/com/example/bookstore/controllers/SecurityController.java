package com.example.bookstore.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/security")
public class SecurityController {

//    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String user() {
        return "Hi, USER";
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "Hi, ADMIN";
    }
}
