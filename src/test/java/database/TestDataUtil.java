package database;

import database.domain.Author;
import database.domain.Book;

public class TestDataUtil {
    private TestDataUtil() {

    }

    public static Author createAuthorA() {
        return Author.
                builder()
                .id(1L)
                .Name("bob jones")
                .age(56)
                .build();
    }

    public static Author createAuthorB() {
        return Author.
                builder()
                .id(2L)
                .Name("john doe")
                .age(34)
                .build();
    }

    public static Author createAuthorC() {
        return Author.
                builder()
                .id(3L)
                .Name("jane smith")
                .age(23)
                .build();
    }

    public static Book createBookA(final Author author) {
        return Book.builder()
                .isbn("1")
                .title("The Goat")
                .author(author)
                .build();
    }

    public static Book createBookB(final Author author) {
        return Book.builder()
                .isbn("2")
                .title("Hello World")
                .author(author)
                .build();
    }

    public static Book createBookC(final Author author) {
        return Book.builder()
                .isbn("3")
                .title("Second Fire")
                .author(author)
                .build();
    }
}



