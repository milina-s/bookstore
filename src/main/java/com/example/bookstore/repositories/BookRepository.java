package com.example.bookstore.repositories;

import com.example.bookstore.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    List<Book> findByTitleOriginalContaining(String originalTitle);

    List<Book> findByTitleContaining(String title);

    Optional<Book> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

    boolean existsByImage(String image);

    List<Book> findAllByAuthorId(Long authorId);

    List<Book> findAllByCategoryId(Long categoryId);
}
