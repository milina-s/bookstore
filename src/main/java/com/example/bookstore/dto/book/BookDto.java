package com.example.bookstore.dto.book;

import com.example.bookstore.dto.author.AuthorDto;
import com.example.bookstore.dto.category.CategoryDtoShort;
import com.example.bookstore.dto.publisher.PublisherDto;
import com.example.bookstore.dto.series.SeriesDto;
import com.example.bookstore.model.book.BookCover;
import com.example.bookstore.model.book.BookLanguage;
import com.example.bookstore.model.book.BookType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class BookDto implements Serializable {

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

    private CategoryDtoShort category;

    private PublisherDto publisher;

    private SeriesDto series;
}
