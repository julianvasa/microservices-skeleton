package com.microservices.skeleton;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
public class TestBikeRepository {

    @Autowired
    private BikeRepository repository;

    @Test
    public void insertBike() {
        Bike bike = new Bike();
        bike.setCoverage(2000.0);
        Bike savedBike = repository.save(bike);
        assertNotNull(savedBike);
    }

    @Test
    public void whenInsertedBikeCheckRepositorySize() {
        Bike bike = new Bike();
        bike.setCoverage(2000.0);
        repository.save(bike);

        List<Bike> bikes = repository.findAll();
        assertEquals(1, bikes.size());
    }



}
