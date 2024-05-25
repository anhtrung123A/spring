package com.example.LinkingPGSQL.controllers;

import com.example.LinkingPGSQL.TestDataUtil;
import com.example.LinkingPGSQL.domain.entities.AuthorEntity;
import com.example.LinkingPGSQL.services.AuthorService;
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
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTests {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final AuthorService authorService;
    @Autowired
    public AuthorControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper, AuthorService authorService)
    {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.authorService = authorService;
    }
    @Test
    public void testThatCreateAuthorSuccessfullyReturnsHttp201Created() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        authorEntity.setId(null);
        String authorJson = objectMapper.writeValueAsString(authorEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }
    @Test
    public void testThatCreateAuthorSuccessfullyReturnsSavedAuthor() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        authorEntity.setId(null);
        String authorJson = objectMapper.writeValueAsString(authorEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Abigail Rose")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(80)
        );
    }
    @Test
    public void testThatListAuthorsReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testThatListAuthorsReturnsListAuthors() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        authorService.save(authorEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("Abigail Rose")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].age").value(80)
        );
    }
    @Test
    public void testThatFindOneAuthorReturnsHttpStatus200() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        authorService.save(authorEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/" + authorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testThatFindOneAuthorReturnsAnAuthor() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        authorService.save(authorEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/" + authorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value(authorEntity.getName())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.age").value(authorEntity.getAge()));
    }
    @Test
    public void testThatFindOneAuthorReturnHttpStatus404IfAuthorNotExist() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    public void testThatFullUpdateAuthorReturnsHttpStatus200() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        authorService.save(authorEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/"+authorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\":\"Alex\", \"age\":20}")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testThatFullUpdateAuthorReturnsUpdatedAuthor() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        authorService.save(authorEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/"+authorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\":\"Alex\", \"age\":20}")
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Alex"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(20));
    }
    @Test
    public void testThatUpdateAuthorReturnsHttpStatus404IfAuthorNotExist() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/authors/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{ \"name\":\"Alex\", \"age\":20}")
                ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    public void testThatPartialUpdateAuthorReturnsHttpStatus200() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        authorService.save(authorEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/authors/"+authorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Legend\"}")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testThatPartialUpdateAuthorReturnsHttpStatus404IfAuthorNotExist() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Legend\"}")
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    public void testThatPartialUpdateAuthorReturnsUpdatedAuthor() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        authorService.save(authorEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/authors/" + authorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Legend\"}")
        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Legend"));
    }
    @Test
    public void testThatDeleteAuthorReturnsHttpStatus204() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        authorService.save(authorEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/authors/" + authorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
    @Test
    public void testThatDeleteAuthorReturnsHttpStatus404() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/authors/2")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    public void testThatDeleteAuthorSuccessfullyDeleteAnAuthor() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        authorService.save(authorEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/authors/"+authorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/"+authorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}


