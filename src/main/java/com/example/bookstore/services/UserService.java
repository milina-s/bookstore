package com.example.bookstore.services;

import com.example.bookstore.constants.ErrorMessage;
import com.example.bookstore.model.user.User;
import com.example.bookstore.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findByEmail(String email) {
        log.info("Find user by email: {}", email);

        return userRepository.findByEmail(email).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.USER_NOT_FOUND_BY_EMAIL + email)
        );
    }
}
