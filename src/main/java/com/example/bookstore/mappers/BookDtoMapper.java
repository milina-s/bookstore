package com.example.bookstore.mappers;

import com.example.bookstore.dto.book.BookDto;
import com.example.bookstore.model.book.Book;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor

@Component
public class BookDtoMapper extends AbstractConverter<Book, BookDto> {

    private final AuthorDtoMapper authorDtoMapper;
    private final CategoryDtoRequestMapper categoryDtoRequestMapper;
    private final PublisherDtoMapper publisherDtoMapper;
    private final SeriesDtoMapper tagDtoMapper;

    @Override
    protected BookDto convert(Book book) {
        return BookDto.builder()
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
                .category(book.getCategory() != null ? categoryDtoRequestMapper.convert(book.getCategory()) : null)
                .publisher(book.getPublisher() != null ? publisherDtoMapper.convert(book.getPublisher()) : null)
                .series(book.getSeries() != null ? tagDtoMapper.convert(book.getSeries()) : null)
                .build();
    }
}
