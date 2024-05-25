package com.example.LinkingPGSQL.services.impl;

import com.example.LinkingPGSQL.domain.entities.AuthorEntity;
import com.example.LinkingPGSQL.repositories.AuthorRepository;
import com.example.LinkingPGSQL.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;
    public AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }
    @Override
    public AuthorEntity save(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }

    @Override
    public List<AuthorEntity> findAll() {
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AuthorEntity> findOne(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity) {
        authorEntity.setId(id);
        return authorRepository.findById(id).map(existedAuthor ->{
            Optional.ofNullable(authorEntity.getName()).ifPresent(existedAuthor::setName);
            Optional.ofNullable(authorEntity.getAge()).ifPresent(existedAuthor::setAge);
            return authorRepository.save(existedAuthor);
         }).orElseThrow(()->new RuntimeException("Author does not exist"));
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
