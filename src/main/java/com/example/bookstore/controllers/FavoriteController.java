package com.example.bookstore.controllers;

import com.example.bookstore.dto.BookDtoResponse;
import com.example.bookstore.services.FavoriteService;
import com.example.bookstore.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/bookstore/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final UserService userService;

    @GetMapping("/get_all")
    @Operation(
            description = "Get all favorites for current user",
            responses = {@ApiResponse(responseCode = "200", description = "Favorites retrieved successfully")}
    )
    public List<BookDtoResponse> getAllFavorites(Principal principal) {
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
