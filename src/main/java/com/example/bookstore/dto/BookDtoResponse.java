package com.example.bookstore.dto;

import com.example.bookstore.model.book.BookCover;
import com.example.bookstore.model.book.BookLanguage;
import com.example.bookstore.model.book.BookType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class BookDtoResponse {

    private String isbn;

    private String title;

    private String titleOriginal;

    private String description;

    private String image;

    private Double price;

    private Double priceWithDiscount;

    private Integer year;

    private Integer pages;

    private String format;

    private Integer quantity;

    private Integer popularity;

    private String translator;

    private Double rating;

    private BookType type;

    private BookLanguage language;

    private BookCover cover;

    private AuthorDto author;

    private CategoryDtoRequest category;

    private PublisherDto publisher;

    private SeriesDto series;
}
