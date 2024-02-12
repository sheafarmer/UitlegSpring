package com.example.techiteasy.controllers;
import com.example.techiteasy.models.Television;
import com.example.techiteasy.services.TelevisionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("televisions")
public class TelevisionController {

    private TelevisionService televisionService;

    public TelevisionController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    // CREATE

    @PostMapping
    public ResponseEntity<Television> createTelevision(@RequestBody Television television) {
        Television savedTelevision = televisionService.save(television);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTelevision);
    }

    // READ: alle auto's of gefilterd op merk

    @GetMapping
    public ResponseEntity<List<Television>> getAllTelevision(@RequestParam(name = "brand",
            required = false) String brand) {
        List<Television> television = (brand == null) ? televisionService.getAllTelevision() :
                televisionService.getTelevisionByBrand(brand);
        return ResponseEntity.ok(television);
    }

    // READ: specifieke auto

    @GetMapping("/{id}")
    public ResponseEntity<Television> getTelevision(@PathVariable Long id){
        return televisionService.getTelevisionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    // UPDATE

    @PutMapping("/{id}")
    public ResponseEntity<Television> updateTelevision(@PathVariable Long id,@RequestBody Television
            televisionDetails){
        return televisionService.getTelevisionById(id)
                .map(television->{
                    television.setBrand(televisionDetails.getBrand());
                    television.setType(televisionDetails.getType());
                    Television updatedTelevision=televisionService.save(television);
                    return ResponseEntity.ok(updatedTelevision);
                }).orElseGet(()->ResponseEntity.notFound().build());
    }

    // DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTelevision(@PathVariable Long id){
        if(televisionService.getTelevisionById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        televisionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
