package com.example.bookstore.repositories;

import com.example.bookstore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByNameEn(String name);

    Optional<Category> findById (Long id);

}
