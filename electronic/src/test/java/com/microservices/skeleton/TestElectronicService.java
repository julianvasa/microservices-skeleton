package com.microservices.skeleton;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestElectronicService {

    private static Validator validator;

    @Autowired
    private ElectronicsService service;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenCoverageWithinRange_SavedElectronic() {
        Electronic electronic = new Electronic();
        electronic.setCoverage(2000.0);

        Electronic savedElectronic = service.insert(electronic);
        assertNotNull(savedElectronic);
    }

    @Test(expected = TransactionSystemException.class)
    public void whenCoverageOutOfRange_NotSavedElectronic() {
        Electronic electronic = new Electronic();
        electronic.setCoverage(200000.0);
        service.insert(electronic);
    }

    @SpringBootApplication
    static class Configuration {
    }
}
