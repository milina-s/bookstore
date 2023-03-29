package com.example.bookstore.services;

import com.example.bookstore.constants.ErrorMessage;
import com.example.bookstore.constants.LogMessage;
import com.example.bookstore.dto.CategoryDtoRequest;
import com.example.bookstore.dto.CategoryDtoResponse;
import com.example.bookstore.model.Category;
import com.example.bookstore.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Slf4j
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;


    public void save(CategoryDtoRequest categoryDtoRequest) {
        log.info(LogMessage.IN_SAVE, CategoryDtoRequest.class);

        if (categoryRepository.findByNameEn(categoryDtoRequest.getNameEn()).isPresent())
            throw new IllegalArgumentException (ErrorMessage.CATEGORY_ALREADY_EXISTS_BY_THIS_NAME);

        Category category = mapper.map(categoryDtoRequest, Category.class);

        if (categoryDtoRequest.getParentCategoryId() != null)
            category.setParentCategory(findCategoryById(categoryDtoRequest.getParentCategoryId()));

        categoryRepository.save(category);

    }

    public Category findCategoryById(Long id) {
        log.info(LogMessage.IN_FIND_BY_ID, Category.class, id);

        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.CATEGORY_NOT_FOUND_BY_ID + id));
    }

    public List<CategoryDtoResponse> findAllCategoryDto() {
        log.info(LogMessage.IN_FIND_ALL, CategoryDtoRequest.class);

        return mapper.map(categoryRepository.findAllByParentCategoryIsNull(), new TypeToken<List<CategoryDtoResponse>>() {
        }.getType());
    }

    public Category findCategoryByNameEn(String nameEn) {
        log.info(LogMessage.IN_FIND_BY_NAME_EN, Category.class, nameEn);

        return categoryRepository.findByNameEn(nameEn)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.CATEGORY_NOT_FOUND_BY_NAME + nameEn));

    }

    public void deleteById(Long id) {
        log.info(LogMessage.IN_DELETE_BY_ID, Category.class, id);

        Category category = findCategoryById(id);
        if (!category.getChildCategories().isEmpty() || !category.getBooks().isEmpty())
            throw new NoSuchElementException(ErrorMessage.NOT_SAVE_DELETION);

        categoryRepository.deleteById(id);
    }

    public void update(CategoryDtoRequest categoryDtoRequest) {
        log.info(LogMessage.IN_UPDATE_BY_ID, Category.class, categoryDtoRequest.getId());

        Category category = findCategoryById(categoryDtoRequest.getId());
        if (categoryDtoRequest.getParentCategoryId() != null)
            category.setParentCategory(findCategoryById(categoryDtoRequest.getParentCategoryId()));

        if (categoryRepository.findByNameEn(categoryDtoRequest.getNameEn()).isPresent())
            throw new IllegalArgumentException (ErrorMessage.CATEGORY_ALREADY_EXISTS_BY_THIS_NAME);

        category.setNameEn(categoryDtoRequest.getNameEn());
        category.setNameUa(categoryDtoRequest.getNameUa());

        categoryRepository.save(category);
    }
}
