package in.element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportsService {

    SportsRepository repository;

    @Autowired
    public SportsService(SportsRepository repository) {
        this.repository = repository;
    }

    public List<Sport> get() {
        return repository.findAll();
    }

    public Sport insert(Sport sport) {
        sport.setRisk(0.3);
        sport.setPrice(sport.getCoverage() * sport.getRisk());
        return repository.save(sport);
    }
}
