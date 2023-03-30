package com.example.bookstore.controllers;

import com.example.bookstore.dto.book.BookDto;
import com.example.bookstore.dto.book.BookDtoRequest;
import com.example.bookstore.model.book.BookCover;
import com.example.bookstore.model.book.BookLanguage;
import com.example.bookstore.model.book.BookType;
import com.example.bookstore.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/bookstore/books")
public class BookController {

    private final BookService bookService;

    @PostMapping("/save")
    @Operation(
            description = "Save book",
            responses = {@ApiResponse(responseCode = "200", description = "Book saved successfully")}
    )
    public void saveBook(@Valid @RequestBody BookDtoRequest book) {
        bookService.save(book);
    }

    @PutMapping("/update")
    @Operation(
            description = "Update book",
            responses = {@ApiResponse(responseCode = "200", description = "Book updated successfully")}
    )
    public void updateBook(@Valid @RequestBody BookDtoRequest book) {
        bookService.update(book);
    }

    @DeleteMapping("/delete/{isbn}")
    @Operation(
            description = "Delete book by isbn",
            responses = {@ApiResponse(responseCode = "200", description = "Book deleted successfully")}
    )
    public void deleteBook(@PathVariable String isbn) {
        bookService.deleteById(isbn);
    }

    @GetMapping("/get_by_isbn")
    @Operation(
            description = "Get book by isbn",
            responses = {@ApiResponse(responseCode = "200", description = "Book retrieved successfully")}
    )
    public BookDto getBookByIsbn(@RequestParam String isbn) {
        return bookService.findBookDtoByIsbn(isbn);
    }

    @GetMapping("/get_by_original_title")
    @Operation(
            description = "Get list of books by original title",
            responses = {@ApiResponse(responseCode = "200", description = "List of books retrieved")}
    )
    public List<BookDto> getBooksByOriginalTitle(@RequestParam String originalTitle) {
        return bookService.findBookDtoByOriginalTitle(originalTitle);
    }

    @GetMapping("/filter")
    @Operation(
            description = "Filter books by search word, authors, categories, series, publishers, languages, covers, types, price, in stock, sort",
            responses = {@ApiResponse(responseCode = "200", description = "List of books retrieved successfully")}
    )
    public ResponseEntity<List<BookDto>> filterBooks(
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
        List<BookDto> books = bookService.filterBooks(searchWord, authorIds, categoryIds, seriesIds, publisherIds, languages, covers, types, minPrice, maxPrice, inStock, pageable);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(books.size()));
        return ResponseEntity.ok().headers(headers).body(books);
    }
}
