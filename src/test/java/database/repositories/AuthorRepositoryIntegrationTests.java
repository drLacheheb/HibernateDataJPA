package database.repositories;

import database.TestDataUtil;
import database.domain.Author;
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
public class AuthorRepositoryIntegrationTests {
    private final AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createAuthorA();
        underTest.save(author);
        Optional<Author> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled(){
        Author author_a = TestDataUtil.createAuthorA();
        underTest.save(author_a);
        Author author_b = TestDataUtil.createAuthorB();
        underTest.save(author_b);
        Author author_c = TestDataUtil.createAuthorC();
        underTest.save(author_c);
        Iterable<Author> result = underTest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(author_a, author_b, author_c);

    }

    @Test
    public void testThatAuthorCanBeUpdatedAndRecalled(){
        Author author_a = TestDataUtil.createAuthorA();
        underTest.save(author_a);
        author_a.setName("Updated");
        underTest.save(author_a);
        Optional<Author> result = underTest.findById(author_a.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author_a);
    }

    @Test
    public void testThatAuthorCanBeDeletedAndRecalled(){
        Author author_a = TestDataUtil.createAuthorA();
        underTest.save(author_a);
        underTest.deleteById(author_a.getId());
        Optional<Author> result = underTest.findById(author_a.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan(){
        Author author_a = TestDataUtil.createAuthorA();
        underTest.save(author_a);
        Author author_b = TestDataUtil.createAuthorB();
        underTest.save(author_b);
        Author author_c = TestDataUtil.createAuthorC();
        underTest.save(author_c);
        Iterable<Author> result = underTest.ageLessThan(40);
        assertThat(result).containsExactly(author_b, author_c);
    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan(){
        Author author_a = TestDataUtil.createAuthorA();
        underTest.save(author_a);
        Author author_b = TestDataUtil.createAuthorB();
        underTest.save(author_b);
        Author author_c = TestDataUtil.createAuthorC();
        underTest.save(author_c);
        Iterable<Author> result = underTest.ageGreaterThan(40);
        assertThat(result).containsExactly(author_a);
    }
}
