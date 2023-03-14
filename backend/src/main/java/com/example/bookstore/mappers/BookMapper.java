package com.example.bookstore.mappers;

import com.example.bookstore.dto.BookDtoRequest;
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
    protected Book convert(BookDtoRequest bookDtoRequest) {
        return Book.builder()
                .isbn(bookDtoRequest.getIsbn())
                .title(bookDtoRequest.getTitle())
                .titleOriginal(bookDtoRequest.getTitleOriginal())
                .description(bookDtoRequest.getDescription())
                .image(bookDtoRequest.getImage())
                .price(bookDtoRequest.getPrice())
                .priceWithDiscount(bookDtoRequest.getPriceWithDiscount())
                .year(bookDtoRequest.getYear())
                .pages(bookDtoRequest.getPages())
                .format(bookDtoRequest.getFormat())
                .quantity(bookDtoRequest.getQuantity())
                .popularity(bookDtoRequest.getPopularity())
                .translator(bookDtoRequest.getTranslator())
                .rating(bookDtoRequest.getRating())
                .type(bookDtoRequest.getType())
                .language(bookDtoRequest.getLanguage())
                .cover(bookDtoRequest.getCover())
                .author(bookDtoRequest.getAuthorId() != null ? Author.builder()
                        .id(bookDtoRequest.getAuthorId())
                        .build() : null)
                .category(bookDtoRequest.getCategoryId() != null ? Category.builder()
                        .id(bookDtoRequest.getCategoryId())
                        .build() : null)
                .publisher(bookDtoRequest.getPublisherId() != null ? Publisher.builder()
                        .id(bookDtoRequest.getPublisherId())
                        .build() : null)
                .series(bookDtoRequest.getSeriesId() != null ? Series.builder()
                        .id(bookDtoRequest.getSeriesId())
                        .build() : null)
                .build();
    }

}
