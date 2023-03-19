package com.example.bookstore.services;

import com.example.bookstore.dto.ReviewDtoRequest;
import com.example.bookstore.dto.ReviewDtoResponse;
import com.example.bookstore.model.Review;
import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.user.User;
import com.example.bookstore.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookService bookService;

    public List<ReviewDtoResponse> findAllReviewsByBook(String isbn) {
        log.info("in findAllReviewsByBook(): bookIsbn = {}", isbn);
        return reviewRepository.findAllByBookIsbn(isbn)
                .stream()
                .map(review -> ReviewDtoResponse.builder()
                        .userName(review.getUser().getFirstName() + " " + review.getUser().getLastName())
                        .rating(review.getRating())
                        .text(review.getText())
                        .build())
                .toList();
    }

    public void saveReview(User user, String isbn, ReviewDtoRequest dto) {
        log.info("in saveReview(): user = {}, bookIsbn = {}", user, isbn);
        Book book = bookService.findBookByIsbn(isbn);

        reviewRepository.save(Review.builder()
                .rating(dto.getRating())
                .text(dto.getText())
                .book(book)
                .user(user)
                .build());
    }
}
