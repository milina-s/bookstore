package com.example.bookstore.services;

import com.example.bookstore.constants.ErrorMessage;
import com.example.bookstore.constants.LogMessage;
import com.example.bookstore.dto.BookDtoRequest;
import com.example.bookstore.dto.BookDtoResponse;
import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.book.BookCover;
import com.example.bookstore.model.book.BookLanguage;
import com.example.bookstore.model.book.BookType;
import com.example.bookstore.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.bookstore.specifications.BookSpecification;


import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.data.jpa.domain.Specification.where;

@RequiredArgsConstructor

@Slf4j
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final ModelMapper mapper;
    private final PublisherService publisherService;
    private final SeriesService seriesService;

    public void save(BookDtoRequest bookDtoRequest) {
        log.info(LogMessage.IN_SAVE, Book.class);

        if (bookRepository.findByIsbn(bookDtoRequest.getIsbn()).isPresent())
            throw new IllegalArgumentException(ErrorMessage.BOOK_ALREADY_EXISTS_BY_THIS_ISBN + bookDtoRequest.getIsbn());
        if (bookRepository.existsByImage(bookDtoRequest.getImage()))
            log.warn(LogMessage.BOOK_IMAGE_ALREADY_EXISTS, bookDtoRequest.getImage());

        bookRepository.save(setBook(mapper.map(bookDtoRequest, Book.class)));
    }

    public void update(BookDtoRequest bookDtoRequest) {
        log.info(LogMessage.IN_UPDATE_BY_ID, Book.class, bookDtoRequest.getIsbn());

        if (bookRepository.existsByIsbn(bookDtoRequest.getIsbn()))
            throw new NoSuchElementException(ErrorMessage.BOOK_NOT_FOUND_BY_ISBN + bookDtoRequest.getIsbn());

        bookRepository.save(setBook(mapper.map(bookDtoRequest, Book.class)));
    }

    public Book findBookByIsbn(String isbn) {
        log.info(LogMessage.IN_FIND_BY_ID, Book.class, isbn);

        return bookRepository.findByIsbn(isbn).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.BOOK_NOT_FOUND_BY_ISBN + isbn)
        );
    }


    public List<BookDtoResponse> findAllBookDto() {
        log.info(LogMessage.IN_FIND_ALL, BookDtoResponse.class);

        return bookRepository.findAll().stream()
                .map(book -> mapper.map(book, BookDtoResponse.class))
                .toList();
    }

    public List<BookDtoResponse> findAllBookResponseDto() {
        log.info(LogMessage.IN_FIND_ALL, BookDtoResponse.class);

        return bookRepository.findAll().stream()
                .map(book -> mapper.map(book, BookDtoResponse.class))
                .toList();
    }

    private Book setBook(Book book) {
        if (book.getAuthor() != null)
            book.setAuthor(authorService.findAuthorById(book.getAuthor().getId()));
        if (book.getCategory() != null)
            book.setCategory(categoryService.findCategoryById(book.getCategory().getId()));
        if (book.getPublisher() != null)
            book.setPublisher(publisherService.findPublisherById(book.getPublisher().getId()));
        if (book.getSeries() != null)
            book.setSeries(seriesService.findSeriesById(book.getSeries().getId()));
        return book;
    }

    public BookDtoResponse findBookDtoByIsbn(String isbn) {
        log.info(LogMessage.IN_FIND_BY_ID, BookDtoResponse.class, isbn);

        return mapper.map(findBookByIsbn(isbn), BookDtoResponse.class);
    }

    public void deleteById(String isbn) {
        log.info(LogMessage.IN_DELETE_BY_ID, Book.class, isbn);

        if (!bookRepository.existsByIsbn(isbn))
            throw new NoSuchElementException(ErrorMessage.BOOK_NOT_FOUND_BY_ISBN + isbn);
        bookRepository.delete(findBookByIsbn(isbn));
    }

    public List<BookDtoResponse> findBookDtoByAuthor(Long authorId) {
        log.info(LogMessage.IN_FIND_BY_AUTHOR, authorId);

        return bookRepository.findAllByAuthorId(authorId).stream()
                .map(book -> mapper.map(book, BookDtoResponse.class))
                .toList();
    }

    public List<BookDtoResponse> findBookDtoByCategory(Long categoryId) {
        log.info(LogMessage.IN_FIND_BY_CATEGORY, categoryId);

        return bookRepository.findAllByCategoryId(categoryId).stream()
                .map(book -> mapper.map(book, BookDtoResponse.class))
                .toList();
    }

    public List<BookDtoResponse> findBookDtoByOriginalTitle(String originalTitle) {
        log.info(LogMessage.IN_FIND_BY_ORIGINAL_TITLE, originalTitle);

        return bookRepository.findByTitleOriginalContaining(originalTitle).stream()
                .map(book -> mapper.map(book, BookDtoResponse.class))
                .toList();
    }

    public List<BookDtoResponse> findBookDtoByTitle(String title) {
        log.info(LogMessage.IN_FIND_BY_TITLE, title);

        return bookRepository.findByTitleContaining(title).stream()
                .map(book -> mapper.map(book, BookDtoResponse.class))
                .toList();
    }


    public List<BookDtoResponse> filterBooks(String searchWord, List<Long> authorIds, List<Long> categoryIds, List<Long> seriesIds, List<Long> publisherIds, List<BookLanguage> languages, List<BookCover> covers, List<BookType> types, Double minPrice, Double maxPrice, Boolean inStock, Pageable pageable) {
        log.info(LogMessage.IN_FILTER_BOOKS);
        return bookRepository.findAll(
                        where(searchWord == null ? null :
                                ((BookSpecification.titleContains(searchWord))
                                        .or(BookSpecification.originalTitleContains(searchWord))
                                        .or(BookSpecification.descriptionContains(searchWord))
                                        .or(BookSpecification.authorNameContains(searchWord))))
                                .and(authorIds == null ? null : BookSpecification.hasAuthors(authorIds))
                                .and(categoryIds == null ? null : BookSpecification.hasCategories(categoryIds))
                                .and(seriesIds == null ? null : BookSpecification.hasSeries(seriesIds))
                                .and(publisherIds == null ? null : BookSpecification.hasPublishers(publisherIds))
                                .and(languages == null ? null : BookSpecification.hasLanguages(languages))
                                .and(covers == null ? null : BookSpecification.hasCovers(covers))
                                .and(types == null ? null : BookSpecification.hasTypes(types))
                                .and(minPrice == null ? null : BookSpecification.hasPriceGreaterThan(minPrice))
                                .and(maxPrice == null ? null : BookSpecification.hasPriceLessThan(maxPrice))
                                .and(inStock ? BookSpecification.hasQuantityGreaterThanZero() : null)
                        , pageable).stream()
                .map(book -> mapper.map(book, BookDtoResponse.class))
                .toList();

    }
}
