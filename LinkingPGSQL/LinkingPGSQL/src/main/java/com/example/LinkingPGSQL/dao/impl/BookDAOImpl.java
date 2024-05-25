package com.example.LinkingPGSQL.dao.impl;

import com.example.LinkingPGSQL.dao.BookDAO;
import com.example.LinkingPGSQL.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Component

public class BookDAOImpl implements BookDAO {
    private final JdbcTemplate jdbcTemplate;
    public BookDAOImpl(final  JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO books(isbn, title, author_id) VALUES(?, ?, ?)", book.getIsbn(), book.getTitle(), book.getAuthorID());
    }

    @Override
    public Optional<Book> findOne(String isbn) {
        List<Book> books = jdbcTemplate.query(
                "SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1", new BookRowMapper(), isbn
        );
        return books.stream().findFirst();
    }
    public static class BookRowMapper implements RowMapper<Book>
    {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder()
                    .isbn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .authorID(rs.getLong("author_id"))
                    .build();
        }
    }

    @Override
    public void update(String id, Book book) {
        jdbcTemplate.update("UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?" , book.getIsbn(),
                book.getTitle(), book.getAuthorID(), id);
    }

    @Override
    public List<Book> find() {
        return jdbcTemplate.query("SELECT isbn, title, author_id FROM books",
                new BookRowMapper());
    }

    @Override
    public void delete(String isbn) {
        jdbcTemplate.update("DELETE FROM books WHERE isbn = ?", isbn);
    }
}
