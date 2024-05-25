package com.phamtrung.booking.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "concert")
public class ConcertEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "main_picture", columnDefinition = "TEXT")
    private String mainPicture;
    @Column(name = "small_picture", columnDefinition = "TEXT")
    private String smallPicture;
    @Column(name = "time", columnDefinition = "TEXT")
    private String time;
    @Column(name = "note", columnDefinition = "TEXT")
    private String note;
}
