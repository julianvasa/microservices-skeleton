package com.microservices.skeleton;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class TestSport {

    private static Validator validator;


    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    public void sportHasCoverageWithinRange() {
        Object sport = new Sport();
        ((Sport) sport).setCoverage(2000.0);
        Optional<ConstraintViolation<Object>> violation = validator.validate(sport).stream().findFirst();
        if (violation.isPresent()) {
            throw new ValidationException(violation.get().getMessage());
        }
    }

    @Test
    public void sportHasCoverageOutOfRange() {
        Object sport = new Sport();
        ((Sport) sport).setCoverage(2000000.0);
        Optional<ConstraintViolation<Object>> violation = validator.validate(sport).stream().findFirst();
        assertTrue(violation.isPresent());
    }

}
