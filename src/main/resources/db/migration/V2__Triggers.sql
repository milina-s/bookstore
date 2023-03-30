-- function to update the rating of a book

CREATE OR REPLACE FUNCTION update_book_rating() RETURNS TRIGGER AS
$$
DECLARE
    sum_rating DECIMAL;
    review_count INTEGER;
    book_rating DECIMAL(2,1);
BEGIN
    SELECT COUNT(*), COALESCE(SUM(rating), 0)
    INTO review_count, sum_rating
    FROM reviews
    WHERE book_isbn = NEW.book_isbn;

    IF (review_count > 0) THEN
        book_rating := round ((sum_rating / review_count), 1);
    END IF;

    update books set rating = book_rating where isbn = NEW.book_isbn;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- trigger to call the previous function after a review is added

CREATE TRIGGER reviews_after_insert
    AFTER INSERT
    ON reviews
    FOR EACH ROW
EXECUTE FUNCTION update_book_rating();

-- function to update the popularity of a book

CREATE OR REPLACE FUNCTION update_book_popularity() RETURNS TRIGGER AS
$$
DECLARE
    purchases INTEGER;
BEGIN
    SELECT COALESCE(SUM(quantity), 0)
    INTO purchases
    FROM order_books
    WHERE book_isbn = NEW.book_isbn;

    update books set popularity = purchases where isbn = NEW.book_isbn;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- trigger to call the previous function after a book is purchased

CREATE TRIGGER reviews_after_insert
    AFTER INSERT
    ON order_books
    FOR EACH ROW
EXECUTE FUNCTION update_book_popularity();