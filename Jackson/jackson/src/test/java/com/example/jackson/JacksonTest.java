package com.example.jackson;

import com.example.jackson.domain.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Target;

import static org.assertj.core.api.Assertions.assertThat;

public class JacksonTest {
    @Test
    public void testThatObjectMapperCanCreateJsonFromAnObject() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Book book = Book.builder()
                .isbn("1")
                .title("Sunny Days")
                .author("James King")
                .yearPublished("1997")
                .build();
        String result = objectMapper.writeValueAsString(book);
        assertThat(result).isEqualTo("{\"isbn\":\"1\",\"title\":\"Sunny Days\",\"author\":\"James King\",\"year\":\"1997\"}");
    }
    @Test
    public void testThatObjectMapperCanCreateJavaObjectFromJsonObject() throws JsonProcessingException {
        String json = "{\"foo\":\"bar\",\"isbn\":\"1\",\"title\":\"Sunny Days\",\"author\":\"James King\",\"year\":\"1997\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        Book book = objectMapper.readValue(json, Book.class);
        Book book1 = Book.builder()
                .isbn("1")
                .title("Sunny Days")
                .author("James King")
                .yearPublished("1997")
                .build();
        assertThat(book.toString()).isEqualTo(book1.toString());
    }
}
