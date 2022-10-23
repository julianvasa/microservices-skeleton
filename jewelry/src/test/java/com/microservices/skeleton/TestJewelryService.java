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
public class TestJewelryService {

    @Autowired
    private JewelryService service;


    @Test
    public void whenCoverageWithinRange_SavedJewelry() {
        Jewelry jewelry = new Jewelry();
        jewelry.setCoverage(2000.0);

        Jewelry savedJewelry = service.insert(jewelry);
        assertNotNull(savedJewelry);
    }


    @Test
    public void whenCoverageOutOfRange_NotSavedJewelry() {
        TransactionSystemException exception = assertThrows(
                TransactionSystemException.class,
                () -> {
                    Jewelry jewelry = new Jewelry();
                    jewelry.setCoverage(200000.0);
                    service.insert(jewelry);
                },
                "Expected whenCoverageOutOfRange_NotSavedJewelry() to throw, but it didn't"
        );

        assertFalse(Objects.requireNonNull(exception.getMessage()).isEmpty());
    }

}
