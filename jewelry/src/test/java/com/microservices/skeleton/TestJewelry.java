package com.microservices.skeleton;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestJewelry {

    private static Validator validator;


    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    public void jewelryHasCoverageWithinRange() {
        Object jewelry = new Jewelry();
        ((Jewelry) jewelry).setCoverage(2000.0);
        Optional<ConstraintViolation<Object>> violation = validator.validate(jewelry).stream().findFirst();
        if (violation.isPresent()) {
            throw new ValidationException(violation.get().getMessage());
        }
    }

    @Test
    public void jewelryHasCoverageOutOfRange() {
        Object jewelry = new Jewelry();
        ((Jewelry) jewelry).setCoverage(2000000.0);
        Optional<ConstraintViolation<Object>> violation = validator.validate(jewelry).stream().findFirst();
        assertTrue(violation.isPresent());
    }

}
