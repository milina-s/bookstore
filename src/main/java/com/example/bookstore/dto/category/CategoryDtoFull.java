package com.example.bookstore.dto.category;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonPropertyOrder({"id", "nameUa", "nameEn", "childCategories"})
public class CategoryDtoFull implements Serializable {

    private Long id;

    private String nameEn;

    private String nameUa;

    private List<CategoryDtoFull> childCategories;
}

