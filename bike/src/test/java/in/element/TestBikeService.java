package in.element;

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
public class TestBikeService {

    private static Validator validator;

    @Autowired
    private BikeService service;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenCoverageWithinRange_SavedBike() {
        Bike bike = new Bike();
        bike.setCoverage(2000.0);

        Bike savedBike = service.insert(bike);
        assertNotNull(savedBike);
    }

    @Test(expected = TransactionSystemException.class)
    public void whenCoverageOutOfRange_NotSavedBike() {
        Bike bike = new Bike();
        bike.setCoverage(200000.0);
        service.insert(bike);
    }

    @SpringBootApplication
    static class Configuration {
    }
}
