package com.example.bookstore.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewDto implements Serializable {

    private Integer rating;

    private String text;

    private String userName;
}
