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
public class TestSportService {

    @Autowired
    private SportsService service;


    @Test
    public void whenCoverageWithinRange_SavedSport() {
        Sport sport = new Sport();
        sport.setCoverage(2000.0);

        Sport savedSport = service.insert(sport);
        assertNotNull(savedSport);
    }


    @Test
    public void whenCoverageOutOfRange_NotSavedSport() {
        TransactionSystemException exception = assertThrows(TransactionSystemException.class, () -> {
                    Sport sport = new Sport();
                    sport.setCoverage(2000000.0);
                    service.insert(sport);
                },
                "Expected whenCoverageOutOfRange_NotSavedSport() to throw, but it didn't"
        );

        assertFalse(Objects.requireNonNull(exception.getMessage()).isEmpty());
    }

}
