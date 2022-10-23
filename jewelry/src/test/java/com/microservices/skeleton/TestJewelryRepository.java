package com.microservices.skeleton;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
public class TestJewelryRepository {

    @Autowired
    private JewelryRepository repository;

    @Test
    public void insertJewelry() {
        Jewelry jewelry = new Jewelry();
        jewelry.setCoverage(2000.0);
        Jewelry savedJewelry = repository.save(jewelry);
        assertNotNull(savedJewelry);
    }

    @Test
    public void whenInsertedJewelryCheckRepositorySize() {
        Jewelry jewelry = new Jewelry();
        jewelry.setCoverage(2000.0);
        repository.save(jewelry);
        List<Jewelry> jewelrys = repository.findAll();
        assertEquals(1, jewelrys.size());
    }



}
