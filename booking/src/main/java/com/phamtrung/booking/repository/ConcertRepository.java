package com.phamtrung.booking.repository;

import com.phamtrung.booking.domain.entity.ConcertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertRepository extends JpaRepository<ConcertEntity, Long>
{
}
