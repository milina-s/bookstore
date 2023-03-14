package com.example.bookstore.model.book;

import com.example.bookstore.constants.ErrorMessage;

public enum BookLanguage {
    UKRAINIAN,
    ENGLISH,
    GERMAN,
    FRENCH;

    public static BookLanguage fromString(String language) {
        if (language != null)
            for (BookLanguage bookLanguage : BookLanguage.values())
                if (language.equalsIgnoreCase(bookLanguage.name()))
                    return bookLanguage;
        throw new IllegalArgumentException(ErrorMessage.BOOK_LANGUAGE_NOT_FOUND);
    }
}
