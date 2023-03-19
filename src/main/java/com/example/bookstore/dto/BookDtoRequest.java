package com.example.bookstore.dto;

import com.example.bookstore.model.book.BookCover;
import com.example.bookstore.model.book.BookLanguage;
import com.example.bookstore.model.book.BookType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor

@Data
public class BookDtoRequest implements Serializable {

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

    private Long authorId;

    private Long categoryId;

    private Long publisherId;

    private Long seriesId;
}
