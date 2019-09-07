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


    @SpringBootApplication
    static class Configuration {
    }

}
