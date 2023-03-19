package com.example.bookstore.specifications;

import com.example.bookstore.model.Author;
import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.book.BookCover;
import com.example.bookstore.model.book.BookLanguage;
import com.example.bookstore.model.book.BookType;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class BookSpecification {

    public static Specification<Book> titleContains(String title) {
        return (root, query, builder) -> builder.like(root.get("title"), "%" + title + "%");
    }

    public static Specification<Book> hasAuthors(List<Long> authorIds) {
        return (root, query, builder) -> root.join("author").get("id").in(authorIds);
    }

    public static Specification<Book> hasCategories(List<Long> categoryIds) {
        return (root, query, builder) -> root.join("category").get("id").in(categoryIds);
    }

    public static Specification<Book> hasSeries(List<Long> seriesIds) {
        return (root, query, builder) -> root.join("series").get("id").in(seriesIds);
    }

    public static Specification<Book> hasPublishers(List<Long> publisherIds) {
        return (root, query, builder) -> root.join("publisher").get("id").in(publisherIds);
    }

    public static Specification<Book> hasLanguages(List<BookLanguage> languages) {
        return (root, query, builder) -> root.get("language").in(languages);
    }

    public static Specification<Book> hasCovers(List<BookCover> covers) {
        return (root, query, builder) -> root.get("cover").in(covers);
    }

    public static Specification<Book> hasTypes(List<BookType> types) {
        return (root, query, builder) -> root.get("type").in(types);
    }

    public static Specification<Book> hasPriceLessThan(Double maxPrice) {
        return (root, query, builder) -> builder.lessThan(root.get("price"), maxPrice);
    }

    public static Specification<Book> hasPriceGreaterThan(Double minPrice) {
        return (root, query, builder) -> builder.greaterThan(root.get("price"), minPrice);
    }

    public static Specification<Book> hasQuantityGreaterThanZero() {
        return (root, query, builder) -> builder.greaterThan(root.get("quantity"), 0);
    }

    public static Specification<Book> originalTitleContains(String originalTitle) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("titleOriginal")), "%" + originalTitle.toLowerCase() + "%");
    }


    public static Specification<Book> descriptionContains(String description) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("description")), "%" + description + "%");
    }


    public static Specification<Book> authorNameContains(String authorName) {
        return (root, query, builder) -> {
            Join<Book, Author> authorJoin = root.join("author", JoinType.LEFT);
            Predicate predicate = builder.like(builder.lower(authorJoin.get("nameUa")), "%" + authorName.toLowerCase() + "%");
            predicate = builder.or(predicate, builder.like(builder.lower(authorJoin.get("nameEn")), "%" + authorName.toLowerCase() + "%"));
            predicate = builder.or(predicate, builder.like(builder.lower(authorJoin.get("fullNameOriginal")), "%" + authorName.toLowerCase() + "%"));
            return predicate;
        };
    }
}
