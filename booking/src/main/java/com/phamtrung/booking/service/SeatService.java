package com.phamtrung.booking.service;

import com.phamtrung.booking.domain.entity.SeatEntity;

import java.util.List;
import java.util.Optional;

public interface SeatService {
    void save(SeatEntity seatEntity);
    List<SeatEntity> findAll();
    List<SeatEntity> findByHallID(long hallID);
    Optional<SeatEntity> findOne(String seatID, long hallID);
    List<SeatEntity> findByConcertID(long concertID);
}
