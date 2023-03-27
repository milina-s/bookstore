package com.example.bookstore.controllers;

import com.example.bookstore.dto.AuthorDto;
import com.example.bookstore.services.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(value = "/api/v1/bookstore/authors")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("/save")
    @Operation(
            description = "Save a new author",
            responses = {@ApiResponse(responseCode = "200", description = "Author saved successfully")}
    )
    public void saveBook(@Valid @RequestBody AuthorDto author) {
        authorService.save(author);
    }

    @PutMapping("/update")
    @Operation(
            description = "Update author",
            responses = {@ApiResponse(responseCode = "200", description = "Author updated successfully")}
    )
    public void updateAuthor(@Valid @RequestBody AuthorDto authorDto) {
        authorService.update(authorDto);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            description = "Delete author by id",
            responses = {@ApiResponse(responseCode = "200", description = "Author deleted successfully")}
    )
    public void deleteAuthorById(@PathVariable Long id) {
        authorService.deleteById(id);
    }

    @GetMapping("/get_all")
    @Operation(
            description = "Get list of all authors",
            responses = {@ApiResponse(responseCode = "200", description = "Authors retrieved successfully")}
    )
    public List<AuthorDto> getAllAuthors() {
        return authorService.findAllAuthorDto();
    }

    @GetMapping("/get/{id}")
    @Operation(
            description = "Get author by id",
            responses = {@ApiResponse(responseCode = "200", description = "Author retrieved successfully")}
    )
    public AuthorDto getAuthorById(@PathVariable Long id) {
        return authorService.findAuthorDtoById(id);
    }

}
