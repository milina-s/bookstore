package com.example.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
// TODO: add @JsonPropertyOrder to all DTOs
@JsonPropertyOrder({"id", "nameUa", "nameEn", "fullNameOriginal", "birthDate", "deathDate", "aboutUa", "aboutEn", "image"})
public class AuthorDto implements Serializable {

    // TODO: add validation to all DTOs
    // TODO: add @JsonProperty to all DTOs
    @JsonProperty("id")
    private Long id;

    private String nameUa;

    private String nameEn;

    private String fullNameOriginal;

    private LocalDate birthDate;

    private LocalDate deathDate;

    private String aboutUa;

    private String aboutEn;

    private String image;
}
