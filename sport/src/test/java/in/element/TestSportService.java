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
public class TestSportService {

    private static Validator validator;

    @Autowired
    private SportsService service;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenCoverageWithinRange_SavedSport() {
        Sport sport = new Sport();
        sport.setCoverage(2000.0);

        Sport savedSport = service.insert(sport);
        assertNotNull(savedSport);
    }

    @Test(expected = TransactionSystemException.class)
    public void whenCoverageOutOfRange_NotSavedSport() {
        Sport sport = new Sport();
        sport.setCoverage(200000.0);
        service.insert(sport);
    }

    @SpringBootApplication
    static class Configuration {
    }
}
