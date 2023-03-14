package com.example.bookstore.services;

import com.example.bookstore.constants.ErrorMessage;
import com.example.bookstore.constants.LogMessage;
import com.example.bookstore.dto.CategoryDto;
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


    public void save(CategoryDto categoryDto) {
        log.info(LogMessage.IN_SAVE, CategoryDto.class);

        if (categoryRepository.findByNameEn(categoryDto.getNameEn()).isPresent())
            throw new IllegalArgumentException (ErrorMessage.CATEGORY_ALREADY_EXISTS_BY_THIS_NAME);

        Category category = mapper.map(categoryDto, Category.class);

        if (categoryDto.getParentCategoryId() != null)
            category.setParentCategory(findCategoryById(categoryDto.getParentCategoryId()));

        categoryRepository.save(category);

    }

    private CategoryDto findCategoryDtoById(Long id) {
        log.info(LogMessage.IN_FIND_BY_ID, CategoryDto.class, id);

        return mapper.map(categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.CATEGORY_NOT_FOUND_BY_ID + id)), CategoryDto.class);
    }

    public Category findCategoryById(Long id) {
        log.info(LogMessage.IN_FIND_BY_ID, Category.class, id);

        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.CATEGORY_NOT_FOUND_BY_ID + id));
    }

    public List<CategoryDto> findAllCategoryDto() {
        log.info(LogMessage.IN_FIND_ALL, CategoryDto.class);

        return mapper.map(categoryRepository.findAll(), new TypeToken<List<CategoryDto>>() {
        }.getType());
    }

    public List<Category> findAllCategory() {
        log.info(LogMessage.IN_FIND_ALL, Category.class);

        return categoryRepository.findAll();
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

    public void update(CategoryDto categoryDto) {
        log.info(LogMessage.IN_UPDATE_BY_ID, Category.class, categoryDto.getId());

        Category category = findCategoryById(categoryDto.getId());
        if (categoryDto.getParentCategoryId() != null)
            category.setParentCategory(findCategoryById(categoryDto.getParentCategoryId()));

        if (categoryRepository.findByNameEn(categoryDto.getNameEn()).isPresent())
            throw new IllegalArgumentException (ErrorMessage.CATEGORY_ALREADY_EXISTS_BY_THIS_NAME);

        category.setNameEn(categoryDto.getNameEn());
        category.setNameUa(categoryDto.getNameUa());

        categoryRepository.save(category);
    }
}
