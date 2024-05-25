package com.example.LinkingPGSQL.dao;

import com.example.LinkingPGSQL.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDAO {
    void create(Book book);

    Optional<Book> findOne(String isbn);

    List<Book> find();

    void update(String id, Book book);

    void delete(String isbn);
}
