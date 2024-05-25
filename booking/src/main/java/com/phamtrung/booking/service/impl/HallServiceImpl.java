package com.phamtrung.booking.service.impl;

import com.phamtrung.booking.domain.entity.HallEntity;
import com.phamtrung.booking.repository.HallRepository;
import com.phamtrung.booking.service.HallService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HallServiceImpl implements HallService {
    private HallRepository hallRepository;
    HallServiceImpl(HallRepository hallRepository){
        this.hallRepository = hallRepository;
    }

    @Override
    public List<HallEntity> findAll() {
        return hallRepository.findAll();
    }

    @Override
    public Optional<HallEntity> findOne(long id) {
        return hallRepository.findById(id);
    }

    @Override
    public List<HallEntity> findAllbyConcert(long id) {
        return hallRepository.findByconcertEntity_id(id);
    }
}
