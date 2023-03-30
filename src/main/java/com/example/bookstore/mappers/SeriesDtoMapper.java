package com.example.bookstore.mappers;

import com.example.bookstore.dto.series.SeriesDto;
import com.example.bookstore.model.Series;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class SeriesDtoMapper extends AbstractConverter<Series, SeriesDto> {
    @Override
    protected SeriesDto convert(Series series) {
        return SeriesDto.builder()
                .id(series.getId())
                .name(series.getName())
                .publisherId(series.getPublisher().getId())
                .build();
    }
}
