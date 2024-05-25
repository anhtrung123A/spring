package com.example.LinkingPGSQL.controllers;

import com.example.LinkingPGSQL.domain.dto.BookDto;
import com.example.LinkingPGSQL.domain.entities.BookEntity;
import com.example.LinkingPGSQL.mappers.Mapper;
import com.example.LinkingPGSQL.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookController {
    private Mapper<BookEntity, BookDto> bookMapper;
    private BookService bookService;

    public BookController(Mapper<BookEntity, BookDto> bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }
    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> createUpdateBook(@PathVariable("isbn") String isbn,
                                        @RequestBody BookDto bookDto){
        Optional<BookEntity> bookEntity = bookService.findOne(isbn);
        if(bookEntity.isEmpty())
        {
            BookEntity bookEntity1 = bookMapper.mapFrom(bookDto);
            BookEntity savedBookEntity = bookService.save(isbn, bookEntity1);
            BookDto savedBookDto = bookMapper.mapTo(savedBookEntity);
            return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);
        }
        else
        {
            bookDto.setIsbn(isbn);
            BookEntity bookEntity1 = bookMapper.mapFrom(bookDto);
            BookEntity bookEntity2 = bookService.save(bookDto.getIsbn() ,bookEntity1);
            return new ResponseEntity<>(bookMapper.mapTo(bookEntity2), HttpStatus.OK);
        }
    }
    @GetMapping(path = "/books")
    public List<BookDto> listBooks()
    {
        List<BookEntity> bookEntityList = bookService.findAll();
        return bookEntityList.stream().map(bookMapper::mapTo).collect(Collectors.toList());
    }
    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> findOneBook(@PathVariable("isbn") String isbn)
    {
        Optional<BookEntity> bookEntity = bookService.findOne(isbn);
        return bookEntity.map(book ->{
            BookDto bookDto = bookMapper.mapTo(book);
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PatchMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> partialUpdateBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto)
    {
        Optional<BookEntity> bookEntity = bookService.findOne(isbn);
        if(bookEntity.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            BookEntity bookEntity1 = bookMapper.mapFrom(bookDto);
            BookEntity bookEntity2 = bookService.partialUpdate(isbn, bookEntity1);
            bookService.save(isbn, bookEntity2);
            return new ResponseEntity<>(bookMapper.mapTo(bookEntity2), HttpStatus.OK);
        }
    }
    @DeleteMapping(path = "/books/{isbn}")
    public ResponseEntity deleteAuthor(@PathVariable("isbn") String isbn)
    {
        Optional<BookEntity> bookEntity = bookService.findOne(isbn);
        if(bookEntity.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            bookService.delete(isbn);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
