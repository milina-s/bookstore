package com.example.bookstore.model;

import com.example.bookstore.model.book.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(generator = "sequence-authors-id")
    @GenericGenerator(
            name = "sequence-authors-id",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "authors_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            })
    @Column(name = "id")
    private Long id;

    @Column(name = "name_ua")
    private String nameUa;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "full_name_original")
    private String fullNameOriginal;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "death_date")
    private LocalDate deathDate;

    @Column(name = "about_ua")
    private String aboutUa;

    @Column(name = "about_en")
    private String aboutEn;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

}
