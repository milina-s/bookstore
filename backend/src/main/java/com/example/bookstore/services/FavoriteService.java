package com.example.bookstore.services;

import com.example.bookstore.model.Favorite;
import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.user.User;
import com.example.bookstore.repositories.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    public void addFavorite(Book book, User user) {
        favoriteRepository.save(
                Favorite.builder()
                        .book(book)
                        .user(user)
                        .build()
        );
    }

    public boolean deleteFavoriteByBookIsbnAndUserId(String isbn, Long userId) {
        if (!favoriteRepository.existsByBookIsbnAndUserId(isbn, userId)) return false;

        favoriteRepository.deleteFavoriteByBookIsbnAndUserId(isbn, userId);
        return true;
    }
}
