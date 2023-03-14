package com.example.bookstore.controllers;

import com.example.bookstore.dto.AuthorDto;
import com.example.bookstore.services.AuthorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Save author")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Author successfully saved")
    })
    @PostMapping("/save")
    public void saveBook(@Valid @RequestBody AuthorDto author) {
        authorService.save(author);
    }

    @ApiOperation(value = "Update author")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Author successfully updated")
    })
    @PutMapping("/update")
    public void updateAuthor(@Valid @RequestBody AuthorDto authorDto) {
        authorService.update(authorDto);
    }

    @ApiOperation(value = "Delete author by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Author successfully deleted")
    })
    @DeleteMapping("/delete/{id}")
    public void deleteAuthorById(@PathVariable Long id) {
        authorService.deleteById(id);
    }

    @ApiOperation(value = "Get a list of all authors")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Authors successfully found")
    })
    @GetMapping("/get_all")
    public List<AuthorDto> getAllAuthors() {
        return authorService.findAllAuthorDto();
    }

    @ApiOperation(value = "Get author by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Author successfully found")
    })
    @GetMapping("/get/{id}")
    public AuthorDto getAuthorById(@PathVariable Long id) {
        return authorService.findAuthorDtoById(id);
    }

}
