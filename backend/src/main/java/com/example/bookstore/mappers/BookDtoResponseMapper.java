package com.example.bookstore.mappers;

import com.example.bookstore.dto.BookDtoResponse;
import com.example.bookstore.model.book.Book;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor

@Component
public class BookDtoResponseMapper extends AbstractConverter<Book, BookDtoResponse> {

    private final AuthorDtoMapper authorDtoMapper;
    private final CategoryDtoMapper categoryDtoMapper;
    private final PublisherDtoMapper publisherDtoMapper;
    private final SeriesDtoMapper tagDtoMapper;

    @Override
    protected BookDtoResponse convert(Book book) {
        return BookDtoResponse.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .titleOriginal(book.getTitleOriginal())
                .description(book.getDescription())
                .image(book.getImage())
                .price(book.getPrice())
                .priceWithDiscount(book.getPriceWithDiscount())
                .year(book.getYear())
                .pages(book.getPages())
                .format(book.getFormat())
                .quantity(book.getQuantity())
                .popularity(book.getPopularity())
                .translator(book.getTranslator())
                .rating(book.getRating())
                .type(book.getType())
                .language(book.getLanguage())
                .cover(book.getCover())
                .author(book.getAuthor() != null ? authorDtoMapper.convert(book.getAuthor()) : null)
                .category(book.getCategory() != null ? categoryDtoMapper.convert(book.getCategory()) : null)
                .publisher(book.getPublisher() != null ? publisherDtoMapper.convert(book.getPublisher()) : null)
                .series(book.getSeries() != null ? tagDtoMapper.convert(book.getSeries()) : null)
                .build();
    }
}
