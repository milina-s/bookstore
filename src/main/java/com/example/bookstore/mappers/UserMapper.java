package com.example.bookstore.mappers;

import com.example.bookstore.dto.auth.RegisterRequest;
import com.example.bookstore.model.user.User;
import com.example.bookstore.model.user.UserRole;
import lombok.AllArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserMapper extends AbstractConverter<RegisterRequest, User> {

    private final PasswordEncoder passwordEncoder;

    @Override
    protected User convert(RegisterRequest registerRequest) {
        return User.builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(UserRole.ROLE_CUSTOMER)
                .build();
    }

}
