package com.phamtrung.booking.service.impl;

import com.phamtrung.booking.domain.entity.ConcertEntity;
import com.phamtrung.booking.repository.ConcertRepository;
import com.phamtrung.booking.service.ConcertService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConcertServiceImpl implements ConcertService {
    private ConcertRepository concertRepository;
    ConcertServiceImpl(ConcertRepository concertRepository){
        this.concertRepository = concertRepository;
    }

    @Override
    public List<ConcertEntity> findAll() {
        return concertRepository.findAll();
    }

    @Override
    public Optional<ConcertEntity> findOne(long id) {
        return concertRepository.findById(id);
    }
}
