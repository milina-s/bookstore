package com.example.bookstore.mappers;

import com.example.bookstore.dto.publisher.PublisherDto;
import com.example.bookstore.model.Publisher;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class PublisherDtoMapper extends AbstractConverter<Publisher, PublisherDto> {
    @Override
    protected PublisherDto convert(Publisher publisher) {
        return PublisherDto.builder()
                .id(publisher.getId())
                .nameUa(publisher.getNameUa())
                .nameEn(publisher.getNameEn())
                .aboutUa(publisher.getAboutUa())
                .aboutEn(publisher.getAboutEn())
                .website(publisher.getWebsite())
                .build();
    }
}
