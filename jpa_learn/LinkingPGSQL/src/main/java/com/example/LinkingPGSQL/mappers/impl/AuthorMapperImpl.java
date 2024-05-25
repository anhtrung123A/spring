package com.example.LinkingPGSQL.mappers.impl;

import com.example.LinkingPGSQL.domain.dto.AuthorDto;
import com.example.LinkingPGSQL.domain.entities.AuthorEntity;
import com.example.LinkingPGSQL.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapperImpl implements Mapper<AuthorEntity, AuthorDto> {
    private ModelMapper modelMapper;
    public AuthorMapperImpl(ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorDto mapTo(AuthorEntity authorEntity) {
        return modelMapper.map(authorEntity, AuthorDto.class);
    }

    @Override
    public AuthorEntity mapFrom(AuthorDto authorDto) {
        return modelMapper.map(authorDto, AuthorEntity.class);
    }
}
