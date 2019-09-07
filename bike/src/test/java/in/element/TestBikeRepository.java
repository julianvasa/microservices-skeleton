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
public class TestBikeRepository {

    @Autowired
    private BikeRepository repository;

    @Test
    public void insertBike() {
        Bike bike = new Bike();
        bike.setCoverage(2000.0);
        Bike savedBike = repository.save(bike);
        assertNotNull(savedBike);
    }

    @Test
    public void whenInsertedBikeCheckRepositorySize() {
        Bike bike = new Bike();
        bike.setCoverage(2000.0);
        Bike savedBike = repository.save(bike);

        List<Bike> bikes = repository.findAll();
        assertEquals(1, bikes.size());
    }


    @SpringBootApplication
    static class Configuration {
    }

}
