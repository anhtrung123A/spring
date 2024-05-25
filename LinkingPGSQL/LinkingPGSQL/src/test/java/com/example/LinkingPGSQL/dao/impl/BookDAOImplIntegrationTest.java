package com.example.LinkingPGSQL.dao.impl;

import com.example.LinkingPGSQL.TestDataUtil;
import com.example.LinkingPGSQL.dao.AuthorDAO;
import com.example.LinkingPGSQL.domain.Author;
import com.example.LinkingPGSQL.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDAOImplIntegrationTest {
    private BookDAOImpl underTest;
    private AuthorDAOImpl authorDAO;
    @Autowired
    public BookDAOImplIntegrationTest(BookDAOImpl underTest, AuthorDAOImpl authorDAO)
    {
        this.underTest = underTest;
        this.authorDAO = authorDAO;
    }
    @Test
    public void testThatBookCanBeCreatedAndRecalled()
    {
        Author author = TestDataUtil.createTestAuthorA();
        Book book = TestDataUtil.createTestBookA();
        authorDAO.create(author);
        underTest.create(book);
        Optional<Book> result = underTest.findOne(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }
    @Test
    public void testThatMultipleBookCanBeCreatedAndRecalled()
    {
        Author author = TestDataUtil.createTestAuthorA();
        authorDAO.create(author);
        Book bookA = TestDataUtil.createTestBookA();
        underTest.create(bookA);
        Book bookB = TestDataUtil.createTestBookB();
        underTest.create(bookB);
        Book bookC = TestDataUtil.createTestBookC();
        underTest.create(bookC);
        List<Book> result = underTest.find();
        assertThat(result).hasSize(3).containsExactly(bookA, bookB, bookC);
    }
    @Test
    public void testThatBookCanBeUpdated()
    {
        Book book = TestDataUtil.createTestBookA();
        Author author = TestDataUtil.createTestAuthorA();
        authorDAO.create(author);
        underTest.create(book);
        book.setTitle("Alahu Akba");
        underTest.update(book.getIsbn(), book);
        Optional<Book> result = underTest.findOne(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }
    @Test
    public void testThatBookCanBeDeleted()
    {
        Author author = TestDataUtil.createTestAuthorA();
        Book book = TestDataUtil.createTestBookA();
        authorDAO.create(author);
        underTest.create(book);
        underTest.delete(book.getIsbn());
        Optional<Book> result = underTest.findOne(book.getIsbn());
        assertThat(result).isEmpty();
    }
}
