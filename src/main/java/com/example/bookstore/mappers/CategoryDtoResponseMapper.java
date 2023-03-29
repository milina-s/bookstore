package com.example.bookstore.mappers;

import com.example.bookstore.dto.CategoryDtoResponse;
import com.example.bookstore.model.Category;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoResponseMapper extends AbstractConverter<Category, CategoryDtoResponse> {
    @Override
    protected CategoryDtoResponse convert(Category category) {
        return CategoryDtoResponse.builder()
                .id(category.getId())
                .nameEn(category.getNameEn())
                .nameUa(category.getNameUa())
                .childCategories(category.getChildCategories().stream().map(this::convert).toList())
                .build();
    }

}
