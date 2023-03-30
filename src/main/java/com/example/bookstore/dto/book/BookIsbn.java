package com.example.bookstore.dto.book;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookIsbn implements Serializable {

    @Pattern(regexp = "^[0-9]{10}([0-9]{3})?$", message = "Wrong ISBN. ISBN must contain only numbers and be 10 or 13 characters long")
    private String isbn;
}
