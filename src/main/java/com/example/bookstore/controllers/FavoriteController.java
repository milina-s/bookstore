package com.example.bookstore.controllers;

import com.example.bookstore.dto.book.BookDto;
import com.example.bookstore.services.FavoriteService;
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

    private final FavoriteService favoriteService;
    private final UserService userService;

    @GetMapping("/get_all_for_current_user")
    @Operation(
            description = "Get all favorites for current user",
            responses = {@ApiResponse(responseCode = "200", description = "Favorites retrieved successfully")}
    )
    public List<BookDto> getAllFavorites(Principal principal) {
        return favoriteService.findAllFavorites(userService.findByEmail(principal.getName()));
    }

    @PutMapping("/add_or_remove")
    @Operation(
            description = "Add or remove favorite for current user",
            responses = {@ApiResponse(responseCode = "200", description = "Favorite added or removed successfully")}
    )
    public void addFavorite(Principal principal, String isbn) {
        favoriteService.addOrRemoveFavorite(userService.findByEmail(principal.getName()), isbn);
    }
}
