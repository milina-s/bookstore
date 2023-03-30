package com.example.bookstore.controllers;

import com.example.bookstore.dto.review.ReviewDto;
import com.example.bookstore.dto.review.ReviewDtoRequest;
import com.example.bookstore.services.ReviewService;
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
@RequestMapping("/api/v1/bookstore/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;

    @GetMapping("/get_all_for_book/{isbn}")
    @Operation(
            description = "Get all reviews for book",
            responses = {@ApiResponse(responseCode = "200", description = "Reviews retrieved successfully")}
    )
    public List<ReviewDto> getAllReviewsByBook(@PathVariable String isbn) {
        return reviewService.findAllReviewsByBook(isbn);
    }

    @PutMapping("/save")
    @Operation(
            description = "Save review for book",
            responses = {@ApiResponse(responseCode = "200", description = "Review saved successfully")}
    )
    public void addReview(Principal principal, @RequestBody ReviewDtoRequest review) {
        reviewService.saveReview(userService.findByEmail(principal.getName()), review);
    }
}
