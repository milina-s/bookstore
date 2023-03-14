package com.example.bookstore.repositories;

import com.example.bookstore.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail (String email);

    Optional<Long> findIdByEmail (String email);

}
