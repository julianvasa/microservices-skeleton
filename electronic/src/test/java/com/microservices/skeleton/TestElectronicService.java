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
public class TestElectronicService {

    @Autowired
    private ElectronicsService service;


    @Test
    public void whenCoverageWithinRange_SavedElectronic() {
        Electronic electronic = new Electronic();
        electronic.setCoverage(2000.0);

        Electronic savedElectronic = service.insert(electronic);
        assertNotNull(savedElectronic);
    }


    @Test
    public void whenCoverageOutOfRange_NotSavedElectronic() {
        TransactionSystemException exception = assertThrows(
                TransactionSystemException.class,
                () -> {
                    Electronic electronic = new Electronic();
                    electronic.setCoverage(200000.0);
                    service.insert(electronic);
                },
                "Expected whenCoverageOutOfRange_NotSavedSport() to throw, but it didn't"
        );

        assertFalse(Objects.requireNonNull(exception.getMessage()).isEmpty());
    }

}
