package com.phamtrung.booking.controller;

import com.phamtrung.booking.domain.entity.SeatEntity;
import com.phamtrung.booking.domain.entity.SeatID;
import com.phamtrung.booking.service.SeatService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Log
public class SeatController {
    private SeatService seatService;
    SeatController(SeatService seatService){
        this.seatService = seatService;
    }
    @GetMapping(path = "/seats")
    public ResponseEntity<List<SeatEntity>> listSeats(){
        List<SeatEntity> seatEntities = seatService.findAll();
        if (seatEntities.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(seatEntities, HttpStatus.OK);
        }
    }
    @CrossOrigin
    @GetMapping(path = "/seat/concert/{concertID}")
    public ResponseEntity<List<SeatEntity>> findSeatsByConcertID(@PathVariable("concertID") long concertID){
        List<SeatEntity> seatEntities = seatService.findByConcertID(concertID);
        if (seatEntities.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(seatEntities, HttpStatus.OK);
        }
    }
    @CrossOrigin
    @GetMapping(path = "/seat/{hallID}")
    public ResponseEntity<List<SeatEntity>> findSeatsByHallID(@PathVariable("hallID") long hallID){
        List<SeatEntity> seatEntities = seatService.findByHallID(hallID);
        if (seatEntities.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(seatEntities, HttpStatus.OK);
        }
    }
    @GetMapping(path = "/seat/{seatID}/{hallID}")
    public ResponseEntity<SeatEntity> findSeatById(@PathVariable("seatID") String seatID, @PathVariable("hallID") long hallID){
        Optional<SeatEntity> seatEntity = seatService.findOne(seatID, hallID);
        return seatEntity.map(seatEntity1 -> {
            return new ResponseEntity<>(seatEntity1, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @CrossOrigin
    @PostMapping(path = "/seat")
    public ResponseEntity book(@RequestBody List<Holder> holder){
        for(Holder holder1: holder){
            Optional<SeatEntity> seatEntity = seatService.findOne(holder1.getSeatID(), holder1.getHallID());
            seatEntity.ifPresent(seatEntity1 -> {
                seatEntity1.setStatus("selected");
                seatService.save(seatEntity1);
            });
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
