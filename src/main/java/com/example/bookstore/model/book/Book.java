package com.example.bookstore.model.book;

import com.example.bookstore.model.Author;
import com.example.bookstore.model.Category;
import com.example.bookstore.model.Publisher;
import com.example.bookstore.model.Series;
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
@Table(name = "books")
public class Book {
    @Id
    @Column(name = "isbn")
    private String isbn;

    @Column(name = "title")
    private String title;

    @Column(name = "title_original")
    private String titleOriginal;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private Double price;

    @Column(name = "price_with_discount")
    private Double priceWithDiscount;

    @Column(name = "year")
    private Integer year;

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "format")
    private String format;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "popularity")
    private Integer popularity;

    @Column(name = "translator")
    private String translator;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private BookType type;

    @Column(name = "language")
    @Enumerated(EnumType.STRING)
    private BookLanguage language;

    @Column(name = "cover")
    @Enumerated(EnumType.STRING)
    private BookCover cover;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    private Publisher publisher;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "series_id", referencedColumnName = "id")
    private Series series;

}
