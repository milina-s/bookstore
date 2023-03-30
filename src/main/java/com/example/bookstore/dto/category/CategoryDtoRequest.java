package com.example.bookstore.dto.category;

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
    @Pattern(regexp = "^[a-zA-Z0-9 ]{3,50}$", message = "Wrong name for category. Name must contain only letters, numbers and spaces and be 3-50 characters long")
    @NotBlank
    private String nameEn;

    @Pattern(regexp = "^[a-zA-Z0-9 ]{3,50}$", message = "Wrong name for category. Name must contain only letters, numbers and spaces and be 3-50 characters long")
    private String nameUa;
    private Long parentId;
}
