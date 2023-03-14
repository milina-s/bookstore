package com.example.bookstore.model.book;

import com.example.bookstore.constants.ErrorMessage;

public enum BookType {
    PAPER,
    EBOOK;

    public static BookType fromString(String type) {
        if (type != null)
            for (BookType bookType : BookType.values())
                if (type.equalsIgnoreCase(bookType.name()))
                    return bookType;
        throw new IllegalArgumentException(ErrorMessage.BOOK_TYPE_NOT_FOUND);
    }
}
