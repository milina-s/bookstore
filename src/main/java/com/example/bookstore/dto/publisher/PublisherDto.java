package com.example.bookstore.dto.publisher;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonPropertyOrder({"id", "nameUa", "nameEn", "aboutUa", "aboutEn", "website"})
public class PublisherDto implements Serializable {

    private Long id;

    @Pattern(regexp = "^[a-zA-Z0-9 ]{3,50}$", message = "Wrong name for publisher. Name must contain only letters, numbers and spaces and be 3-50 characters long")
    private String nameUa;

    @Pattern(regexp = "^[a-zA-Z0-9 ]{3,50}$", message = "Wrong name for publisher. Name must contain only letters, numbers and spaces and be 3-50 characters long")
    private String nameEn;

    private String aboutUa;

    private String aboutEn;

    @URL
    private String website;
}
