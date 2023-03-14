package com.example.bookstore.controllers;


import com.example.bookstore.dto.CategoryDto;
import com.example.bookstore.services.CategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/bookstore/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @ApiOperation(value = "Save category")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category successfully saved")
    })
    @PostMapping("/save")
    public void saveCategory(@Valid @RequestBody CategoryDto dto) {
        categoryService.save(dto);
    }

    @ApiOperation(value = "Update category")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category successfully updated")
    })
    @PutMapping("/update")
    public void updateCategory(@Valid @RequestBody CategoryDto dto) {
        categoryService.update(dto);
    }

    @ApiOperation(value = "Delete category")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category successfully deleted")
    })
    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
    @ApiOperation(value = "Get a list of available categories")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list")
    })
    @GetMapping("/get_all")
    public List<CategoryDto> getAllCategories() {
        return categoryService.findAllCategoryDto();
    }

}
