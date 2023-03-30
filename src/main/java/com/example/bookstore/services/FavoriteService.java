package com.example.bookstore.services;

import com.example.bookstore.dto.book.BookDto;
import com.example.bookstore.model.favorite.Favorite;
import com.example.bookstore.model.user.User;
import com.example.bookstore.repositories.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final BookService bookService;
    private final ModelMapper modelMapper;

    public List<BookDto> findAllFavorites(User user) {
        log.info("Find all favorites for user with id: {}", user.getId());

        return favoriteRepository.findAllByUserId(user.getId())
                .stream()
                .map(favorite -> modelMapper.map(favorite.getBook(), BookDto.class))
                .toList();
    }


    public void addOrRemoveFavorite(User user, String isbn) {
        log.info("Add or remove favorite for user with id: {}, and book isbn {}", user.getId(), isbn);

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
