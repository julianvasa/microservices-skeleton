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
@RequestMapping("/sports")
public class SportController {

    private final SportsService service;

    @Autowired
    public SportController(SportsService service) {
        this.service = service;
    }


    @GetMapping(produces="application/json")
    public List<Sport> get() {
        return service.get();
    }

    @PostMapping(produces="application/json", consumes="application/json")
    public ResponseEntity<Sport> insert(@Valid @RequestBody Sport sport) {
        HttpStatus httpStatus = HttpStatus.CREATED;
        try {
            service.insert(sport);
        } catch (Exception e) {
            httpStatus = HttpStatus.PRECONDITION_FAILED;
        }
        return new ResponseEntity<>(sport, httpStatus);
    }

}
