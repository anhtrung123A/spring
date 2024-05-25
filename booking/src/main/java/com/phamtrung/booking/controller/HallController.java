package com.phamtrung.booking.controller;

import com.phamtrung.booking.domain.entity.HallEntity;
import com.phamtrung.booking.service.HallService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class HallController {
    private HallService hallService;
    HallController(HallService hallService){
        this.hallService = hallService;
    }
    @GetMapping(path = "/halls")
    public ResponseEntity<List<HallEntity>> listHalls(){
        List<HallEntity> hallEntityList = hallService.findAll();
        if (hallEntityList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(hallEntityList, HttpStatus.OK);
        }
    }
    @GetMapping(path = "/hall/{id}")
    public ResponseEntity<HallEntity> findHallById(@PathVariable("id") long id){
        Optional<HallEntity> hallEntity = hallService.findOne(id);
        return hallEntity.map(hallEntity1 -> {
            return new ResponseEntity<>(hallEntity1, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping(path = "/hall/concert/{id}")
    public ResponseEntity<List<HallEntity>> findHallByConcert(@PathVariable("id") long id){
        List<HallEntity> hallEntityList = hallService.findAllbyConcert(id);
        if (hallEntityList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(hallEntityList, HttpStatus.OK);
        }
    }
}
