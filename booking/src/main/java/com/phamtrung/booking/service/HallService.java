package com.phamtrung.booking.service;

import com.phamtrung.booking.domain.entity.HallEntity;

import java.util.List;
import java.util.Optional;

public interface HallService {
    List<HallEntity> findAll();
    Optional<HallEntity> findOne(long id);
    List<HallEntity> findAllbyConcert(long id);
}
