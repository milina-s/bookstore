package com.example.bookstore.dto.book;

import com.example.bookstore.model.book.BookCover;
import com.example.bookstore.model.book.BookLanguage;
import com.example.bookstore.model.book.BookType;
import jakarta.validation.constraints.*;
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

    @Pattern(regexp = "^[0-9]{10}([0-9]{3})?$", message = "Wrong ISBN. ISBN must contain only numbers and be 10 or 13 characters long")
    private String isbn;

    @NotBlank(message = "Title must not be empty")
    private String title;

    private String titleOriginal;

    private String description;

    private String image;

    @NotNull(message = "Price must not be empty")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double price;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price with discount must be greater than 0")
    private Double priceWithDiscount;

    @Pattern(regexp = "^[0-9]{1,4}$", message = "Wrong year. Year must contain only numbers and be 1-4 characters long")
    private Integer year;

    @Min(value = 1, message = "Pages must be greater than 0")
    private Integer pages;

    private String format;

    @Min(value = 0, message = "Quantity can't be less than 0")
    private Integer quantity;

    @Max(value = 10, message = "Popularity must be less than 10")
    @Min(value = 0, message = "Popularity must be greater than 0")
    private Integer popularity;

    private String translator;

    @DecimalMax(value = "5.0", message = "Rating must be less than 5")
    @DecimalMin(value = "0.0", message = "Rating must be greater than 0")
    private Double rating;

    private BookType type;

    private BookLanguage language;

    private BookCover cover;

    private Long authorId;

    private Long categoryId;

    private Long publisherId;

    private Long seriesId;
}
