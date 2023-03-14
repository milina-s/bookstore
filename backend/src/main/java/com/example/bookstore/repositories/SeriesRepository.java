package com.example.bookstore.repositories;

import com.example.bookstore.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeriesRepository extends JpaRepository<Series, Long> {
    Optional<Series> findAllByPublisherId(Long publisherId);
}
