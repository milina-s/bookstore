package com.example.bookstore.dto.review;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonPropertyOrder({"bookIsbn", "rating", "text"})
public class ReviewDtoRequest implements Serializable {

    @Pattern(regexp = "^[1-5]$", message = "Rating must be a number from 1 to 5")
    private Integer rating;

    private String text;

    @Pattern(regexp = "^[0-9]{10}([0-9]{3})?$", message = "Wrong ISBN. ISBN must contain only numbers and be 10 or 13 characters long")
    private String bookIsbn;
}
