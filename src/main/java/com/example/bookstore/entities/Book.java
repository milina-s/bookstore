package com.example.bookstore.entities;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "book")
public class Book {
    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "author")
    private String author;

    @Column(name = "price")
    private Double price;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "author_id", referencedColumnName = "id")
//    private Author author;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "category_id", referencedColumnName = "id")
//    private Category category;
//
//    @Column(name = "sale_price")
//    private Double salePrice;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "publishing_house", referencedColumnName = "id")
//    private PublishingHouse publishingHouse;
//
//    @Column(name = "type", nullable = false)
//    @Enumerated(EnumType.STRING)
//    private Type type;
//
//    @Column(name = "language", nullable = false)
//    @Enumerated(EnumType.STRING)
//    private Language language;
//
//    @Column(name = "year")
//    private Integer year;
//
//    @Column(name = "translator")
//    private String translator;
//
//    @Column(name = "cover", nullable = false)
//    @Enumerated(EnumType.STRING)
//    private Cover cover;
//
//    @Column(name = "pages")
//    private Integer pages;
//
//    @Column(name = "isbn")
//    private String isbn;
//
//    @Column(name = "format")
//    private Long format;
//
//    @Column(name = "weight")
//    private Double weight;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "series", referencedColumnName = "id")
//    private Series series;
//
//    @Column(name = "about", columnDefinition = "TEXT")
//    private String about;
}
