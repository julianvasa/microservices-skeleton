package com.microservices.skeleton;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class TestBikeService {

    @Autowired
    private BikeService service;


    @Test
    public void whenCoverageWithinRange_SavedBike() {
        Bike bike = new Bike();
        bike.setCoverage(2000.0);

        Bike savedBike = service.insert(bike);
        assertNotNull(savedBike);
    }


    @Test
    public void whenCoverageOutOfRange_NotSavedBike() {
        TransactionSystemException exception = assertThrows(TransactionSystemException.class,
                () -> {
                    Bike bike = new Bike();
                    bike.setCoverage(200000.0);
                    service.insert(bike);
                },
                "Expected whenCoverageOutOfRange_NotSavedBike() to throw, but it didn't"
        );

        assertFalse(Objects.requireNonNull(exception.getMessage()).isEmpty());
    }

}
