package com.example.bookstore.services;

import com.example.bookstore.constants.ErrorMessage;
import com.example.bookstore.constants.LogMessage;
import com.example.bookstore.dto.SeriesDto;
import com.example.bookstore.model.Series;
import com.example.bookstore.repositories.SeriesRepository;
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
public class SeriesService {

    private final SeriesRepository seriesRepository;
    private final PublisherService publisherService;
    private final ModelMapper modelMapper;

    public Series findSeriesById(Long id) {
        log.info(LogMessage.IN_FIND_BY_ID, Series.class ,id);

        return seriesRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.SERIES_NOT_FOUND_BY_ID + id));
    }

    public SeriesDto findSeriesDtoById(Long id) {
        log.info(LogMessage.IN_FIND_BY_ID, SeriesDto.class, id);

        return modelMapper.map(findSeriesById(id), SeriesDto.class);
    }

    public void save(SeriesDto seriesDto) {
        log.info(LogMessage.IN_SAVE, Series.class);

        Series series = modelMapper.map(seriesDto, Series.class);
        series.setPublisher(publisherService.findPublisherById(seriesDto.getPublisherId()));

        seriesRepository.save(series);
    }

    public void update(SeriesDto seriesDto) {
        log.info(LogMessage.IN_UPDATE_BY_ID, Series.class, seriesDto.getId());

        Series series = findSeriesById(seriesDto.getId());
        series.setName(seriesDto.getName());
        series.setPublisher(publisherService.findPublisherById(seriesDto.getPublisherId()));

        seriesRepository.save(series);
    }

    public void deleteById(Long id) {
        log.info(LogMessage.IN_DELETE_BY_ID, Series.class, id);

        if (!findSeriesById(id).getBooks().isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_SAVE_DELETION);
        }

        seriesRepository.deleteById(id);
    }

    public List<SeriesDto> findSeriesByPublisher(Long publisherId) {
        log.info(LogMessage.IN_FIND_BY_PUBLISHER, publisherId);

        return modelMapper.map(seriesRepository.findAllByPublisherId(publisherId), new TypeToken<List<SeriesDto>>() {
        }.getType());
    }

    public List<SeriesDto> findAllSeriesDto() {
        log.info(LogMessage.IN_FIND_ALL, SeriesDto.class);

        return modelMapper.map(seriesRepository.findAll(), new TypeToken<List<SeriesDto>>() {
        }.getType());
    }
}
