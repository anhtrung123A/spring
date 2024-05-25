package com.phamtrung.booking.service.impl;

import com.phamtrung.booking.domain.entity.HallEntity;
import com.phamtrung.booking.domain.entity.SeatEntity;
import com.phamtrung.booking.domain.entity.SeatID;
import com.phamtrung.booking.repository.SeatRepository;
import com.phamtrung.booking.service.SeatService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatServiceImpl implements SeatService {
    private SeatRepository seatRepository;
    SeatServiceImpl(SeatRepository seatRepository){
        this.seatRepository = seatRepository;
    }

    @Override
    public List<SeatEntity> findAll() {
        return seatRepository.findAll();
    }

    @Override
    public Optional<SeatEntity> findOne(String seatID, long hallID) {
        SeatID seatID1 = SeatID.builder()
                .id(seatID)
                .hallEntity(HallEntity.builder().id(hallID).build())
                .build();
        return seatRepository.findById(seatID1);
    }

    @Override
    public void save(SeatEntity seatEntity) {
        seatRepository.save(seatEntity);
    }

    @Override
    public List<SeatEntity> findByHallID(long hallID) {
        return seatRepository.findByhallEntity_id(hallID);
    }

    @Override
    public List<SeatEntity> findByConcertID(long concertID) {
        return seatRepository.findByhallEntity_concertEntity_id(concertID);
    }
}
