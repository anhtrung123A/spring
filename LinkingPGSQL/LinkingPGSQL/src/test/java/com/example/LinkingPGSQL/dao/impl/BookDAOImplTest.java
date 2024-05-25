package com.example.LinkingPGSQL.dao.impl;

import com.example.LinkingPGSQL.TestDataUtil;
import com.example.LinkingPGSQL.domain.Author;
import com.example.LinkingPGSQL.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDAOImplTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private BookDAOImpl underTest;
    @Test
    public void testThatCreateBookGeneratesCorrectSql()
    {
        Book book = TestDataUtil.createTestBookA();
        underTest.create(book);
        verify(jdbcTemplate).update(eq("INSERT INTO books(isbn, title, author_id) VALUES(?, ?, ?)"),
                eq("978-1-2345-6789-0"),
                eq("The Shaddow in the Attic"),
                eq(1L));
    }

    @Test
    public void testThatFindOneGeneratesCorrectSql()
    {
        underTest.findOne("978-1-2345-6789-0");
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDAOImpl.BookRowMapper>any(),
                eq("978-1-2345-6789-0")
        );
    }
    @Test
    public void testThatUpdateGeneratesCorrectSql()
    {
        Book book = TestDataUtil.createTestBookA();
        Author author = TestDataUtil.createTestAuthorA();
        underTest.update(book.getIsbn(), book);
        verify(jdbcTemplate).update("UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?"
        , book.getIsbn(), book.getTitle(), book.getAuthorID(), book.getIsbn());
    }
    @Test
    public void testThatDeleteGeneratesCorrectSql()
    {
        underTest.delete("978-1-2345-6789-0");
        verify(jdbcTemplate).update("DELETE FROM books WHERE isbn = ?", "978-1-2345-6789-0");
    }
}
