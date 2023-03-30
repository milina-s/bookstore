package com.example.bookstore.services;

import com.example.bookstore.constants.ErrorMessage;
import com.example.bookstore.dto.category.CategoryDtoFull;
import com.example.bookstore.dto.category.CategoryDtoRequest;
import com.example.bookstore.model.Category;
import com.example.bookstore.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public void save(CategoryDtoRequest categoryDtoRequest) {
        log.info("Save category with name: {}", categoryDtoRequest.getNameEn());

        if (categoryRepository.findByNameEn(categoryDtoRequest.getNameEn()).isPresent())
            throw new IllegalArgumentException(ErrorMessage.CATEGORY_ALREADY_EXISTS_BY_THIS_NAME);

        Category category = modelMapper.map(categoryDtoRequest, Category.class);

        if (categoryDtoRequest.getParentId() != null)
            category.setParentCategory(findCategoryById(categoryDtoRequest.getParentId()));

        categoryRepository.save(category);

    }

    public Category findCategoryById(Long id) {
        log.info("Find category by id: {}", id);

        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.CATEGORY_NOT_FOUND_BY_ID + id));
    }

    public List<CategoryDtoFull> findAllCategoryDto() {
        log.info("Find all categories");

        return modelMapper.map(categoryRepository.findAllByParentCategoryIsNull(), new TypeToken<List<CategoryDtoFull>>() {
        }.getType());
    }

    public void deleteById(Long id) {
        log.info("Delete category by id: {}", id);

        Category category = findCategoryById(id);
        if (!category.getChildCategories().isEmpty() || !category.getBooks().isEmpty())
            throw new NoSuchElementException(ErrorMessage.NOT_SAVE_DELETION);

        categoryRepository.deleteById(id);
    }

    public void update(CategoryDtoRequest categoryDtoRequest) {
        log.info("Update category with id: {}", categoryDtoRequest.getId());

        Category category = findCategoryById(categoryDtoRequest.getId());
        if (categoryDtoRequest.getParentId() != null)
            category.setParentCategory(findCategoryById(categoryDtoRequest.getParentId()));

        if (categoryRepository.findByNameEn(categoryDtoRequest.getNameEn()).isPresent())
            throw new IllegalArgumentException(ErrorMessage.CATEGORY_ALREADY_EXISTS_BY_THIS_NAME);

        category.setNameEn(categoryDtoRequest.getNameEn());
        category.setNameUa(categoryDtoRequest.getNameUa());

        categoryRepository.save(category);
    }
}
