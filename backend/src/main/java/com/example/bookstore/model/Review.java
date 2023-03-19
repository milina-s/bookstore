package com.example.bookstore.model;

import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_isbn", referencedColumnName = "isbn")
    private Book book;

}
