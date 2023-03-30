package com.example.bookstore.mappers;

import com.example.bookstore.dto.category.CategoryDtoFull;
import com.example.bookstore.model.Category;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoResponseMapper extends AbstractConverter<Category, CategoryDtoFull> {
    @Override
    protected CategoryDtoFull convert(Category category) {
        return CategoryDtoFull.builder()
                .id(category.getId())
                .nameEn(category.getNameEn())
                .nameUa(category.getNameUa())
                .childCategories(category.getChildCategories().stream().map(this::convert).toList())
                .build();
    }
}
