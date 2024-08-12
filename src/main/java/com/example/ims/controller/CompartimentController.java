package com.example.ims.controller;


import com.example.ims.dto.CompartimentRequestDTO;
import com.example.ims.model.Compartiment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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



    //post compartiment : /compartiment
    @PostMapping("/compartiment")
    @ResponseBody
    public ResponseEntity<Object> createCompartiment(@RequestBody CompartimentRequestDTO compartimentRequestDTO) {
        try {
            Compartiment createdCompartiment = compartimentService.createCompartiment(compartimentRequestDTO);
            return new ResponseEntity<>(createdCompartiment, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de la création du compartiment.", HttpStatus.BAD_REQUEST);
        }
    }


}
