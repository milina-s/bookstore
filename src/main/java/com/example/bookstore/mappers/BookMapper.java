package com.example.bookstore.mappers;

import com.example.bookstore.dto.book.BookDtoRequest;
import com.example.bookstore.model.Author;
import com.example.bookstore.model.Category;
import com.example.bookstore.model.Publisher;
import com.example.bookstore.model.Series;
import com.example.bookstore.model.book.Book;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class BookMapper extends AbstractConverter<BookDtoRequest, Book> {
    @Override
    protected Book convert(BookDtoRequest addBookDto) {
        return Book.builder()
                .isbn(addBookDto.getIsbn())
                .title(addBookDto.getTitle())
                .titleOriginal(addBookDto.getTitleOriginal())
                .description(addBookDto.getDescription())
                .image(addBookDto.getImage())
                .price(addBookDto.getPrice())
                .priceWithDiscount(addBookDto.getPriceWithDiscount())
                .year(addBookDto.getYear())
                .pages(addBookDto.getPages())
                .format(addBookDto.getFormat())
                .quantity(addBookDto.getQuantity())
                .popularity(addBookDto.getPopularity())
                .translator(addBookDto.getTranslator())
                .rating(addBookDto.getRating())
                .type(addBookDto.getType())
                .language(addBookDto.getLanguage())
                .cover(addBookDto.getCover())
                .author(addBookDto.getAuthorId() != null ? Author.builder()
                        .id(addBookDto.getAuthorId())
                        .build() : null)
                .category(addBookDto.getCategoryId() != null ? Category.builder()
                        .id(addBookDto.getCategoryId())
                        .build() : null)
                .publisher(addBookDto.getPublisherId() != null ? Publisher.builder()
                        .id(addBookDto.getPublisherId())
                        .build() : null)
                .series(addBookDto.getSeriesId() != null ? Series.builder()
                        .id(addBookDto.getSeriesId())
                        .build() : null)
                .build();
    }
}
