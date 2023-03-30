package com.example.bookstore.controllers;

import com.example.bookstore.dto.user.UserDto;
import com.example.bookstore.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/v1/bookstore/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/get_profile_info")
    public UserDto getProfileInfo(Principal principal) {
        return userService.getProfileInfo(userService.findByEmail(principal.getName()));
    }
}
