package com.example.bookstore.services;

import com.example.bookstore.model.user.User;
import com.example.bookstore.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public class UserService {

    private final UserRepository userRepository;

    public void addUser(User user) {
        userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Long getUserIdByEmail(String email) {
        return userRepository.
                findIdByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
