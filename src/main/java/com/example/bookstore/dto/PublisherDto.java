package com.example.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PublisherDto implements Serializable {

    private Long id;

    private String nameUa;

    private String nameEn;

    private String aboutUa;

    private String aboutEn;

    private String website;

}
