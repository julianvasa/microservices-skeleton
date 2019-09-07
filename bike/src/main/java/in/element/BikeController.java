package in.element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bikes")
public class BikeController {

    private BikeService service;

    @Autowired
    public BikeController(BikeService service) {
        this.service = service;
    }

    @GetMapping(produces="application/json")
    public List<Bike> get(){
        return service.get();
    }

    @PostMapping(produces="application/json", consumes="application/json")
    public ResponseEntity<Bike> insert(@Valid @RequestBody Bike bike) {
        HttpStatus httpStatus = HttpStatus.CREATED;
        try {
            service.insert(bike);
        }
        catch (Exception e){
            httpStatus = HttpStatus.PRECONDITION_FAILED;
        }
        return new ResponseEntity<>(bike, httpStatus);
    }

}
