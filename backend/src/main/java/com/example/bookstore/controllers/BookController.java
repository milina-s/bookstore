package com.example.bookstore.controllers;

import com.example.bookstore.dto.BookDtoRequest;
import com.example.bookstore.dto.BookDtoResponse;
import com.example.bookstore.model.book.BookCover;
import com.example.bookstore.model.book.BookLanguage;
import com.example.bookstore.model.book.BookType;
import com.example.bookstore.services.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/bookstore/books")
public class BookController {

    private final BookService bookService;

    @ApiOperation(value = "Save book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book successfully saved")
    })
    @PostMapping("/save")
    public void saveBook(@Valid @RequestBody BookDtoRequest book) {
        bookService.save(book);
    }

    @ApiOperation(value = "Update book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book successfully updated")
    })
    @PutMapping("/update")
    public void updateBook(@Valid @RequestBody BookDtoRequest book) {
        bookService.update(book);
    }

    @ApiOperation(value = "Delete book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book successfully deleted")
    })
    @DeleteMapping("/delete/{isbn}")
    public void deleteBook(@PathVariable String isbn) {
        bookService.deleteById(isbn);
    }

    @ApiOperation(value = "Get a book by isbn")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book successfully retrieved")
    })
    @GetMapping("/get_by_isbn")
    public BookDtoResponse getBookByIsbn(@RequestParam String isbn) {
        return bookService.findBookDtoByIsbn(isbn);
    }


    @ApiOperation(value = "Get a list of books by original title")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Books successfully retrieved")
    })
    @GetMapping("/get_by_original_title")
    public List<BookDtoResponse> getBooksByOriginalTitle(@RequestParam String originalTitle) {
        return bookService.findBookDtoByOriginalTitle(originalTitle);
    }

    @ApiOperation(value = "Get a list of books by title")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Books successfully retrieved")
    })
    @GetMapping("/get_by_title")
    public List<BookDtoResponse> getBooksByTitle(@RequestParam String title) {
        return bookService.findBookDtoByTitle(title);
    }

    @ApiOperation(value = "Get a filtered list of books")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Books successfully retrieved")
    })
    @GetMapping("/filter")
    public ResponseEntity<List<BookDtoResponse>> filterBooks(
            // filter by search word
            @RequestParam(name = "search", required = false) String searchWord,
            // filters by id
            @RequestParam(name = "authors", required = false) List<Long> authorIds,
            @RequestParam(name = "categories", required = false) List<Long> categoryIds,
            @RequestParam(name = "series", required = false) List<Long> seriesIds,
            @RequestParam(name = "publishers", required = false) List<Long> publisherIds,
            // filters by enum
            @RequestParam(name = "languages", required = false) List<BookLanguage> languages,
            @RequestParam(name = "covers", required = false) List<BookCover> covers,
            @RequestParam(name = "types", required = false) List<BookType> types,
            // price
            @RequestParam(name = "minPrice", required = false) Double minPrice,
            @RequestParam(name = "maxPrice", required = false) Double maxPrice,
            // in stock
            @RequestParam(name = "inStock", defaultValue = "false") Boolean inStock,
            // sort
            @RequestParam(name = "sortby", defaultValue = "popularity") String sortBy,
            @RequestParam(name = "sortdir", defaultValue = "desc") String sortDirection,
            // pagination
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
        List<BookDtoResponse> books = bookService.filterBooks(searchWord, authorIds, categoryIds, seriesIds, publisherIds, languages, covers, types, minPrice, maxPrice, inStock, pageable);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(books.size()));
        return ResponseEntity.ok().headers(headers).body(books);
    }


}
