package com.microservices.skeleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {
    BikeRepository repository;

    @Autowired
    public BikeService(BikeRepository repository) {
        this.repository = repository;
    }

    public List<Bike> get() {
        return repository.findAll();
    }

    public Bike insert(Bike bike) {
        bike.setRisk(0.3);
        bike.setPrice(bike.getCoverage() * bike.getRisk());
        return repository.save(bike);
    }
}
