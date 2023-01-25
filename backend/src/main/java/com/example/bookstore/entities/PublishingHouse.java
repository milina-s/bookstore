package com.example.bookstore.entities;

import jakarta.persistence.*;
import lombok.*;

import java.text.DateFormat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "publishing_house")
public class PublishingHouse {
    @Id
    @SequenceGenerator(
            name = "publishing_house_sequence",
            sequenceName = "publishing_house_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "publishing_house_sequence"
    )
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "about", columnDefinition = "TEXT")
    private String about;
}
