package com.example.bookstore.mappers;

import com.example.bookstore.dto.CategoryDtoRequest;
import com.example.bookstore.model.Category;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoRequestMapper extends AbstractConverter<Category, CategoryDtoRequest> {
    @Override
    protected CategoryDtoRequest convert(Category category) {
        return CategoryDtoRequest.builder()
                .id(category.getId())
                .nameEn(category.getNameEn())
                .nameUa(category.getNameUa())
//                .parentCategoryId(category.getParentCategory() != null ? category.getParentCategory().getId() : null)
                .build();
    }

}
