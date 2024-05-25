package com.example.jackson.controller;

import com.example.jackson.domain.Book;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class BookController {
    @GetMapping(path = "/book")
    public Book retrieveBook()
    {
        return Book.builder()
                .isbn("1")
                .title("Sunny Days")
                .author("James King")
                .yearPublished("1992")
                .build();
    }
    @PostMapping(path = "/book")
    public Book createBook(@RequestBody final Book book)
    {
        log.info("Got book:" + book.toString());
        return book;
    }
}
