package com.example.bookstore.services;

import com.example.bookstore.constants.ErrorMessage;
import com.example.bookstore.constants.LogMessage;
import com.example.bookstore.model.user.User;
import com.example.bookstore.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Slf4j

@Service
public class UserService {

    private final UserRepository userRepository;

    public User findByEmail(String email) {
        log.info(LogMessage.IN_FIND_BY_EMAIL, User.class, email);

        return userRepository.findByEmail(email).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.USER_NOT_FOUND_BY_EMAIL + email)
        );
    }

}
