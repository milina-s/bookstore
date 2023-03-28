package com.example.bookstore.security;

import com.example.bookstore.constants.ErrorMessage;
import com.example.bookstore.dto.auth.AuthenticationRequest;
import com.example.bookstore.dto.auth.AuthenticationResponse;
import com.example.bookstore.dto.auth.RegisterRequest;
import com.example.bookstore.model.user.User;
import com.example.bookstore.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    public AuthenticationResponse register(RegisterRequest request) {

        User user = modelMapper.map(request, User.class);
        repository.save(user);
        return AuthenticationResponse.builder()
                .token(jwtService.generateToken(user))
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException(ErrorMessage.USER_NOT_FOUND + " " + request.getEmail()));
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}