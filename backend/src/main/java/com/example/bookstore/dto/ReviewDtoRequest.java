package com.example.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewDtoRequest implements Serializable {

    private Integer rating;

    private String text;

    private String bookIsbn;

}
