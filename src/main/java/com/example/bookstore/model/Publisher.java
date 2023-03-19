package com.example.bookstore.model;

import com.example.bookstore.model.book.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "publishers")
public class Publisher {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name_ua")
    private String nameUa;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "about_ua", columnDefinition = "TEXT")
    private String aboutUa;

    @Column(name = "about_en", columnDefinition = "TEXT")
    private String aboutEn;

    @Column(name = "website")
    private String website;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books;

    @OneToMany(mappedBy = "publisher")
    private List<Series> series;

}
