package com.example.bookstore.services;

import com.example.bookstore.constants.ErrorMessage;
import com.example.bookstore.dto.publisher.PublisherDto;
import com.example.bookstore.model.Publisher;
import com.example.bookstore.repositories.PublisherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final ModelMapper modelMapper;

    public Publisher findPublisherById(Long id) {
        log.info("Find publisher by id: {}", id);

        return publisherRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.PUBLISHER_NOT_FOUND_BY_ID + id));
    }

    public List<PublisherDto> findAllPublisherDto() {
        log.info("Find all publishers");

        return modelMapper.map(publisherRepository.findAll(), new TypeToken<List<PublisherDto>>() {
        }.getType());
    }

    public PublisherDto findPublisherDtoById(Long id) {
        log.info("Find publisher dto by id: {}", id);

        return modelMapper.map(findPublisherById(id), PublisherDto.class);
    }

    public void save(PublisherDto publisherDto) {
        log.info("Save publisher with name: {}", publisherDto.getNameEn());

        publisherRepository.save(modelMapper.map(publisherDto, Publisher.class));
    }

    public void deleteById(Long id) {
        log.info("Delete publisher by id: {}", id);

        if (!findPublisherById(id).getBooks().isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_SAVE_DELETION);
        }

        publisherRepository.deleteById(id);
    }

    public void update(PublisherDto publisherDto) {
        log.info("Update publisher with id: {}", publisherDto.getId());

        if (publisherRepository.findById(publisherDto.getId()).isEmpty())
            throw new NoSuchElementException(ErrorMessage.PUBLISHER_NOT_FOUND_BY_ID + publisherDto.getId());

        publisherRepository.save(modelMapper.map(publisherDto, Publisher.class));
    }
}
