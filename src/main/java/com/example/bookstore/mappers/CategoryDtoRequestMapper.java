package com.example.bookstore.mappers;

import com.example.bookstore.dto.category.CategoryDtoShort;
import com.example.bookstore.model.Category;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoRequestMapper extends AbstractConverter<Category, CategoryDtoShort> {
    @Override
    protected CategoryDtoShort convert(Category category) {
        return CategoryDtoShort.builder()
                .id(category.getId())
                .nameEn(category.getNameEn())
                .nameUa(category.getNameUa())
                .parentCategory(category.getParentCategory() == null ? null : convert(category.getParentCategory()))
                .build();
    }
}
