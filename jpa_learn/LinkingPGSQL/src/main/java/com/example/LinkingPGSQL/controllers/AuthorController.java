package com.example.LinkingPGSQL.controllers;

import com.example.LinkingPGSQL.domain.dto.AuthorDto;
import com.example.LinkingPGSQL.domain.entities.AuthorEntity;
import com.example.LinkingPGSQL.mappers.Mapper;
import com.example.LinkingPGSQL.repositories.AuthorRepository;
import com.example.LinkingPGSQL.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AuthorController {
    private AuthorService authorService;
    private Mapper<AuthorEntity, AuthorDto> authorMapper;
    private AuthorRepository authorRepository;
    public AuthorController(AuthorService authorService, Mapper<AuthorEntity, AuthorDto> authorMapper, AuthorRepository authorRepository){
        this.authorService = authorService;
        this.authorMapper = authorMapper;
        this.authorRepository = authorRepository;
    }
    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto){
        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        AuthorEntity saveAuthorEntity = authorService.save(authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(saveAuthorEntity), HttpStatus.CREATED);
    }
    @GetMapping(path = "/authors")
    public List<AuthorDto> listAuthors()
    {
        List<AuthorEntity> authorEntityList = authorService.findAll();
        return authorEntityList.stream().map(authorMapper::mapTo).
                collect(Collectors.toList());
    }
    @GetMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> readOneAuthor(@PathVariable("id") Long id)
    {
        Optional<AuthorEntity> authorEntity = authorService.findOne(id);
        return authorEntity.map(authorEntity1 -> {
            AuthorDto authorDto = authorMapper.mapTo(authorEntity1);
            return new ResponseEntity<>(authorDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> fullUpdateAuthor(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto)
    {
        Optional<AuthorEntity> authorEntity = authorService.findOne(id);
        if (authorEntity.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
        {
            authorDto.setId(id);
            AuthorEntity authorEntity1 = authorMapper.mapFrom(authorDto);
            AuthorEntity authorEntity2 = authorService.save(authorEntity1);
            return new ResponseEntity<>(authorMapper.mapTo(authorEntity2), HttpStatus.OK);
        }
    }
    @PatchMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> partialUpdateAuthor(
            @PathVariable("id") Long id, @RequestBody AuthorDto authorDto
    )
    {
        Optional<AuthorEntity> authorEntity = authorService.findOne(id);
        if (authorEntity.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
        {
            AuthorEntity authorEntity1 = authorMapper.mapFrom(authorDto);
            AuthorEntity authorEntity2 = authorService.partialUpdate(id, authorEntity1);
            return new ResponseEntity<>(authorMapper.mapTo(authorEntity2), HttpStatus.OK);
        }
    }
    @DeleteMapping(path = "/authors/{id}")
    public ResponseEntity deleteAuthor(@PathVariable("id") Long id)
    {
        Optional<AuthorEntity> authorEntity = authorService.findOne(id);
        if(authorEntity.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            authorService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
