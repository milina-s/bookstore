package com.example.bookstore.model.favorite;

import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "favorites")
public class Favorite {

    @EmbeddedId
    private FavoriteKey id = new FavoriteKey();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("bookIsbn")
    @JoinColumn(name = "book_isbn")
    private Book book;

}
