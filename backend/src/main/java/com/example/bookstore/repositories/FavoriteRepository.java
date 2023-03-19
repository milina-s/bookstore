package com.example.bookstore.repositories;

import com.example.bookstore.model.favorite.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    
    boolean existsByBookIsbnAndUserId(String isbn, Long userId);

    void deleteFavoriteByBookIsbnAndUserId(String isbn, Long userId);

    List<Favorite> findAllByUserId(Long userId);
}
