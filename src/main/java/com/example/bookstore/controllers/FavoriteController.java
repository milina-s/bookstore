package com.example.bookstore.controllers;

import com.example.bookstore.dto.book.BookDto;
import com.example.bookstore.dto.book.BookIsbn;
import com.example.bookstore.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/v1/bookstore/favorites")
public class FavoriteController {

    private final UserService userService;

    @GetMapping("/get_all")
    @Operation(
            description = "Get all favorites for current user",
            responses = {@ApiResponse(responseCode = "200", description = "Favorites retrieved successfully")}
    )
    public List<BookDto> getAllFavorites(Principal principal) {
        return userService.findAllFavorites(userService.findByEmail(principal.getName()));
    }

    @PutMapping("/add")
    @Operation(
            description = "Add book to favorites for current user",
            responses = {@ApiResponse(responseCode = "200", description = "Book added to favorites successfully")}
    )
    public void addFavorite(Principal principal, @RequestBody BookIsbn isbn) {
        System.out.println(isbn.getIsbn());
        userService.addFavorite(userService.findByEmail(principal.getName()), isbn);
    }

    @PutMapping("/delete")
    @Operation(
            description = "Delete book from favorites for current user",
            responses = {@ApiResponse(responseCode = "200", description = "Book deleted from favorites successfully")}
    )
    public void removeFavorite(Principal principal, @RequestBody BookIsbn isbn) {
        userService.deleteFavorite(userService.findByEmail(principal.getName()), isbn);
    }
}
