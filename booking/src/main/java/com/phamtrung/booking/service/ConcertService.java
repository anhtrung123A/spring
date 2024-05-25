package com.phamtrung.booking.service;

import com.phamtrung.booking.domain.entity.ConcertEntity;

import java.util.List;
import java.util.Optional;

public interface ConcertService {
    List<ConcertEntity> findAll();
    Optional<ConcertEntity> findOne(long id);
}
