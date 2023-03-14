package com.example.bookstore.services;

import com.example.bookstore.constants.ErrorMessage;
import com.example.bookstore.constants.LogMessage;
import com.example.bookstore.dto.AuthorDto;
import com.example.bookstore.model.Author;
import com.example.bookstore.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public Author findAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.AUTHOR_NOT_FOUND_BY_ID + id));
    }

    public void save(AuthorDto author) {
        log.info(LogMessage.IN_SAVE, Author.class);

        if(authorRepository.findByNameEn(author.getNameEn()).isPresent())
            log.warn(LogMessage.AUTHOR_NAME_ALREADY_EXISTS, author.getNameEn());

        Author authorEntity = modelMapper.map(author, Author.class);
        authorRepository.save(authorEntity);
    }

    public AuthorDto findAuthorDtoById(Long id) {
        log.info(LogMessage.IN_FIND_BY_ID, Author.class, id);

        return modelMapper.map(findAuthorById(id), AuthorDto.class);
    }

    public void deleteById(Long id) {
        log.info(LogMessage.IN_DELETE_BY_ID, Author.class, id);

        if (!findAuthorById(id).getBooks().isEmpty())
            throw new IllegalArgumentException(ErrorMessage.NOT_SAVE_DELETION);

        authorRepository.delete(findAuthorById(id));
    }

    public void update(AuthorDto authorDto) {
        log.info(LogMessage.IN_UPDATE_BY_ID, Author.class, authorDto.getId());

        if (!authorRepository.existsById(authorDto.getId()))
            throw new NoSuchElementException(ErrorMessage.AUTHOR_NOT_FOUND_BY_ID + authorDto.getId());

        Author author = modelMapper.map(authorDto, Author.class);

        authorRepository.save(author);
    }

    public List<AuthorDto> findAllAuthorDto() {
        log.info(LogMessage.IN_FIND_ALL, Author.class);

        return modelMapper.map(authorRepository.findAll(), new TypeToken<List<AuthorDto>>() {
        }.getType());
    }
}
