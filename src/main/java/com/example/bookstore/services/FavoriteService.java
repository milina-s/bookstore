package com.example.bookstore.services;

import com.example.bookstore.dto.BookDtoResponse;
import com.example.bookstore.model.favorite.Favorite;
import com.example.bookstore.model.user.User;
import com.example.bookstore.repositories.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final BookService bookService;
    private final ModelMapper modelMapper;

    public List<BookDtoResponse> findAllFavorites(User user) {
        log.info("in findAllFavorites(): userId = {}", user.getId());
        return favoriteRepository.findAllByUserId(user.getId())
                .stream()
                .map(favorite -> modelMapper.map(favorite.getBook(), BookDtoResponse.class))
                .toList();
    }


    public void addOrRemoveFavorite(User user, String isbn) {
        log.info("in addOrRemoveFavorite(): bookIsbn = {}, userId = {}", isbn, user.getId());
        if (favoriteRepository.existsByBookIsbnAndUserId(isbn, user.getId())) {
            favoriteRepository.deleteFavoriteByBookIsbnAndUserId(isbn, user.getId());
            log.info("Favorite deleted");
        } else {
            favoriteRepository.save(
                    Favorite.builder()
                            .book(bookService.findBookByIsbn(isbn))
                            .user(user)
                            .build()
            );
            log.info("Favorite added");
        }
    }
}
