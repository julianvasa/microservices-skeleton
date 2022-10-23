package com.microservices.skeleton;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
public class TestSportRepository {

    @Autowired
    private SportsRepository repository;

    @Test
    public void insertElectronic() {
        Sport sport = new Sport();
        sport.setCoverage(2000.0);
        Sport savedSport = repository.save(sport);
        assertNotNull(savedSport);
    }

    @Test
    public void whenInsertedElectronicCheckRepositorySize() {
        Sport sport = new Sport();
        sport.setCoverage(2000.0);
        repository.save(sport);
        List<Sport> sports = repository.findAll();
        assertEquals(1, sports.size());
    }



}
