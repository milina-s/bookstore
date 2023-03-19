package com.example.bookstore.controllers;

import com.example.bookstore.dto.ReviewDtoRequest;
import com.example.bookstore.dto.ReviewDtoResponse;
import com.example.bookstore.services.ReviewService;
import com.example.bookstore.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/bookstore/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;

    @GetMapping("/get_all_by_book/{isbn}")
    public List<ReviewDtoResponse> getAllReviewsByBook(@PathVariable String isbn) {
        return reviewService.findAllReviewsByBook(isbn);
    }

    @PutMapping("/save_review/{isbn}")
    public void addReview(Principal principal, @PathVariable String isbn, @RequestBody ReviewDtoRequest review) {
        reviewService.saveReview(userService.findByEmail(principal.getName()), isbn, review);
    }

}
