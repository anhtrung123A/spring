package book.api.book_api.controller;

import book.api.book_api.domain.entity.BookEntity;
import book.api.book_api.repository.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    private BookRepository bookRepository;
    BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    @GetMapping(path = "/books")
    public List<BookEntity> sayHello(){
        return bookRepository.findAll();
    }
}
