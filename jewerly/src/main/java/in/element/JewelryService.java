package in.element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JewelryService {

    JewelryRepository repository;

    @Autowired
    public JewelryService(JewelryRepository repository) {
        this.repository = repository;
    }

    public List<Jewelry> get() {
        return repository.findAll();
    }

    public Jewelry insert(Jewelry jewelry) {
        jewelry.setRisk(0.05);
        jewelry.setPrice(jewelry.getCoverage() * jewelry.getRisk());
        return repository.save(jewelry);
    }
}
