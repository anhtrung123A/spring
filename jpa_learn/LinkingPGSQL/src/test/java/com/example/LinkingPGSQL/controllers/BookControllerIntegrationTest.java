package com.example.LinkingPGSQL.controllers;

import com.example.LinkingPGSQL.TestDataUtil;
import com.example.LinkingPGSQL.domain.dto.BookDto;
import com.example.LinkingPGSQL.domain.entities.AuthorEntity;
import com.example.LinkingPGSQL.domain.entities.BookEntity;
import com.example.LinkingPGSQL.services.BookService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class BookControllerIntegrationTest {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final BookService bookService;
    @Autowired

    public BookControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper, BookService bookService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.bookService = bookService;
    }
    @Test
    public void testThatCreateBookSuccessfullyReturnsHttp201Created() throws Exception
    {
        BookDto bookDto = BookDto.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .author(null)
                .build();
        String bookJson = objectMapper.writeValueAsString(bookDto);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/"+ bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }
    @Test
    public void testThatCreateBookSuccessfullyReturnsSavedBook() throws Exception
    {
        BookDto bookDto = BookDto.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .author(null)
                .build();
        String bookJson = objectMapper.writeValueAsString(bookDto);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/"+bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value("978-1-2345-6789-0")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("The Shadow in the Attic")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.author").isEmpty()
        );
    }
    @Test
    public void testThatListBooksReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testThatListBooksReturnsListBooks() throws Exception {
        BookEntity bookEntity = BookEntity.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .author(null)
                .build();
        bookService.save(bookEntity.getIsbn() ,bookEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].isbn").value("978-1-2345-6789-0")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].title").value("The Shadow in the Attic")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].author").isEmpty()
        );
    }
    @Test
    public void testThatFindOneBookReturnsHttpStatus200() throws Exception {
        BookEntity bookEntity = BookEntity.builder()
                .isbn("12321412")
                .title("dag")
                .author(null)
                .build();
        bookService.save(bookEntity.getIsbn(), bookEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/"+bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testThatFindOneBookReturnsABook() throws Exception {
        BookEntity bookEntity = BookEntity.builder()
                .isbn("12321412")
                .title("dag")
                .author(null)
                .build();
        bookService.save(bookEntity.getIsbn(), bookEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/"+bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(bookEntity.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(bookEntity.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").isEmpty());
    }
    @Test
    public void testThatFindOneBookReturnsHttpStatus404IfBookNotExist() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/books/1")
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isNotFound());

    }
    @Test
    public void testThatFullUpdateBookReturnsHttpStatus200() throws Exception {
        BookEntity bookEntity = BookEntity.builder()
                .isbn("12321412")
                .title("dag")
                .author(null)
                .build();
        bookService.save(bookEntity.getIsbn() ,bookEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/"+bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Big cat\", \"author\":null}")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testThatFullUpdateBookReturnsHttpStatus201() throws Exception {
        BookEntity bookEntity = BookEntity.builder()
                .isbn("12321412")
                .title("dag")
                .author(null)
                .build();
        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/"+bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Big cat\", \"author\":null}")
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @Test
    public void testThatFullUpdateBookReturnsUpdatedBook() throws Exception {
        BookEntity bookEntity = BookEntity.builder()
                .isbn("12321412")
                .title("dag")
                .author(null)
                .build();
        bookService.save(bookEntity.getIsbn() ,bookEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/"+bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Big cat\", \"author\":null}")
        ).andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Big cat"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").isEmpty());
    }
    @Test
    public void testThatPartialUpdateBookReturnsHttpStatus200() throws Exception {
        BookEntity bookEntity = BookEntity.builder()
                .isbn("12321412")
                .title("dag")
                .author(null)
                .build();
        bookService.save(bookEntity.getIsbn(), bookEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/books/"+bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Snow days\"}")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testThatPartialUpdateBookReturnsHttpStatus404() throws Exception {
        BookEntity bookEntity = BookEntity.builder()
                .isbn("12321412")
                .title("dag")
                .author(null)
                .build();
        //bookService.save(bookEntity.getIsbn(), bookEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/books/"+bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Snow days\"}")
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    public void testThatPartialUpdateBookReturnsUpdatedBook() throws Exception {
        BookEntity bookEntity = BookEntity.builder()
                .isbn("12321412")
                .title("dag")
                .author(null)
                .build();
        bookService.save(bookEntity.getIsbn(), bookEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/books/"+bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Snow days\"}")
        ).andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Snow days"));
    }
    @Test
    public void testThatDeleteBookReturnsHttpStatus204() throws Exception {
        BookEntity bookEntity = BookEntity.builder()
                .isbn("12321412")
                .title("dag")
                .author(null)
                .build();
        bookService.save(bookEntity.getIsbn(), bookEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/books/"+bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
    @Test
    public void testThatDeleteBookReturnsHttpStatus404() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    public void testThatDeleteBookReturnsDeletedBook() throws Exception {
        BookEntity bookEntity = BookEntity.builder()
                .isbn("12321412")
                .title("dag")
                .author(null)
                .build();
        bookService.save(bookEntity.getIsbn(), bookEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/books/"+bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/"+bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
