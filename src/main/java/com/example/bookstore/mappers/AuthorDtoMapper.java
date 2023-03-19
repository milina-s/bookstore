package com.example.bookstore.mappers;

import com.example.bookstore.dto.AuthorDto;
import com.example.bookstore.model.Author;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class AuthorDtoMapper extends AbstractConverter<Author, AuthorDto> {

    @Override
    protected AuthorDto convert(Author author) {
        return AuthorDto.builder()
                .id(author.getId())
                .nameUa(author.getNameUa())
                .nameEn(author.getNameEn())
                .fullNameOriginal(author.getFullNameOriginal())
                .aboutUa(author.getAboutUa())
                .aboutEn(author.getAboutEn())
                .image(author.getImage())
                .birthDate(author.getBirthDate())
                .deathDate(author.getDeathDate())
                .build();
    }

}
