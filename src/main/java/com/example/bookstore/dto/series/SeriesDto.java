package com.example.bookstore.dto.series;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class SeriesDto implements Serializable {

    private Long id;

    private String name;

    private Long publisherId;
}
