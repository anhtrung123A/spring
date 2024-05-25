package springwithreactnative.travelspot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springwithreactnative.travelspot.entities.SpotEntity;
import springwithreactnative.travelspot.services.SpotService;

import java.util.List;

@RestController
public class SpotController {
    private final SpotService spotService;
    SpotController(SpotService spotService){
        this.spotService = spotService;
    }
    @CrossOrigin
    @GetMapping(path = "/get-spot")
    public ResponseEntity<List<SpotEntity>> sayHello(){
        List<SpotEntity> spotEntities = spotService.getAll();
        if (spotEntities.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(spotEntities, HttpStatus.OK);
        }
    }
}
