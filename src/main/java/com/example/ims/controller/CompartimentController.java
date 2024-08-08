package com.example.ims.controller;


import com.example.ims.model.Compartiment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.ims.service.CompartimentService;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class CompartimentController {
    private final CompartimentService compartimentService;

    @Autowired
    public CompartimentController(CompartimentService compartimentService) {
        this.compartimentService = compartimentService;

    }


    //get compartiments : /compartiments
    @GetMapping("/compartiments")
    @ResponseBody


    public ResponseEntity<Object> getAllCompatiments() {

        List<Compartiment> compartiments = compartimentService.getAllCompartiments();

        if (compartiments.isEmpty()) {
            return new ResponseEntity<>("No compartiments found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(compartiments, HttpStatus.OK);

    }


}
