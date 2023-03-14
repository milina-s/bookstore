package com.example.bookstore.services;

import com.example.bookstore.constants.ErrorMessage;
import com.example.bookstore.constants.LogMessage;
import com.example.bookstore.dto.PublisherDto;
import com.example.bookstore.model.Publisher;
import com.example.bookstore.repositories.PublisherRepository;
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
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final ModelMapper mapper;

    public Publisher findPublisherById(Long id) {
        log.info(LogMessage.IN_FIND_BY_ID, Publisher.class, id);

        return publisherRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.PUBLISHER_NOT_FOUND_BY_ID + id));
    }

    public List<PublisherDto> findAllPublisherDto() {
        log.info(LogMessage.IN_FIND_ALL, PublisherDto.class);

        return mapper.map(publisherRepository.findAll(), new TypeToken<List<PublisherDto>>() {
        }.getType());
    }

    public PublisherDto findPublisherDtoById(Long id) {
        log.info(LogMessage.IN_FIND_BY_ID, PublisherDto.class, id);

        return mapper.map(findPublisherById(id), PublisherDto.class);
    }

    public void save(PublisherDto publisherDto) {
        log.info(LogMessage.IN_SAVE, Publisher.class);

        publisherRepository.save(mapper.map(publisherDto, Publisher.class));
    }

    public void deleteById(Long id) {
        log.info(LogMessage.IN_DELETE_BY_ID, Publisher.class, id);

        if (!findPublisherById(id).getBooks().isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_SAVE_DELETION);
        }

        publisherRepository.deleteById(id);
    }

    public void update(PublisherDto publisherDto) {
        log.info(LogMessage.IN_UPDATE_BY_ID, Publisher.class, publisherDto.getId());

        if (publisherRepository.findById(publisherDto.getId()).isEmpty())
            throw new NoSuchElementException(ErrorMessage.PUBLISHER_NOT_FOUND_BY_ID + publisherDto.getId());

        publisherRepository.save(mapper.map(publisherDto, Publisher.class));
    }
}
