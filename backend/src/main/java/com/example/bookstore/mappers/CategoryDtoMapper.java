package com.example.bookstore.mappers;

import com.example.bookstore.dto.CategoryDto;
import com.example.bookstore.model.Category;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoMapper extends AbstractConverter<Category, CategoryDto> {
    @Override
    protected CategoryDto convert(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .nameEn(category.getNameEn())
                .nameUa(category.getNameUa())
                .parentCategoryId(category.getParentCategory() != null ? category.getParentCategory().getId() : null)
                .build();
    }

}
