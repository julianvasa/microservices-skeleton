package com.microservices.skeleton;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
public class TestElectronicRepository {

    @Autowired
    private ElectronicsRepository repository;

    @Test
    public void insertElectronic() {
        Electronic electronic = new Electronic();
        electronic.setCoverage(2000.0);
        Electronic savedElectronic = repository.save(electronic);
        assertNotNull(savedElectronic);
    }

    @Test
    public void whenInsertedElectronicCheckRepositorySize() {
        Electronic electronic = new Electronic();
        electronic.setCoverage(2000.0);
        repository.save(electronic);
        List<Electronic> electronics = repository.findAll();
        assertEquals(1, electronics.size());
    }



}
