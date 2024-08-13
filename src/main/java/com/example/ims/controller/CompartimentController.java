package com.example.ims.controller;


import com.example.ims.dto.CompartimentCreateDTO;
import com.example.ims.dto.CompartimentUpdateDTO;
import com.example.ims.model.Compartiment;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ims.service.CompartimentService;

import java.util.List;
import java.util.NoSuchElementException;

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
    public ResponseEntity<Object> createCompartiment(@Valid @RequestBody CompartimentCreateDTO compartimentCreateDTO) {
        try {
            Compartiment createdCompartiment = compartimentService.createCompartiment(compartimentCreateDTO);
            return new ResponseEntity<>(createdCompartiment, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de la création du compartiment.", HttpStatus.BAD_REQUEST);
        }
    }


    // Delete compartiment :/compartiment
    @DeleteMapping("/compartiment/{id}")
    public ResponseEntity<Object> deleteCompartimentById(@PathVariable Integer id) {

        try {
            Compartiment deletedCompartiment = compartimentService.deleteCompartimentById(id);
            return new ResponseEntity<>(deletedCompartiment, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error for any other exceptions
        }

    }

    @PutMapping("/compartiment/{id}")
    public ResponseEntity<Object> updateCompartimentById(@PathVariable Integer id,@RequestBody CompartimentUpdateDTO compartimentUpdateDTO) {

        try {
            Compartiment updatedCompartiment = compartimentService.updateCompartimentById(id, compartimentUpdateDTO);
            return new ResponseEntity<>(updatedCompartiment, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error for any other exceptions
        }

    }
}

