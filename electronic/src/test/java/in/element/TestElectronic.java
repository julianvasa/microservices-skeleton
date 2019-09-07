package in.element;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.*;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class TestElectronic {

    private static Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void electronicHasCoverageWithinRange() {
        Object electronic = new Electronic();
        ((Electronic) electronic).setCoverage(2000.0);
        Optional<ConstraintViolation<Object>> violation = validator.validate(electronic).stream().findFirst();
        if (violation.isPresent()) {
            throw new ValidationException(violation.get().getMessage());
        }
    }

    @Test
    public void electronicHasCoverageOutOfRange() {
        Object electronic = new Electronic();
        ((Electronic) electronic).setCoverage(2000000.0);
        Optional<ConstraintViolation<Object>> violation = validator.validate(electronic).stream().findFirst();
        assertEquals(violation.isPresent(), true);
    }

}
