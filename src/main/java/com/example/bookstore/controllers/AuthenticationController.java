package com.example.bookstore.controllers;

import com.example.bookstore.dto.auth.AuthenticationRequest;
import com.example.bookstore.dto.auth.AuthenticationResponse;
import com.example.bookstore.dto.auth.RegisterRequest;
import com.example.bookstore.security.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/bookstore/auth")
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    @Operation(
            description = "Register user by email and password",
            responses = {@ApiResponse(responseCode = "200", description = "User registered")}
    )
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    @Operation(
            description = "Authenticate user by email and password",
            responses = {@ApiResponse(responseCode = "200", description = "User authenticated")}
    )
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
