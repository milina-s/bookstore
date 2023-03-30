package com.example.bookstore.dto.category;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonPropertyOrder({"nameEn", "nameUa", "parentCategoryId"})
public class CategoryDtoShort implements Serializable {

    private Long id;

    private String nameEn;

    private String nameUa;

    private CategoryDtoShort parentCategory;
}