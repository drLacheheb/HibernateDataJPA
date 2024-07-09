package database.repositories;

import database.TestDataUtil;
import database.domain.Author;
import database.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTests {
    private final BookRepository underTest;
    @Autowired
    public BookRepositoryIntegrationTests(BookRepository underTest) {
        this.underTest = underTest;
    }
    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createAuthorA();
        Book book = TestDataUtil.createBookB(author);
        underTest.save(book);
        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }
    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createAuthorA();
        Book book_A = TestDataUtil.createBookA(author);
        underTest.save(book_A);
        Book book_B = TestDataUtil.createBookB(author);
        underTest.save(book_B);
        Book book_C = TestDataUtil.createBookC(author);
        underTest.save(book_C);
        Iterable<Book> result = underTest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(book_A, book_B, book_C);
    }
    @Test
    public void testThatBookCanBeUpdatedAndRecalled() {
        Author author = TestDataUtil.createAuthorA();
        Book book = TestDataUtil.createBookA(author);
        underTest.save(book);
        book.setTitle("Updated");
        underTest.save(book);
        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);

    }
}
