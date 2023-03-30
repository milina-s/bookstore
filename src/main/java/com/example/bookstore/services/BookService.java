package com.example.bookstore.services;

import com.example.bookstore.constants.ErrorMessage;
import com.example.bookstore.dto.book.BookDto;
import com.example.bookstore.dto.book.BookDtoRequest;
import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.book.BookCover;
import com.example.bookstore.model.book.BookLanguage;
import com.example.bookstore.model.book.BookType;
import com.example.bookstore.repositories.BookRepository;
import com.example.bookstore.specifications.BookSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.data.jpa.domain.Specification.where;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;
    private final PublisherService publisherService;
    private final SeriesService seriesService;

    public void save(BookDtoRequest addBookDto) {
        log.info("Save book with isbn: {}", addBookDto.getIsbn());

        if (bookRepository.findByIsbn(addBookDto.getIsbn()).isPresent())
            throw new IllegalArgumentException(ErrorMessage.BOOK_ALREADY_EXISTS_BY_THIS_ISBN + addBookDto.getIsbn());
        if (bookRepository.existsByImage(addBookDto.getImage()))
            log.warn("Book with this image already exists: {}", addBookDto.getImage());

        bookRepository.save(setBook(modelMapper.map(addBookDto, Book.class)));
    }

    public void update(BookDtoRequest addBookDto) {
        log.info("Update book with isbn: {}", addBookDto.getIsbn());

        if (bookRepository.existsByIsbn(addBookDto.getIsbn()))
            throw new NoSuchElementException(ErrorMessage.BOOK_NOT_FOUND_BY_ISBN + addBookDto.getIsbn());

        bookRepository.save(setBook(modelMapper.map(addBookDto, Book.class)));
    }

    public Book findBookByIsbn(String isbn) {
        log.info("Find book by isbn: {}", isbn);

        return bookRepository.findByIsbn(isbn).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.BOOK_NOT_FOUND_BY_ISBN + isbn)
        );
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

    public BookDto findBookDtoByIsbn(String isbn) {
        log.info("Find book dto by isbn: {}", isbn);

        return modelMapper.map(findBookByIsbn(isbn), BookDto.class);
    }

    public void deleteById(String isbn) {
        log.info("Delete book by isbn: {}", isbn);

        if (!bookRepository.existsByIsbn(isbn))
            throw new NoSuchElementException(ErrorMessage.BOOK_NOT_FOUND_BY_ISBN + isbn);
        bookRepository.delete(findBookByIsbn(isbn));
    }

    public List<BookDto> findBookDtoByOriginalTitle(String originalTitle) {
        log.info("Find book by original title: {}", originalTitle);

        return bookRepository.findByTitleOriginalContaining(originalTitle).stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .toList();
    }

    public List<BookDto> filterBooks(String searchWord, List<Long> authorIds, List<Long> categoryIds, List<Long> seriesIds, List<Long> publisherIds, List<BookLanguage> languages, List<BookCover> covers, List<BookType> types, Double minPrice, Double maxPrice, Boolean inStock, Pageable pageable) {
        log.info("Filter books by \nsearchWord: {}, \nauthorIds: {}, \ncategoryIds: {}, \nseriesIds: {}, \npublisherIds: {}, \nlanguages: {}, \ncovers: {}, \ntypes: {}, \nminPrice: {}, \nmaxPrice: {}, \ninStock: {}, \npageable: {}",
                searchWord, authorIds, categoryIds, seriesIds, publisherIds, languages, covers, types, minPrice, maxPrice, inStock, pageable);

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
                .map(book -> modelMapper.map(book, BookDto.class))
                .toList();
    }
}
