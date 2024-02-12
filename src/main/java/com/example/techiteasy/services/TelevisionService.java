package com.example.techiteasy.services;
import com.example.techiteasy.models.Television;
import com.example.techiteasy.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {
    private TelevisionRepository televisionRepository;

    public TelevisionService(TelevisionRepository televisionRepository) {

        this.televisionRepository = televisionRepository;

    }

    public List<Television> getAllTelevision() {

        return televisionRepository.findAll();

    }

    public Optional<Television> getTelevisionById(Long id) {

        return televisionRepository.findById(id);

    }

    public List<Television> getTelevisionByBrand(String brand) {
        return televisionRepository.findByBrand(brand);
    }

    public Television save(Television television) {
        return televisionRepository.save(television);
    }

    public void deleteById(Long id) {
        televisionRepository.deleteById(id);
    }

}
