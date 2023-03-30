package com.example.bookstore.services;

import com.example.bookstore.dto.review.ReviewDto;
import com.example.bookstore.dto.review.ReviewDtoRequest;
import com.example.bookstore.model.Review;
import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.user.User;
import com.example.bookstore.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookService bookService;

    public List<ReviewDto> findAllReviewsByBook(String isbn) {
        log.info("Find all reviews for book with isbn: {}", isbn);

        return reviewRepository.findAllByBookIsbn(isbn)
                .stream()
                .map(review -> ReviewDto.builder()
                        .userName(review.getUser().getFirstname() + " " + review.getUser().getLastname())
                        .rating(review.getRating())
                        .text(review.getText())
                        .build())
                .toList();
    }

    public void saveReview(User user, ReviewDtoRequest dto) {
        log.info("Save review for book with isbn: {}", dto.getBookIsbn());

        Book book = bookService.findBookByIsbn(dto.getBookIsbn());

        reviewRepository.save(Review.builder()
                .rating(dto.getRating())
                .text(dto.getText())
                .book(book)
                .user(user)
                .build());
    }
}
