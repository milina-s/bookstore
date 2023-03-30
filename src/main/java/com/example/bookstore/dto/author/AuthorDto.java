package com.example.bookstore.dto.author;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @Pattern(regexp = "^[a-zA-Z0-9 ]{3,100}$", message = "Wrong name for author. Name must contain only letters, numbers and spaces and be 3-100 characters long")
    @NotBlank
    private String nameUa;

    @Pattern(regexp = "^[a-zA-Z0-9 ]{3,100}$", message = "Wrong name for author. Name must contain only letters, numbers and spaces and be 3-100 characters long")
    private String nameEn;

    @Pattern(regexp = "^[a-zA-Z0-9 ]{3,100}$", message = "Wrong name for author. Name must contain only letters, numbers and spaces and be 3-100 characters long")
    private String fullNameOriginal;

    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "Wrong date format. Date must be in format yyyy-MM-dd")
    private LocalDate birthDate;

    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "Wrong date format. Date must be in format yyyy-MM-dd")
    private LocalDate deathDate;

    private String aboutUa;

    private String aboutEn;

    private String image;
}
