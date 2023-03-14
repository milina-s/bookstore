package com.example.bookstore.repositories;

import com.example.bookstore.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    
    boolean existsByBookIsbnAndUserId(String isbn, Long userId);

    void deleteFavoriteByBookIsbnAndUserId(String isbn, Long userId);
}
