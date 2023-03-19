package com.example.bookstore.repositories;

import com.example.bookstore.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByBookIsbn(String isbn);
}
