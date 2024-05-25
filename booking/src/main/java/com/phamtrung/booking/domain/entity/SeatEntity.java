package com.phamtrung.booking.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "seat")
@IdClass(SeatID.class)
public class SeatEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Id
    @ManyToOne
    @JoinColumn(name = "hall", referencedColumnName = "id")
    private HallEntity hallEntity;
    @Column(name = "status")
    private String status;
    @Column(name = "price")
    private double price;
}
