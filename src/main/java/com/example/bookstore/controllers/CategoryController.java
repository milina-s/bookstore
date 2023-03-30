package com.example.bookstore.controllers;


import com.example.bookstore.dto.category.CategoryDtoFull;
import com.example.bookstore.dto.category.CategoryDtoRequest;
import com.example.bookstore.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/v1/bookstore/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/save")
    @Operation(
            description = "Save a new category",
            responses = {@ApiResponse(responseCode = "200", description = "Category saved successfully")}
    )
    public void saveCategory(@Valid @RequestBody CategoryDtoRequest dto) {
        categoryService.save(dto);
    }

    @PutMapping("/update")
    @Operation(
            description = "Update category",
            responses = {@ApiResponse(responseCode = "200", description = "Category updated successfully")}
    )
    public void updateCategory(@Valid @RequestBody CategoryDtoRequest dto) {
        categoryService.update(dto);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            description = "Delete category by id",
            responses = {@ApiResponse(responseCode = "200", description = "Category deleted successfully")}
    )
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @GetMapping("/get_all")
    @Operation(
            description = "Get list of all categories",
            responses = {@ApiResponse(responseCode = "200", description = "Categories retrieved successfully")}
    )
    public List<CategoryDtoFull> getAllCategories() {
        return categoryService.findAllCategoryDto();
    }
}
