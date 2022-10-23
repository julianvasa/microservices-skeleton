package com.microservices.skeleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/electronics")
public class ElectronicController {

    private final ElectronicsService service;

    @Autowired
    public ElectronicController(ElectronicsService service) {
        this.service = service;
    }

    @GetMapping(produces="application/json")
    public List<Electronic> get(){
        return service.get();
    }

    @PostMapping(produces="application/json", consumes="application/json")
    public ResponseEntity<Electronic> insert(@Valid @RequestBody Electronic electronic) {
        HttpStatus httpStatus = HttpStatus.CREATED;
        try {
            service.insert(electronic);
        }
        catch (Exception e){
            httpStatus = HttpStatus.PRECONDITION_FAILED;
        }
        return new ResponseEntity<>(electronic, httpStatus);
    }

}
