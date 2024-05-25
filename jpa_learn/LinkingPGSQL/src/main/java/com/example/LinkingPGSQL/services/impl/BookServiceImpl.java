package com.example.LinkingPGSQL.services.impl;

import com.example.LinkingPGSQL.domain.entities.BookEntity;
import com.example.LinkingPGSQL.repositories.BookRepository;
import com.example.LinkingPGSQL.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity save(String isbn, BookEntity book) {
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }

    @Override
    public List<BookEntity> findAll() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<BookEntity> findOne(String isbn) {
        return bookRepository.findById(isbn);
    }

    @Override
    public BookEntity partialUpdate(String isbn, BookEntity bookEntity) {
        bookEntity.setIsbn(isbn);
        return bookRepository.findById(isbn).map(existedBook ->{
            Optional.ofNullable(bookEntity.getTitle()).ifPresent(existedBook::setTitle);
            Optional.ofNullable(bookEntity.getAuthor()).ifPresent(existedBook::setAuthor);
            return existedBook;
        }).orElseThrow(()-> new RuntimeException("Book does not exist"));
    }

    @Override
    public void delete(String isbn) {
        bookRepository.deleteById(isbn);
    }
}
