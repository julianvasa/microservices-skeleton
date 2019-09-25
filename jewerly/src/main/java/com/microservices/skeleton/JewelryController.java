package com.microservices.skeleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/jewelry")
public class JewelryController {

    private JewelryService service;

    @Autowired
    public JewelryController(JewelryService service) {
        this.service = service;
    }

    @GetMapping(produces="application/json")
    public List<Jewelry> get(){
        return service.get();
    }

    @PostMapping(produces="application/json", consumes="application/json")
    public ResponseEntity<Jewelry> insert(@Valid @RequestBody Jewelry jewelry) {
        HttpStatus httpStatus = HttpStatus.CREATED;
        try {
            service.insert(jewelry);
        }
        catch (Exception e){
            httpStatus = HttpStatus.PRECONDITION_FAILED;
        }
        return new ResponseEntity<>(jewelry, httpStatus);
    }

}
