package com.example.bookstore.services;

import com.example.bookstore.constants.ErrorMessage;
import com.example.bookstore.dto.series.SeriesDto;
import com.example.bookstore.model.Series;
import com.example.bookstore.repositories.SeriesRepository;
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
public class SeriesService {

    private final SeriesRepository seriesRepository;
    private final PublisherService publisherService;
    private final ModelMapper modelMapper;

    public Series findSeriesById(Long id) {
        log.info("Find series by id: {}", id);

        return seriesRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.SERIES_NOT_FOUND_BY_ID + id));
    }

    public void save(SeriesDto seriesDto) {
        log.info("Save series with name: {}", seriesDto.getName());

        Series series = modelMapper.map(seriesDto, Series.class);
        series.setPublisher(publisherService.findPublisherById(seriesDto.getPublisherId()));

        seriesRepository.save(series);
    }

    public void update(SeriesDto seriesDto) {
        log.info("Update series with id: {}", seriesDto.getId());

        Series series = findSeriesById(seriesDto.getId());
        series.setName(seriesDto.getName());
        series.setPublisher(publisherService.findPublisherById(seriesDto.getPublisherId()));

        seriesRepository.save(series);
    }

    public void deleteById(Long id) {
        log.info("Delete series by id: {}", id);

        if (!findSeriesById(id).getBooks().isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_SAVE_DELETION);
        }

        seriesRepository.deleteById(id);
    }

    public List<SeriesDto> findSeriesByPublisher(Long publisherId) {
        log.info("Find series by publisher id: {}", publisherId);

        return modelMapper.map(seriesRepository.findAllByPublisherId(publisherId), new TypeToken<List<SeriesDto>>() {
        }.getType());
    }

    public List<SeriesDto> findAllSeriesDto() {
        log.info("Find all series");

        return modelMapper.map(seriesRepository.findAll(), new TypeToken<List<SeriesDto>>() {
        }.getType());
    }
}
