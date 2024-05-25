package com.phamtrung.booking.repository;

import com.phamtrung.booking.domain.entity.SeatEntity;
import com.phamtrung.booking.domain.entity.SeatID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<SeatEntity, SeatID> {
    List<SeatEntity> findByhallEntity_id(long id);
    List<SeatEntity> findByhallEntity_concertEntity_id(long id);
}
