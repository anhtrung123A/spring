package com.phamtrung.booking.controller;

import com.phamtrung.booking.domain.entity.ConcertEntity;
import com.phamtrung.booking.service.ConcertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ConcertController {
    private ConcertService concertService;
    public ConcertController(ConcertService concertService){
        this.concertService = concertService;
    }
    @CrossOrigin
    @GetMapping(path = "/concerts")
    public ResponseEntity<List<ConcertEntity>> listConcerts(){
        List<ConcertEntity> concertEntityList = concertService.findAll();
        if (concertEntityList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(concertEntityList, HttpStatus.OK);
        }
    }
    @CrossOrigin
    @GetMapping(path = "/concert/{id}")
    public ResponseEntity<ConcertEntity> findConcertByID(@PathVariable("id") long id){
        Optional<ConcertEntity> concertEntity = concertService.findOne(id);
        return concertEntity.map(concertEntity1 -> {
            return new ResponseEntity<>(concertEntity1, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
