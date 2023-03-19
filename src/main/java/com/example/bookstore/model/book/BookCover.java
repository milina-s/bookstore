package com.example.bookstore.model.book;

import com.example.bookstore.constants.ErrorMessage;

public enum BookCover {
    HARD,
    SOFT;

    public static BookCover fromString(String cover) {
        if (cover != null)
            for (BookCover bookCover : BookCover.values())
                if (cover.equalsIgnoreCase(bookCover.name()))
                    return bookCover;
        throw new IllegalArgumentException(ErrorMessage.BOOK_COVER_NOT_FOUND);
    }
}
