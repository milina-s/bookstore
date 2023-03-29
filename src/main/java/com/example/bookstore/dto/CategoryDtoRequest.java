package com.example.bookstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDtoRequest implements Serializable {

    private Long id;

    @Pattern(regexp = "^[a-zA-Z0-9\\s][^<>]*$", message = "Wrong name for category")
    @NotBlank
    private String nameEn;

    private String nameUa;

    private Long parentCategoryId;
}