package com.example.bookstore.dto;

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

public class AuthorDto implements Serializable {

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
