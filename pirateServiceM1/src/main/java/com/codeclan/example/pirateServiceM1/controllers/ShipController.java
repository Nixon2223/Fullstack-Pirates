package com.codeclan.example.pirateServiceM1.controllers;

import com.codeclan.example.pirateServiceM1.models.Ship;
import com.codeclan.example.pirateServiceM1.repositories.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShipController {
    @Autowired
    ShipRepository shipRepository;

    @GetMapping(value = "/ships")
    public ResponseEntity<List<Ship>> getAllShips(){
        return new ResponseEntity<>(shipRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/ships/{id}")
    public ResponseEntity getShip(@PathVariable Long id){
        return new ResponseEntity<>(shipRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/ships/pirates")
    public ResponseEntity<List<Ship>> findShipsThatHavePiratesNamedQueryString(
            @RequestParam(name = "named") String name){
        return new ResponseEntity<>(shipRepository.findByPiratesFirstName(name), HttpStatus.OK);
    }

    @PostMapping(value = "/ships")
    public ResponseEntity<Ship> postShip(@RequestBody Ship ship) {
        shipRepository.save(ship);
        return new ResponseEntity<>(ship, HttpStatus.CREATED);
    }
}
