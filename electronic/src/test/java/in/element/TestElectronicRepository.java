package in.element;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
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


    @SpringBootApplication
    static class Configuration {
    }

}
