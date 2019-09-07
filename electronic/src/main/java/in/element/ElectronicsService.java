package in.element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectronicsService {

    ElectronicsRepository repository;

    @Autowired
    public ElectronicsService(ElectronicsRepository repository) {
        this.repository = repository;
    }

    public List<Electronic> get() {
        return repository.findAll();
    }

    public Electronic insert(Electronic electronic) {
        electronic.setRisk(0.35);
        electronic.setPrice(electronic.getCoverage() * electronic.getRisk());
        return repository.save(electronic);
    }
}
