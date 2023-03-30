package com.example.bookstore.services;

import com.example.bookstore.constants.ErrorMessage;
import com.example.bookstore.dto.book.BookDto;
import com.example.bookstore.dto.book.BookIsbn;
import com.example.bookstore.model.user.User;
import com.example.bookstore.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BookService bookService;
    private final ModelMapper modelMapper;

    public User findByEmail(String email) {
        log.info("Find user by email: {}", email);

        return userRepository.findByEmail(email).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.USER_NOT_FOUND_BY_EMAIL + email)
        );
    }

    public List<BookDto> findAllFavorites(User user) {
        log.info("Find all favorites for user: {}", user.getEmail());

        return modelMapper.map(user.getFavouriteBooks(), new TypeToken<List<BookDto>>() {
        }.getType());
    }


    public void addFavorite(User user, BookIsbn isbn) {
        log.info("Add favorite for user: {}", user.getEmail());

        if (user.getFavouriteBooks().contains(bookService.findBookByIsbn(isbn.getIsbn())))
            throw new IllegalArgumentException(ErrorMessage.BOOK_ALREADY_IN_FAVORITES);

        user.getFavouriteBooks().add(bookService.findBookByIsbn(isbn.getIsbn()));
        userRepository.save(user);
    }

    public void deleteFavorite(User user, BookIsbn isbn) {
        log.info("Delete favorite for user: {}", user.getEmail());

        if (!user.getFavouriteBooks().contains(bookService.findBookByIsbn(isbn.getIsbn())))
            throw new IllegalArgumentException(ErrorMessage.BOOK_NOT_IN_FAVORITES);

        user.getFavouriteBooks().remove(bookService.findBookByIsbn(isbn.getIsbn()));
        userRepository.save(user);
    }
}
