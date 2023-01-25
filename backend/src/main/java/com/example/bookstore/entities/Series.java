package com.example.bookstore.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "series")
public class Series {
    @Id
    @SequenceGenerator(
            name = "series_sequence",
            sequenceName = "series_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "series_sequence"
    )
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publishing_house_id", referencedColumnName = "id")
    private PublishingHouse publishingHouse;
}
