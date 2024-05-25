//package com.example.LinkingPGSQL.repositories;
//
//import com.example.LinkingPGSQL.TestDataUtil;
//import com.example.LinkingPGSQL.domain.entities.AuthorEntity;
//import com.example.LinkingPGSQL.domain.entities.BookEntity;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class BookRepositoryIntegrationTest {
//    private BookRepository underTest;
//    @Autowired
//    public BookRepositoryIntegrationTest(BookRepository underTest)
//    {
//        this.underTest = underTest;
//    }
//    @Test
//    public void testThatBookCanBeCreatedAndRecalled()
//    {
//        AuthorEntity author = TestDataUtil.createTestAuthorA();
//        BookEntity book = TestDataUtil.createTestBookA(author);
//        underTest.save(book);
//        Optional<BookEntity> result = underTest.findById(book.getIsbn());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(book);
//    }
//    @Test
//    public void testThatMultipleBookCanBeCreatedAndRecalled()
//    {
//        AuthorEntity author = TestDataUtil.createTestAuthorA();
//        BookEntity bookA = TestDataUtil.createTestBookA(author);
//        underTest.save(bookA);
//        BookEntity bookB = TestDataUtil.createTestBookB(author);
//        underTest.save(bookB);
//        BookEntity bookC = TestDataUtil.createTestBookC(author);
//        underTest.save(bookC);
//        Iterable<BookEntity> result = underTest.findAll();
//        assertThat(result).hasSize(3).containsExactly(bookA, bookB, bookC);
//    }
//    @Test
//    public void testThatBookCanBeUpdated()
//    {
//        AuthorEntity author = TestDataUtil.createTestAuthorA();
//        BookEntity book = TestDataUtil.createTestBookA(author);
//        underTest.save(book);
//        book.setTitle("Allah u Akbar");
//        underTest.save(book);
//        Optional<BookEntity> result = underTest.findById(book.getIsbn());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(book);
//    }
//    @Test
//    public void testThatBookCanBeDeleted()
//    {
//        AuthorEntity author = TestDataUtil.createTestAuthorA();
//        BookEntity book = TestDataUtil.createTestBookA(author);
//        underTest.save(book);
//        underTest.deleteById(book.getIsbn());
//        Optional<BookEntity> result = underTest.findById(book.getIsbn());
//        assertThat(result).isEmpty();
//    }
//}
