package com.example.LinkingPGSQL.dao.impl;

import com.example.LinkingPGSQL.TestDataUtil;
import com.example.LinkingPGSQL.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorDAOImplTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private AuthorDAOImpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql()
    {
        Author author = TestDataUtil.createTestAuthorA();
        underTest.create(author);
        verify(jdbcTemplate).update(
                eq("INSERT INTO authors(id, name, age) VALUES (?, ?, ?)"),
                eq(1L), eq("Abigail Rose"), eq(80)
                );
    }

    @Test
    public void testThatFindOneGeneratesCorrectSql()
    {
        underTest.findOne(1L);
        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDAOImpl.AuthorRowMapper>any(),
                eq(1L)
        );
    }
    @Test
    public void testThatFindManyGeneratesCorrectSql()
    {
        underTest.find();
        verify(jdbcTemplate).query(eq("SELECT id, name, age FROM authors"),
                ArgumentMatchers.<AuthorDAOImpl.AuthorRowMapper>any());
    }
    @Test
    public void testThatUpdateGeneratesCorrectSql()
    {
        Author author = TestDataUtil.createTestAuthorA();
        underTest.update(3L, author);
        verify(jdbcTemplate).update(
                "UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?", 1L, "Abigail Rose", 80, 3L
        );
    }
    @Test
    public void testThatDeleteGeneratesCorrectSql()
    {
        underTest.delete(1L);
        verify(jdbcTemplate).update(
                "DELETE FROM authors WHERE id = ?", 1L
        );
    }
}
