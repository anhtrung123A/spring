package com.phamtrung.booking.repository;

import com.phamtrung.booking.domain.entity.HallEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallRepository extends JpaRepository<HallEntity, Long> {
    List<HallEntity> findByconcertEntity_id(long id);
}
